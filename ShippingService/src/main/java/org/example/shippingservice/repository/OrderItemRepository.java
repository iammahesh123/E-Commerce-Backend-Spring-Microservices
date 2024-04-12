package org.example.shippingservice.repository;


import org.example.shippingservice.domain.OrderItem;
import org.example.shippingservice.domain.OrderItemId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, OrderItemId> {

}
