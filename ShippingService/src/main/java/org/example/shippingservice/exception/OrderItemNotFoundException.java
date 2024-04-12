package org.example.shippingservice.exception;

public class OrderItemNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public OrderItemNotFoundException() {
        super();
    }

    public OrderItemNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public OrderItemNotFoundException(String message) {
        super(message);
    }

    public OrderItemNotFoundException(Throwable cause) {
        super(cause);
    }



}
