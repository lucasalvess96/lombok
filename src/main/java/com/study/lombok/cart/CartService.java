package com.study.lombok.cart;

import com.study.lombok.cart.Dto.CartCreateDto;
import com.study.lombok.cart.Dto.CartListDto;
import com.study.lombok.items.ItemsEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public record CartService(CartRepository cartRepository) {

    public CartCreateDto cartCreate(CartCreateDto cartCreateDto) {
        CartEntity cartEntity = new CartEntity();
        cartEntity.setId(cartCreateDto.id());
        cartEntity.setModel(cartCreateDto.model());
        cartEntity.setBrand(cartCreateDto.brand());

        List<ItemsEntity> itemsEntities = new ArrayList<>();
        if (cartCreateDto.itemsCreateDtos() != null) {
            itemsEntities = cartCreateDto.itemsCreateDtos().stream().map(itemsCreateDto -> {
                ItemsEntity itemsEntity = new ItemsEntity();
                itemsEntity.setId(itemsCreateDto.id());
                itemsEntity.setRoad(itemsCreateDto.road());
                itemsEntity.setSeats(itemsCreateDto.seats());
                itemsEntity.setCartEntity(cartEntity);
                return itemsEntity;
            }).collect(Collectors.toList());
        }
        cartEntity.setItemsEntity(itemsEntities);
        return new CartCreateDto(cartRepository.save(cartEntity));
    }

    public Page<CartListDto> cartList(Pageable pageable) {
        Page<CartEntity> cartEntityPage = cartRepository.findAll(pageable);
        return cartEntityPage.map(CartListDto::new);
    }
}
