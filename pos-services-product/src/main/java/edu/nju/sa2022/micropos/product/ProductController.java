package edu.nju.sa2022.micropos.product;

import edu.nju.sa2022.micropos.dtos.ProductDto;
import edu.nju.sa2022.micropos.services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductMapper productMapper;
    private final ProductService productService;

    public ProductController(ProductService productService, ProductMapper productMapper) {
        this.productMapper = productMapper;
        this.productService = productService;
    }

    @GetMapping("")
    public Flux<ProductDto> listProducts() {
        return productService.listProducts()
                .map(productMapper::toProductDto);
    }

    @GetMapping("{productId}")
    public Mono<ProductDto> showProductById(@PathVariable String productId) {
        return productService.findProduct(productId)
                .map(productMapper::toProductDto)
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND)));
    }

}
