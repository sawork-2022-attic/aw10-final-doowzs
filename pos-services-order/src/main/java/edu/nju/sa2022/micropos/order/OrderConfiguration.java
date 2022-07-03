package edu.nju.sa2022.micropos.order;

import edu.nju.sa2022.micropos.services.CartService;
import edu.nju.sa2022.micropos.services.OrderService;
import edu.nju.sa2022.micropos.services.rest.CartServiceRestImpl;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class OrderConfiguration {

    @Bean
    @LoadBalanced
    public WebClient.Builder webClientBuilder() {
        return WebClient.builder();
    }

    @Bean
    public OrderService orderService(OrderRepository orderRepository,
                                     CartService cartService,
                                     StreamBridge streamBridge) {
        return new OrderServiceImpl(orderRepository, cartService, streamBridge);
    }

    @Bean
    public CartService cartService(WebClient.Builder webClientBuilder) {
        return new CartServiceRestImpl(webClientBuilder.build());
    }

}
