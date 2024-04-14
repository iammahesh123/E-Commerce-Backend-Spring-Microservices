package org.example.proxyclient.bussiness.orderItem.controller;

import lombok.RequiredArgsConstructor;
import org.example.proxyclient.bussiness.orderItem.model.OrderItemDTO;
import org.example.proxyclient.bussiness.orderItem.model.OrderItemId;
import org.example.proxyclient.bussiness.orderItem.model.response.OrderItemOrderItemServiceDtoCollectionResponse;
import org.example.proxyclient.bussiness.orderItem.service.OrderItemClientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/shippings")
@RequiredArgsConstructor
public class OrderItemController {

    private final OrderItemClientService orderItemClientService;

    @GetMapping
    public ResponseEntity<OrderItemOrderItemServiceDtoCollectionResponse> findAll() {
        return ResponseEntity.ok(this.orderItemClientService.findAll().getBody());
    }

    @GetMapping("/{orderId}/{productId}")
    public ResponseEntity<OrderItemDTO> findById(
            @PathVariable("orderId") final String orderId,
            @PathVariable("productId") final String productId) {
        return ResponseEntity.ok(this.orderItemClientService.findById(new OrderItemId(Integer.parseInt(productId),
                Integer.parseInt(orderId))).getBody());
    }

    @GetMapping("/find")
    public ResponseEntity<OrderItemDTO> findById(@RequestBody final OrderItemId orderItemId) {
        return ResponseEntity.ok(this.orderItemClientService.findById(orderItemId).getBody());
    }

    @PostMapping
    public ResponseEntity<OrderItemDTO> save(@RequestBody final OrderItemDTO orderItemDto) {
        return ResponseEntity.ok(this.orderItemClientService.save(orderItemDto).getBody());
    }

    @PutMapping
    public ResponseEntity<OrderItemDTO> update(@RequestBody final OrderItemDTO orderItemDto) {
        return ResponseEntity.ok(this.orderItemClientService.update(orderItemDto).getBody());
    }

    @DeleteMapping("/{orderId}/{productId}")
    public ResponseEntity<Boolean> deleteById(
            @PathVariable("orderId") final String orderId,
            @PathVariable("productId") final String productId) {
        this.orderItemClientService.deleteById(new OrderItemId(Integer.parseInt(orderId),
                Integer.parseInt(productId))).getBody();
        return ResponseEntity.ok(true);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Boolean> deleteById(@RequestBody final OrderItemId orderItemId) {
        this.orderItemClientService.deleteById(orderItemId).getBody();
        return ResponseEntity.ok(true);
    }



}
