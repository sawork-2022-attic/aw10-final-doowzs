package edu.nju.sa2022.micropos.order;

import edu.nju.sa2022.micropos.dtos.OrderDto;
import edu.nju.sa2022.micropos.services.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;
    private final OrderMapper orderMapper;

    public OrderController(OrderService orderService, OrderMapper orderMapper) {
        this.orderService = orderService;
        this.orderMapper = orderMapper;
    }

    @GetMapping("")
    public Flux<OrderDto> listOrders(@RequestParam String userId) {
        return orderService.listOrders(userId)
                .map(orderMapper::toOrderDto);
    }

    @GetMapping("{orderId}")
    public Mono<OrderDto> findOrder(@PathVariable String orderId) {
        return orderService.findOrder(orderId)
                .map(orderMapper::toOrderDto)
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND)));
    }

    @PostMapping("")
    public Mono<OrderDto> createOrder(@RequestParam String userId) {
        return orderService.createOrder(userId)
                .map(orderMapper::toOrderDto);
    }

}
