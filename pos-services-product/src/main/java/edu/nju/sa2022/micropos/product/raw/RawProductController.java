package edu.nju.sa2022.micropos.product.raw;

import edu.nju.sa2022.micropos.models.Product;
import edu.nju.sa2022.micropos.services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/raw/products")
public class RawProductController {

    private final ProductService productService;

    public RawProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("{productId}")
    public Mono<Product> getProduct(@PathVariable String productId) {
        return productService.findProduct(productId)
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND)));
    }

}
