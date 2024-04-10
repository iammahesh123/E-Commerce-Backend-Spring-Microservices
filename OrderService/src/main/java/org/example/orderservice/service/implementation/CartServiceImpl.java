package org.example.orderservice.service.implementation;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.orderservice.constant.AppConstant;
import org.example.orderservice.dtos.CartDTO;
import org.example.orderservice.dtos.UserDTO;
import org.example.orderservice.exception.CartNotFoundException;
import org.example.orderservice.helper.CartMapping;
import org.example.orderservice.repository.CartRepository;
import org.example.orderservice.service.CartService;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private CartRepository cartRepository;
    private final RestTemplate restTemplate;

    @Override
    public List<CartDTO> findAll() {

        log.info("CartServiceImplementation, find all the CartDTOS");
        return this.cartRepository.findAll()
                .stream()
                .map(CartMapping::map)
                .map(cart -> {
                    // Capture restTemplate in a local variable
                    cart.setUserDTO(this.restTemplate.getForObject(
                            AppConstant.DiscoveredDomainsApi.USER_SERVICE_API_URL + "/" + cart.getUserDTO().getUserId(),
                            UserDTO.class));
                    return cart;
                })
                .distinct()
                .collect(Collectors.toUnmodifiableList());
    }


    @Override
    public CartDTO findById(Integer cartId) {
        log.info("CartServiceImplementation,Find the CartDTo by using cart Id");
        return this.cartRepository.findById(cartId)
                .map(CartMapping::map)
                .map(cart -> {
                    cart.setUserDTO(this.restTemplate.getForObject(AppConstant.DiscoveredDomainsApi
                            .USER_SERVICE_API_URL + "/" + cart.getUserDTO().getUserId(), UserDTO.class));
                    return cart;

                })
                .orElseThrow(() -> new CartNotFoundException(String.format("Cart with id: %d is not found", cartId)));

    }

    @Override
    public CartDTO save(CartDTO cartDTO) {
        log.info("CartServiceImplementation, Save the cartDTOs");
        return CartMapping.map(this.cartRepository
                .save(CartMapping.map(cartDTO)));
    }

    @Override
    public CartDTO update(CartDTO cartDTO) {
        log.info("CartServiceImplementation, Update the CartDTO");
        return CartMapping.map(this.cartRepository
                .save(CartMapping.map(cartDTO)));
    }

    @Override
    public CartDTO update(Integer cartId, CartDTO cartDTO) {
        log.info("CartServiceImplementation, Update the cartDTO using cartID");
        return CartMapping.map(this.cartRepository
                .save(CartMapping.map(findById(cartId))));
    }

    @Override
    public void deleteById(Integer cartId) {
        log.info("CartServiceImplementaion, Delete the cartDTO using cartId");
        this.cartRepository.deleteById(cartId);

    }
}
