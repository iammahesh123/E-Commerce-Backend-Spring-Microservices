package org.example.shippingservice.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.shippingservice.domain.entity.OrderItemId;
import org.example.shippingservice.domain.dtos.OrderItemsDTO;
import org.example.shippingservice.response.DTOCollectionResponse;
import org.example.shippingservice.service.OrderItemService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/shippings")
@Slf4j
@RequiredArgsConstructor
public class OrderItemsController {
    private final OrderItemService orderItemService;

    @GetMapping
    public ResponseEntity<DTOCollectionResponse<OrderItemsDTO>> findAll() {
        log.info("*** OrderItemDto List, controller; fetch all orderItems *");
        return ResponseEntity.ok(new DTOCollectionResponse<>(this.orderItemService.findAll()));
    }

    @GetMapping("/{orderId}/{productId}")
    public ResponseEntity<OrderItemsDTO> findById(
            @PathVariable("orderId") final String orderId,
            @PathVariable("productId") final String productId) {
        log.info("*** OrderItemDto, resource; fetch orderItem by id *");
        return ResponseEntity.ok(this.orderItemService.findById(
                new OrderItemId(Integer.parseInt(orderId), Integer.parseInt(productId))));
    }

    @GetMapping("/find")
    public ResponseEntity<OrderItemsDTO> findById(
            @RequestBody
            @NotNull(message = "Input must not be NULL")
            @Valid final OrderItemId orderItemId) {
        log.info("*** OrderItemDto, resource; fetch orderItem by id *");
        return ResponseEntity.ok(this.orderItemService.findById(orderItemId));
    }

    @PostMapping
    public ResponseEntity<OrderItemsDTO> save(
            @RequestBody
            @NotNull(message = "Input must not be NULL")
            @Valid final OrderItemsDTO orderItemDto) {
        log.info("*** OrderItemDto, resource; save orderItem *");
        return ResponseEntity.ok(this.orderItemService.save(orderItemDto));
    }

    @PutMapping
    public ResponseEntity<OrderItemsDTO> update(
            @RequestBody
            @NotNull(message = "Input must not be NULL")
            @Valid final OrderItemsDTO orderItemDto) {
        log.info("*** OrderItemDto, resource; update orderItem *");
        return ResponseEntity.ok(this.orderItemService.update(orderItemDto));
    }

    @DeleteMapping("/{orderId}/{productId}")
    public ResponseEntity<Boolean> deleteById(
            @PathVariable("orderId") final String orderId,
            @PathVariable("productId") final String productId) {
        log.info("*** Boolean, resource; delete orderItem by id *");
        this.orderItemService.deleteById(new OrderItemId(Integer.parseInt(orderId), Integer.parseInt(productId)));
        return ResponseEntity.ok(true);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Boolean> deleteById(
            @RequestBody
            @NotNull(message = "Input must not be NULL")
            @Valid final OrderItemId orderItemId) {
        log.info("*** Boolean, resource; delete orderItem by id *");
        this.orderItemService.deleteById(orderItemId);
        return ResponseEntity.ok(true);
    }

}
