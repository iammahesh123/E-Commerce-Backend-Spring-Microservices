package org.example.paymentservice.service.implementation;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.paymentservice.constant.AppConstant;
import org.example.paymentservice.dtos.OrderDTO;
import org.example.paymentservice.dtos.PaymentDTO;
import org.example.paymentservice.exception.PaymentNotFoundException;
import org.example.paymentservice.helper.PaymentMapping;
import org.example.paymentservice.repository.PaymentRepository;
import org.example.paymentservice.service.PaymentService;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final RestTemplate restTemplate;


    public List<PaymentDTO> findAll() {
        log.info("*** PaymentDto List, service; fetch all payments *");
        return this.paymentRepository.findAll()
                .stream()
                .map(PaymentMapping::map)
                .map(p -> {
                    p.setOrderDto(this.restTemplate.getForObject(AppConstant.DiscoveredDomainsApi
                            .ORDER_SERVICE_API_URL + "/" + p.getOrderDto().getOrderId(), OrderDTO.class));
                    return p;
                })
                .distinct()
                .collect(Collectors.toUnmodifiableList());
    }


    public PaymentDTO findById(final Integer paymentId) {
        log.info("*** PaymentDto, service; fetch payment by id *");
        return this.paymentRepository.findById(paymentId)
                .map(PaymentMapping::map)
                .map(p -> {
                    p.setOrderDto(this.restTemplate.getForObject(AppConstant.DiscoveredDomainsApi
                            .ORDER_SERVICE_API_URL + "/" + p.getOrderDto().getOrderId(), OrderDTO.class));
                    return p;
                })
                .orElseThrow(() -> new PaymentNotFoundException(String.format("Payment with id: %d not found", paymentId)));
    }


    public PaymentDTO save(final PaymentDTO paymentDto) {
        log.info("*** PaymentDto, service; save payment *");
        return PaymentMapping.map(this.paymentRepository
                .save(PaymentMapping.map(paymentDto)));
    }


    public PaymentDTO update(final PaymentDTO paymentDto) {
        log.info("*** PaymentDto, service; update payment *");
        return PaymentMapping.map(this.paymentRepository
                .save(PaymentMapping.map(paymentDto)));
    }


    public void deleteById(final Integer paymentId) {
        log.info("*** Void, service; delete payment by id *");
        this.paymentRepository.deleteById(paymentId);
    }


}
