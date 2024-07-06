package org.example.shippingservice.repository;


import org.example.shippingservice.domain.entity.OrderItem;
import org.example.shippingservice.domain.entity.OrderItemId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, OrderItemId> {

}
