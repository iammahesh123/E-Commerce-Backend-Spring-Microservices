package org.example.paymentservice.helper;

import org.example.paymentservice.dtos.OrderDTO;
import org.example.paymentservice.dtos.PaymentDTO;
import org.example.paymentservice.model.Payment;

public interface PaymentMapping {
    public static PaymentDTO map(final Payment payment) {
        return PaymentDTO.builder()
                .paymentId(payment.getPaymentId())
                .isPayed(payment.getIsPayed())
                .paymentStatus(payment.getPaymentStatus())
                .orderDto(
                        OrderDTO.builder()
                                .orderId(payment.getOrderId())
                                .build())
                .build();
    }

    public static Payment map(final PaymentDTO paymentDto) {
        return Payment.builder()
                .paymentId(paymentDto.getPaymentId())
                .orderId(paymentDto.getOrderDto().getOrderId())
                .isPayed(paymentDto.getIsPayed())
                .paymentStatus(paymentDto.getPaymentStatus())
                .build();
    }

}
