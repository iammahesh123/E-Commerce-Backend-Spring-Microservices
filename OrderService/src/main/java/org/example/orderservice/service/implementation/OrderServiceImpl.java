package org.example.orderservice.service.implementation;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.orderservice.dtos.OrderDTO;
import org.example.orderservice.repository.CartRepository;
import org.example.orderservice.repository.OrderRepository;
import org.example.orderservice.service.OrderService;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;


@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private OrderRepository orderRepository;
    private RestTemplate restTemplate;


    @Override
    public List<OrderDTO> findAll() {
        return null;
    }

    @Override
    public OrderDTO findById(Integer orderId) {
        return null;
    }

    @Override
    public OrderDTO save(OrderDTO orderDTO) {
        return null;
    }

    @Override
    public OrderDTO update(OrderDTO orderDTO) {
        return null;
    }

    @Override
    public OrderDTO update(Integer orderId, OrderDTO orderDTO) {
        return null;
    }

    @Override
    public void deleteById(Integer orderId) {

    }
}
