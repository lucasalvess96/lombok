package com.study.lombok.cart;

import com.study.lombok.cart.Dto.CartCreateDto;
import com.study.lombok.items.ItemsEntity;
import org.springframework.stereotype.Service;

@Service
public record CartService(CartRepository cartRepository) {

    public CartCreateDto createCart(CartCreateDto cartCreateDto) {
        CartEntity cartEntity = new CartEntity();
        cartEntity.setModel(cartCreateDto.model());
        cartEntity.setBrand(cartEntity.getBrand());
        cartEntity.setItemsEntity(cartCreateDto.itemsEntity());

        ItemsEntity itemsEntity = new ItemsEntity();
        itemsEntity.setRoad(cartCreateDto.itemsEntity().get(0).getRoad());
        itemsEntity.setSeats(cartCreateDto.itemsEntity().get(0).getSeats());

        cartEntity.getItemsEntity().add(itemsEntity);
        itemsEntity.setCartEntity(cartEntity);
        return new CartCreateDto(cartRepository.save(cartEntity));
    }
}
