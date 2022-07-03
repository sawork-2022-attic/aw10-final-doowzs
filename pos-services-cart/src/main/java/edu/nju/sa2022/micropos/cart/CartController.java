package edu.nju.sa2022.micropos.cart;

import edu.nju.sa2022.micropos.dtos.CartDto;
import edu.nju.sa2022.micropos.services.CartService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/carts")
public class CartController {

    private final CartMapper cartMapper;
    private final CartService cartService;

    private final Logger logger = LoggerFactory.getLogger(getClass());

    public CartController(CartService cartService, CartMapper cartMapper) {
        this.cartMapper = cartMapper;
        this.cartService = cartService;
    }

    @GetMapping("{userId}")
    public Mono<CartDto> getCart(@PathVariable String userId) {
        return cartService.getCart(userId)
                .map(cartMapper::toCartDto)
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND)));
    }

    @GetMapping("{userId}/total")
    public Mono<Double> getTotal(@PathVariable String userId) {
        return cartService.getTotal(userId)
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND)));
    }

    @PostMapping("{userId}/items/add")
    public Mono<CartDto> addItem(@PathVariable String userId,
                                 @RequestParam String productId,
                                 @RequestParam Integer quantity) {
        return cartService.addItem(userId, productId, quantity)
                .map(cartMapper::toCartDto)
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND)));
    }

    @PostMapping("{userId}/items/remove")
    public Mono<CartDto> removeItem(@PathVariable String userId,
                                    @RequestParam String productId) {
        return cartService.removeItem(userId, productId)
                .map(cartMapper::toCartDto)
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND)));
    }

}
