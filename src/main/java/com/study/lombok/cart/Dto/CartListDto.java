package com.study.lombok.cart.Dto;

import com.study.lombok.cart.CartEntity;
import com.study.lombok.items.ItemsEntity;

import java.util.List;

public record CartListDto(String model, String brand, List<ItemsEntity> itemsEntity) {
    public CartListDto(CartEntity cartEntity) {
        this(
                cartEntity.getModel(),
                cartEntity.getBrand(),
                cartEntity.getItemsEntity()
        );
    }
}
