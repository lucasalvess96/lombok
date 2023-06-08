package com.study.lombok.cart;

import com.study.lombok.cart.Dto.CartCreateDto;
import com.study.lombok.cart.Dto.CartListDto;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
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
        CartCreateDto createDto = cartService.cartCreate(cartCreateDto);
        return new ResponseEntity<>(createDto, HttpStatus.CREATED);
    }

    @GetMapping("/list")
    public ResponseEntity<Page<CartListDto>> list(@PageableDefault(direction = Sort.Direction.DESC) Pageable pageable) {
        return ResponseEntity.ok().body(cartService.cartList(pageable));
    }
}
