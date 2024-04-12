package org.example.orderservice.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.orderservice.dtos.OrderDTO;
import org.example.orderservice.response.ResponseDtoCollection;
import org.example.orderservice.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {


    private final OrderService orderService;


    @PostMapping
    public ResponseEntity<OrderDTO> save(@RequestBody
                               @NotNull(message = "Input must be not null")
                               @Valid OrderDTO orderDTO) {
        log.info("OrderController, Save the OrderDTO");
        return ResponseEntity.ok(this.orderService.save(orderDTO));
    }

    @PutMapping
    public  ResponseEntity<OrderDTO> update(@RequestBody
                                            @NotNull(message = "Input must be not null")
                                            @Valid OrderDTO orderDTO) {
        log.info("OrderController, Update the OrderDTO");
        return ResponseEntity.ok(this.orderService.save(orderDTO));
    }

    @PutMapping("/{orderId}")
    public ResponseEntity<OrderDTO> update(@PathVariable("orderId")
                                           @NotBlank(message = "Input must be not null")
                                           @Valid String orderId,
                                           @RequestBody
                                           @NotNull(message = "Input must be not null")
                                           @Valid OrderDTO orderDTO) {
        log.info("OrderController, Update the OrderDTO with OrderId");
        return ResponseEntity.ok(this.orderService.update(Integer.parseInt(orderId),orderDTO));
    }

    @GetMapping
    public ResponseEntity<ResponseDtoCollection<OrderDTO>> findAll() {
        log.info("OrderController, Retrieve the OrderDTOs");
        return ResponseEntity.ok(new ResponseDtoCollection<>(this.orderService.findAll()));
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderDTO> findById(@PathVariable("orderId")
                                             @NotBlank(message = "Input must be not null")
                                             @Valid String orderId) {
        log.info("OrderController, Retrieve the OrderDTOs using the orderId");
        return ResponseEntity.ok(this.orderService.findById(Integer.parseInt(orderId)));
    }

    @DeleteMapping("/orderId")
    public ResponseEntity<Boolean> delete(@PathVariable("orderId")
                                          @NotBlank(message = "Input must be not null")
                                          @Valid String orderId) {
        log.info("OrderController, Delete the OrderDTO");
        this.orderService.deleteById(Integer.parseInt(orderId));
        return ResponseEntity.ok(true);
    }
}
