package org.example.proxyclient.bussiness.payment.service;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.example.proxyclient.bussiness.payment.model.PaymentDTO;
import org.example.proxyclient.bussiness.payment.model.response.PaymentPaymentServiceDtoCollectionResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "PAYMENT-SERVICE", contextId = "paymentClientService", path = "/payment-service/api/payments")
public interface PaymentClientService {

    @GetMapping
    public ResponseEntity<PaymentPaymentServiceDtoCollectionResponse> findAll();

    @GetMapping("/{paymentId}")
    public ResponseEntity<PaymentDTO> findById(
            @PathVariable("paymentId")
            @NotBlank(message = "Input must not be blank!")
            @Valid final String paymentId);

    @PostMapping
    public ResponseEntity<PaymentDTO> save(
            @RequestBody
            @NotNull(message = "Input must not be NULL!")
            @Valid final PaymentDTO paymentDto);

    @PutMapping
    public ResponseEntity<PaymentDTO> update(
            @RequestBody
            @NotNull(message = "Input must not be NULL!")
            @Valid final PaymentDTO paymentDto);

    @DeleteMapping("/{paymentId}")
    public ResponseEntity<Boolean> deleteById(@PathVariable("paymentId") final String paymentId);

}
