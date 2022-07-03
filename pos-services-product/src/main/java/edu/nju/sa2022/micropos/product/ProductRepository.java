package edu.nju.sa2022.micropos.product;

import edu.nju.sa2022.micropos.models.Product;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface ProductRepository extends ReactiveMongoRepository<Product, String> {
}
