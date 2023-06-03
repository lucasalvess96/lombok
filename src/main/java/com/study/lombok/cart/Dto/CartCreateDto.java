package com.study.lombok.cart.Dto;

import com.study.lombok.cart.CartEntity;
import com.study.lombok.items.ItemsEntity;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

public record CartCreateDto(

        @NotBlank(message = "Campo obrigatório")
        String model,

        @NotBlank(message = "Campo obrigatório")
        String brand,

        @Valid
        List<ItemsEntity> itemsEntity

) {
    public CartCreateDto(CartEntity cartEntity) {
        this(
                cartEntity.getModel(),
                cartEntity.getBrand(),
                cartEntity.getItemsEntity()
        );
    }
}
