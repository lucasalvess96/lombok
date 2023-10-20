package com.study.lombok.cart.Dto;

import com.study.lombok.cart.CartEntity;
import com.study.lombok.items.ItemsCreateDto;
import com.study.lombok.items.ItemsEntity;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

import java.util.List;
import java.util.stream.Collectors;

public record CartCreateDto(

        Long id,

        @NotBlank(message = "Campo obrigatório") String model,

        @NotBlank(message = "Campo obrigatório") String brand,

        @Valid List<ItemsCreateDto> itemsCreateDtos

) {
    public CartCreateDto(CartEntity cartEntity) {
        this(cartEntity.getId(), cartEntity.getModel(), cartEntity.getBrand(), convertToItemsCreateDto(cartEntity.getItemsEntity()));
    }

    private static List<ItemsCreateDto> convertToItemsCreateDto(List<ItemsEntity> itemsEntities) {
        return itemsEntities.stream().map(itemsEntity -> new ItemsCreateDto(itemsEntity.getId(), itemsEntity.getRoad(), itemsEntity.getSeats())).collect(Collectors.toList());
    }
}
