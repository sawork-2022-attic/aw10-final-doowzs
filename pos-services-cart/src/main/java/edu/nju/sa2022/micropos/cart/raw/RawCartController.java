package edu.nju.sa2022.micropos.cart.raw;

import edu.nju.sa2022.micropos.models.Order;
import edu.nju.sa2022.micropos.services.CartService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/raw/carts")
public class RawCartController {

    private final CartService cartService;

    public RawCartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping("{userId}/checkout")
    public Mono<Order> checkout(@PathVariable String userId) {
        return cartService.checkout(userId);
    }

}
