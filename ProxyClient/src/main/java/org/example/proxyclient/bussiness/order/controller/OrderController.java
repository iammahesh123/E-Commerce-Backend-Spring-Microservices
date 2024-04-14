package org.example.proxyclient.bussiness.order.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.example.proxyclient.bussiness.order.model.OrderDTO;
import org.example.proxyclient.bussiness.order.model.response.OrderOrderServiceDtoCollectionResponse;
import org.example.proxyclient.bussiness.order.service.OrderClientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderClientService orderClientService;

    @GetMapping
    public ResponseEntity<OrderOrderServiceDtoCollectionResponse> findAll() {
        return ResponseEntity.ok(this.orderClientService.findAll().getBody());
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderDTO> findById(
            @PathVariable("orderId")
            @NotBlank(message = "Input must not be blank!")
            @Valid final String orderId) {
        return ResponseEntity.ok(this.orderClientService.findById(orderId).getBody());
    }

    @PostMapping
    public ResponseEntity<OrderDTO> save(
            @RequestBody
            @NotNull(message = "Input must not be NULL!")
            @Valid final OrderDTO orderDto) {
        return ResponseEntity.ok(this.orderClientService.save(orderDto).getBody());
    }

    @PutMapping
    public ResponseEntity<OrderDTO> update(
            @RequestBody
            @NotNull(message = "Input must not be NULL!")
            @Valid final OrderDTO orderDto) {
        return ResponseEntity.ok(this.orderClientService.update(orderDto).getBody());
    }

    @PutMapping("/{orderId}")
    public ResponseEntity<OrderDTO> update(
            @PathVariable("orderId")
            @NotBlank(message = "Input must not be blank!")
            @Valid final String orderId,
            @RequestBody
            @NotNull(message = "Input must not be NULL!")
            @Valid final OrderDTO orderDto) {
        return ResponseEntity.ok(this.orderClientService.update(orderId, orderDto).getBody());
    }

    @DeleteMapping("/{orderId}")
    public ResponseEntity<Boolean> deleteById(@PathVariable("orderId") final String orderId) {
        this.orderClientService.deleteById(orderId).getBody();
        return ResponseEntity.ok(true);
    }
}
