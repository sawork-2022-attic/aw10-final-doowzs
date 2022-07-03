package edu.nju.sa2022.micropos.delivery;

import edu.nju.sa2022.micropos.dtos.DeliveryDto;
import edu.nju.sa2022.micropos.services.DeliveryService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/deliveries")
public class DeliveryController {

    private final DeliveryService deliveryService;
    private final DeliveryMapper deliveryMapper;

    public DeliveryController(DeliveryService deliveryService,
                              DeliveryMapper deliveryMapper) {
        this.deliveryService = deliveryService;
        this.deliveryMapper = deliveryMapper;
    }

    @GetMapping("")
    public Flux<DeliveryDto> listDeliveriesByOrderId(@RequestParam String orderId) {
        return deliveryService.findDeliveriesByOrderId(orderId)
                .map(deliveryMapper::toDeliveryDto);
    }

    @GetMapping("{deliveryId}")
    public Mono<DeliveryDto> findDelivery(@PathVariable String deliveryId) {
        return deliveryService.findDelivery(deliveryId)
                .map(deliveryMapper::toDeliveryDto)
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND)));
    }

}
