package edu.nju.sa2022.micropos.cart;

import edu.nju.sa2022.micropos.services.CartService;
import edu.nju.sa2022.micropos.services.ProductService;
import edu.nju.sa2022.micropos.services.rest.ProductServiceRestImpl;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class CartConfiguration {

    @Bean
    @LoadBalanced
    public WebClient.Builder webClientBuilder() {
        return WebClient.builder();
    }

    @Bean
    public CartService cartService(CartRepository cartRepository,
                                   ProductService productService) {
        return new CartServiceImpl(cartRepository, productService);
    }

    @Bean
    public ProductService productService(WebClient.Builder webClientBuilder) {
        return new ProductServiceRestImpl(webClientBuilder.build());
    }

}
