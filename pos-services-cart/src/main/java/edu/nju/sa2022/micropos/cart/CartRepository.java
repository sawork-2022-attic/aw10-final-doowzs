package edu.nju.sa2022.micropos.cart;

import edu.nju.sa2022.micropos.models.Cart;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface CartRepository extends ReactiveMongoRepository<Cart, String> {
}
