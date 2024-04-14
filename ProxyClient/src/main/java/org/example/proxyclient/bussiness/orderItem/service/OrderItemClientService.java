package org.example.proxyclient.bussiness.orderItem.service;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.example.proxyclient.bussiness.orderItem.model.OrderItemDTO;
import org.example.proxyclient.bussiness.orderItem.model.OrderItemId;
import org.example.proxyclient.bussiness.orderItem.model.response.OrderItemOrderItemServiceDtoCollectionResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "SHIPPING-SERVICE", contextId = "shippingClientService", path = "/shipping-service/api/shippings")
public interface OrderItemClientService {

    @GetMapping
    ResponseEntity<OrderItemOrderItemServiceDtoCollectionResponse> findAll();

    @GetMapping("/{orderId}/{productId}")
    ResponseEntity<OrderItemDTO> findById(
            @PathVariable("orderId") final String orderId,
            @PathVariable("productId") final String productId);

    @GetMapping("/find")
    ResponseEntity<OrderItemDTO> findById(
            @RequestBody
            @NotNull(message = "Input must not be NULL")
            @Valid final OrderItemId orderItemId);

    @PostMapping
    ResponseEntity<OrderItemDTO> save(
            @RequestBody
            @NotNull(message = "Input must not be NULL")
            @Valid final OrderItemDTO orderItemDto);

    @PutMapping
    ResponseEntity<OrderItemDTO> update(
            @RequestBody
            @NotNull(message = "Input must not be NULL")
            @Valid final OrderItemDTO orderItemDto);

    @DeleteMapping("/{orderId}/{productId}")
    ResponseEntity<Boolean> deleteById(
            @PathVariable("orderId") final String orderId,
            @PathVariable("productId") final String productId);

    @DeleteMapping("/delete")
    ResponseEntity<Boolean> deleteById(
            @RequestBody
            @NotNull(message = "Input must not be NULL")
            @Valid final OrderItemId orderItemId);

}
