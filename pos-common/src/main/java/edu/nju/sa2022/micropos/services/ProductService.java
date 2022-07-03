package edu.nju.sa2022.micropos.services;

import edu.nju.sa2022.micropos.models.Product;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductService {

    Flux<Product> listProducts();

    Mono<Product> findProduct(String productId);

    Mono<Product> saveProduct(Product product);

}
