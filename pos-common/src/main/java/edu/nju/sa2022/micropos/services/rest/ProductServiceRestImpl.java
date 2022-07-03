package edu.nju.sa2022.micropos.services.rest;

import edu.nju.sa2022.micropos.models.Product;
import edu.nju.sa2022.micropos.services.ProductService;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class ProductServiceRestImpl implements ProductService {

    private final WebClient webClient;

    public ProductServiceRestImpl(WebClient webClient) {
        this.webClient = webClient;
    }

    @Override
    public Flux<Product> listProducts() {
        throw new UnsupportedOperationException("not implemented in rest impl");
    }

    @Override
    public Mono<Product> findProduct(String productId) {
        return webClient.get()
                .uri(String.format("http://product-service/raw/products/%s", productId))
                .retrieve()
                .bodyToMono(Product.class);
    }

    @Override
    public Mono<Product> saveProduct(Product product) {
        throw new UnsupportedOperationException("not implemented in rest impl");
    }

}
