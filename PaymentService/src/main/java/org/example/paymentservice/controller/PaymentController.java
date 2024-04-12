package org.example.paymentservice.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.paymentservice.dtos.PaymentDTO;
import org.example.paymentservice.response.DTOCollectionResponse;
import org.example.paymentservice.service.PaymentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payments")
@Slf4j
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @GetMapping
    public ResponseEntity<DTOCollectionResponse<PaymentDTO>> findAll() {
        log.info("*** PaymentDto List, controller; fetch all payments *");
        return ResponseEntity.ok(new DTOCollectionResponse<>(this.paymentService.findAll()));
    }

    @GetMapping("/{paymentId}")
    public ResponseEntity<PaymentDTO> findById(
            @PathVariable("paymentId")
            @NotBlank(message = "Input must not be blank")
            @Valid final String paymentId) {
        log.info("*** PaymentDto, resource; fetch payment by id *");
        return ResponseEntity.ok(this.paymentService.findById(Integer.parseInt(paymentId)));
    }

    @PostMapping
    public ResponseEntity<PaymentDTO> save(
            @RequestBody
            @NotNull(message = "Input must not be NULL")
            @Valid final PaymentDTO paymentDto) {
        log.info("*** PaymentDto, resource; save payment *");
        return ResponseEntity.ok(this.paymentService.save(paymentDto));
    }

    @PutMapping
    public ResponseEntity<PaymentDTO> update(
            @RequestBody
            @NotNull(message = "Input must not be NULL")
            @Valid final PaymentDTO paymentDto) {
        log.info("*** PaymentDto, resource; update payment *");
        return ResponseEntity.ok(this.paymentService.update(paymentDto));
    }

    @DeleteMapping("/{paymentId}")
    public ResponseEntity<Boolean> deleteById(@PathVariable("paymentId") final String paymentId) {
        log.info("*** Boolean, resource; delete payment by id *");
        this.paymentService.deleteById(Integer.parseInt(paymentId));
        return ResponseEntity.ok(true);
    }
}
