package org.example.proxyclient.bussiness.payment.controller;

import lombok.RequiredArgsConstructor;
import org.example.proxyclient.bussiness.payment.model.PaymentDTO;
import org.example.proxyclient.bussiness.payment.model.response.PaymentPaymentServiceDtoCollectionResponse;
import org.example.proxyclient.bussiness.payment.service.PaymentClientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentClientService paymentClientService;

    @GetMapping
    public ResponseEntity<PaymentPaymentServiceDtoCollectionResponse> findAll() {
        return ResponseEntity.ok(this.paymentClientService.findAll().getBody());
    }

    @GetMapping("/{paymentId}")
    public ResponseEntity<PaymentDTO> findById(@PathVariable("paymentId") final String paymentId) {
        return ResponseEntity.ok(this.paymentClientService.findById(paymentId).getBody());
    }

    @PostMapping
    public ResponseEntity<PaymentDTO> save(@RequestBody final PaymentDTO paymentDto) {
        return ResponseEntity.ok(this.paymentClientService.save(paymentDto).getBody());
    }

    @PutMapping
    public ResponseEntity<PaymentDTO> update(@RequestBody final PaymentDTO paymentDto) {
        return ResponseEntity.ok(this.paymentClientService.update(paymentDto).getBody());
    }

    @DeleteMapping("/{paymentId}")
    public ResponseEntity<Boolean> deleteById(@PathVariable("paymentId") final String paymentId) {
        return ResponseEntity.ok(this.paymentClientService.deleteById(paymentId).getBody());
    }
}
