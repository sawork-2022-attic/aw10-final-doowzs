package edu.nju.sa2022.micropos.services;

import edu.nju.sa2022.micropos.models.Delivery;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface DeliveryService {

    Flux<Delivery> findDeliveriesByOrderId(String orderId);

    Mono<Delivery> findDelivery(String deliveryId);

    Mono<Delivery> createDelivery(Delivery delivery);

}
