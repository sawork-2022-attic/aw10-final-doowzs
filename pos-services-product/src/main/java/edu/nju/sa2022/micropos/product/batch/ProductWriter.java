package edu.nju.sa2022.micropos.product.batch;

import edu.nju.sa2022.micropos.models.Product;
import edu.nju.sa2022.micropos.services.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemWriter;

import java.util.List;

@Slf4j
public class ProductWriter implements ItemWriter<Product> {

    private final ProductService productService;

    public ProductWriter(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public void write(List<? extends Product> products) throws Exception {
        for (Product product : products) {
            log.info(String.format("Add product %s", product.getId()));
            productService.saveProduct(product).block();
        }
    }

}
