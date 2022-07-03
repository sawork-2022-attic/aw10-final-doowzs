package edu.nju.sa2022.micropos.order;

import edu.nju.sa2022.micropos.models.Order;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface OrderRepository extends ReactiveMongoRepository<Order, String> {

    Flux<Order> findAllByUserId(String userId);

}
