package edu.nju.sa2022.micropos.delivery;

import edu.nju.sa2022.micropos.models.Delivery;
import edu.nju.sa2022.micropos.services.DeliveryService;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
public class DeliveryServiceImpl implements DeliveryService {

    private final DeliveryRepository deliveryRepository;

    public DeliveryServiceImpl(DeliveryRepository deliveryRepository) {
        this.deliveryRepository = deliveryRepository;
    }

    @Override
    public Flux<Delivery> findDeliveriesByOrderId(String orderId) {
        return deliveryRepository.findAllByOrderId(orderId);
    }

    @Override
    public Mono<Delivery> findDelivery(String deliveryId) {
        return deliveryRepository.findById(deliveryId);
    }

    @Override
    public Mono<Delivery> createDelivery(Delivery delivery) {
        return deliveryRepository.save(delivery)
                .doOnNext((d) -> log.info(String.format("Order delivered: %s", d.getOrderId())));
    }

}
