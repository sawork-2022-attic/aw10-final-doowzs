package edu.nju.sa2022.micropos.cart;

import edu.nju.sa2022.micropos.models.Cart;
import edu.nju.sa2022.micropos.models.Order;
import edu.nju.sa2022.micropos.models.Product;
import edu.nju.sa2022.micropos.services.CartService;
import edu.nju.sa2022.micropos.services.ProductService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final ProductService productService;

    public CartServiceImpl(CartRepository cartRepository, ProductService productService) {
        this.cartRepository = cartRepository;
        this.productService = productService;
    }

    private Mono<Double> getCartTotal(Cart cart) {
        return Flux.fromIterable(cart.getItems().entrySet())
                .flatMap((entry) -> {
                    String productId = entry.getKey();
                    Integer quantity = entry.getValue();
                    return Mono.zip(productService.findProduct(productId), Mono.just(quantity));
                })
                .map((tuple) -> {
                    Product product = tuple.getT1();
                    Integer quantity = tuple.getT2();
                    return product.getPrice() * quantity;
                })
                .reduce(Double::sum);
    }

    @Override
    public Mono<Cart> getCart(String userId) {
        return cartRepository.findById(userId)
                .switchIfEmpty(cartRepository.save(Cart.builder().userId(userId).build()));
    }

    @Override
    public Mono<Cart> addItem(String userId, String productId, Integer quantity) {
        return getCart(userId)
                .flatMap((cart) -> {
                    cart.addItem(productId, quantity);
                    return cartRepository.save(cart);
                });
    }

    @Override
    public Mono<Cart> removeItem(String userId, String productId) {
        return getCart(userId)
                .flatMap((cart) -> {
                    cart.removeItem(productId);
                    return cartRepository.save(cart);
                });
    }

    @Override
    public Mono<Double> getTotal(String userId) {
        return getCart(userId)
                .flatMap(this::getCartTotal);
    }

    @Override
    public Mono<Order> checkout(String userId) {
        return getCart(userId)
                .flatMap((cart) -> cartRepository.delete(cart)
                        .then(getCartTotal(cart))
                        .map((total) -> Order.builder()
                                .userId(cart.getUserId())
                                .items(cart.getItems())
                                .total(total)
                                .build()));
    }

}
