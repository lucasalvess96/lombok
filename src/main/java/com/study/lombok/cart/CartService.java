package com.study.lombok.cart;

import com.study.lombok.cart.Dto.CartCreateDto;
import com.study.lombok.cart.Dto.CartListDto;
import com.study.lombok.items.ItemsEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public record CartService(CartRepository cartRepository) {

    public CartCreateDto cartCreate(CartCreateDto cartCreateDto) {
        CartEntity cartEntity = new CartEntity();
        cartEntity.setModel(cartCreateDto.model());
        cartEntity.setBrand(cartEntity.getBrand());

        ItemsEntity itemsEntity = new ItemsEntity();
        itemsEntity.setRoad(cartCreateDto.itemsEntity().get(0).getRoad());
        itemsEntity.setSeats(cartCreateDto.itemsEntity().get(0).getSeats());

        cartEntity.getItemsEntity().add(itemsEntity);
        itemsEntity.setCartEntity(cartEntity);
        return new CartCreateDto(cartRepository.save(cartEntity));
    }

    public Page<CartListDto> cartList(Pageable pageable) {
        Page<CartEntity> cartEntityPage = cartRepository.findAll(pageable);
        return cartEntityPage.map(CartListDto::new);
    }
}
