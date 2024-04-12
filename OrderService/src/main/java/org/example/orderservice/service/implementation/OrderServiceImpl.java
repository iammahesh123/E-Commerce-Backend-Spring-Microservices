package org.example.orderservice.service.implementation;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.orderservice.dtos.OrderDTO;
import org.example.orderservice.exception.OrderNotFoundException;
import org.example.orderservice.helper.OrderMapping;
import org.example.orderservice.repository.CartRepository;
import org.example.orderservice.repository.OrderRepository;
import org.example.orderservice.service.OrderService;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;


@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private OrderRepository orderRepository;
    private RestTemplate restTemplate;


    @Override
    public List<OrderDTO> findAll()  {
        log.info("*** OrderDto List, service; fetch all orders *");
        return this.orderRepository.findAll()
                .stream()
                .map(OrderMapping::map)
                .distinct()
                .collect(Collectors.toUnmodifiableList());
    }

    @Override
    public OrderDTO findById(Integer orderId) {
        log.info("*** OrderDto, service; fetch order by id *");
        return this.orderRepository.findById(orderId)
                .map(OrderMapping::map)
                .orElseThrow(() -> new OrderNotFoundException(String
                        .format("Order with id: %d not found", orderId)));
    }

    @Override
    public OrderDTO save(OrderDTO orderDTO) {
        log.info("*** OrderDto, service; save order *");
        return OrderMapping.map(this.orderRepository
                .save(OrderMapping.map(orderDTO)));
    }

    @Override
    public OrderDTO update(OrderDTO orderDTO) {
        log.info("*** OrderDto, service; update order *");
        return OrderMapping.map(this.orderRepository
                .save(OrderMapping.map(orderDTO)));
    }

    @Override
    public OrderDTO update(Integer orderId, OrderDTO orderDTO) {
        log.info("*** OrderDto, service; update order with orderId *");
        return OrderMapping.map(this.orderRepository
                .save(OrderMapping.map(this.findById(orderId))));
    }

    @Override
    public void deleteById(Integer orderId) {
        log.info("*** Void, service; delete order by id *");
        this.orderRepository.delete(OrderMapping.map(this.findById(orderId)));
    }
}
