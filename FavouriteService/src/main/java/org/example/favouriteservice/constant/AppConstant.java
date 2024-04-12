package org.example.favouriteservice.constant;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public abstract class AppConstant {

    public static final String LOCAL_DATE_FORMAT = "dd-MM-yyyy";
    public static final String LOCAL_DATE_TIME_FORMAT = "dd-MM-yyyy__HH:mm:ss:SSSSSS";
    public static final String ZONED_DATE_TIME_FORMAT = "dd-MM-yyyy__HH:mm:ss:SSSSSS";
    public static final String INSTANT_FORMAT = "dd-MM-yyyy__HH:mm:ss:SSSSSS";

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public abstract class DiscoveredDomainsApi {

        public static final String USER_SERVICE_HOST = "http://UserService/user-service";
        public static final String USER_SERVICE_API_URL = "http://UserService/user-service/api/users";

        public static final String PRODUCT_SERVICE_HOST = "http://ProductService/product-service";
        public static final String PRODUCT_SERVICE_API_URL = "http://ProductService/product-service/api/products";

        public static final String ORDER_SERVICE_HOST = "http://OrderService/order-service";
        public static final String ORDER_SERVICE_API_URL = "http://OrderService/order-service/api/orders";

        public static final String FAVOURITE_SERVICE_HOST = "http://FavouriteService/favourite-service";
        public static final String FAVOURITE_SERVICE_API_URL = "http://FavouriteService/favourite-service/api/favourites";

        //  public static final String PAYMENT_SERVICE_HOST = "http://PAYMENT-SERVICE/payment-service";
        //  public static final String PAYMENT_SERVICE_API_URL = "http://PAYMENT-SERVICE/payment-service/api/payments";

        public static final String SHIPPING_SERVICE_HOST = "http://ShippingService/shipping-service";
        public static final String SHIPPING_SERVICE_API_URL = "http://ShippingService/shipping-service/api/shippings";

    }



}
