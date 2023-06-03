package com.study.lombok.cart;

import com.study.lombok.cart.Dto.CartCreateDto;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/cart")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping("/create")
    @Transactional
    public ResponseEntity<CartCreateDto> create(@RequestBody @Valid CartCreateDto cartCreateDto) {
        CartCreateDto createDto = cartService.createCart(cartCreateDto);
        return new ResponseEntity<>(createDto, HttpStatus.CREATED);
    }
}
