package org.example.proxyclient.bussiness.order.service;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.example.proxyclient.bussiness.order.model.OrderDTO;
import org.example.proxyclient.bussiness.order.model.response.OrderOrderServiceDtoCollectionResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "ORDER-SERVICE", contextId = "orderClientService", url = "http://localhost:9052/order-service/api/orders")
public interface OrderClientService {

    @GetMapping
    public ResponseEntity<OrderOrderServiceDtoCollectionResponse> findAll();

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderDTO> findById(
            @PathVariable("orderId")
            @NotBlank(message = "Input must not be blank!")
            @Valid final String orderId);

    @PostMapping
    public ResponseEntity<OrderDTO> save(
            @RequestBody
            @NotNull(message = "Input must not be NULL!")
            @Valid final OrderDTO orderDto);

    @PutMapping
    public ResponseEntity<OrderDTO> update(
            @RequestBody
            @NotNull(message = "Input must not be NULL!")
            @Valid final OrderDTO orderDto);

    @PutMapping("/{orderId}")
    public ResponseEntity<OrderDTO> update(
            @PathVariable("orderId")
            @NotBlank(message = "Input must not be blank!")
            @Valid final String orderId,
            @RequestBody
            @NotNull(message = "Input must not be NULL!")
            @Valid final OrderDTO orderDto);

    @DeleteMapping("/{orderId}")
    public ResponseEntity<Boolean> deleteById(@PathVariable("orderId") final String orderId);

}
