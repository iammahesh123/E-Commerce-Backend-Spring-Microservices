package org.example.paymentservice.service;

import org.example.paymentservice.dtos.PaymentDTO;

import java.util.List;

public interface PaymentService {
    List<PaymentDTO> findAll();
    PaymentDTO findById(final Integer paymentId);
    PaymentDTO save(final PaymentDTO paymentDto);
    PaymentDTO update(final PaymentDTO paymentDto);
    void deleteById(final Integer paymentId);

}
