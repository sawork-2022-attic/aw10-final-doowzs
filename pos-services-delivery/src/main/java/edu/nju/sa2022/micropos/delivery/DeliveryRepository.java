package edu.nju.sa2022.micropos.delivery;

import edu.nju.sa2022.micropos.models.Delivery;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

import java.util.List;

public interface DeliveryRepository extends ReactiveMongoRepository<Delivery, String> {

    Flux<Delivery> findAllByOrderId(String orderId);

}
