package org.example.shippingservice.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.shippingservice.constant.AppConstant;
import org.example.shippingservice.domain.entity.OrderItemId;
import org.example.shippingservice.domain.dtos.OrderDTO;
import org.example.shippingservice.domain.dtos.OrderItemsDTO;
import org.example.shippingservice.domain.dtos.ProductDTO;
import org.example.shippingservice.exception.OrderItemNotFoundException;
import org.example.shippingservice.mapper.OrderItemMapping;
import org.example.shippingservice.repository.OrderItemRepository;
import org.example.shippingservice.service.OrderItemService;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class OrderItemServiceImpl implements OrderItemService {
    private final OrderItemRepository orderItemRepository;
    private final RestTemplate restTemplate;


    public List<OrderItemsDTO> findAll() {
        log.info("*** OrderItemDto List, service; fetch all orderItems *");
        return this.orderItemRepository.findAll()
                .stream()
                .map(OrderItemMapping::map)
                .map(o -> {
                    o.setProductDto(this.restTemplate.getForObject(AppConstant.DiscoveredDomainsApi
                            .PRODUCT_SERVICE_API_URL + "/" + o.getProductDto().getProductId(), ProductDTO.class));
                    o.setOrderDto(this.restTemplate.getForObject(AppConstant.DiscoveredDomainsApi
                            .ORDER_SERVICE_API_URL + "/" + o.getOrderDto().getOrderId(), OrderDTO.class));
                    return o;
                })
                .distinct()
                .collect(Collectors.toUnmodifiableList());
    }


    public OrderItemsDTO findById(final OrderItemId orderItemId) {
        log.info("*** OrderItemDto, service; fetch orderItem by id *");
        return this.orderItemRepository.findById(null)
                .map(OrderItemMapping::map)
                .map(o -> {
                    o.setProductDto(this.restTemplate.getForObject(AppConstant.DiscoveredDomainsApi
                            .PRODUCT_SERVICE_API_URL + "/" + o.getProductDto().getProductId(), ProductDTO.class));
                    o.setOrderDto(this.restTemplate.getForObject(AppConstant.DiscoveredDomainsApi
                            .ORDER_SERVICE_API_URL + "/" + o.getOrderDto().getOrderId(), OrderDTO.class));
                    return o;
                })
                .orElseThrow(() -> new OrderItemNotFoundException(String.format("OrderItem with id: %s not found", orderItemId)));
    }


    public OrderItemsDTO save(final OrderItemsDTO orderItemDto) {
        log.info("*** OrderItemDto, service; save orderItem *");
        return OrderItemMapping.map(this.orderItemRepository
                .save(OrderItemMapping.map(orderItemDto)));
    }


    public OrderItemsDTO update(final OrderItemsDTO orderItemDto) {
        log.info("*** OrderItemDto, service; update orderItem *");
        return OrderItemMapping.map(this.orderItemRepository
                .save(OrderItemMapping.map(orderItemDto)));
    }


    public void deleteById(final OrderItemId orderItemId) {
        log.info("*** Void, service; delete orderItem by id *");
        this.orderItemRepository.deleteById(orderItemId);
    }


}
