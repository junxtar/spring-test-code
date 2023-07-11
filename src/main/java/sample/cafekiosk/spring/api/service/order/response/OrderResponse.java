package sample.cafekiosk.spring.api.service.order.response;


import sample.cafekiosk.spring.api.service.product.response.ProductResponse;

import java.time.LocalDateTime;
import java.util.List;

public class OrderResponse {

    private Long id;
    private int totalPrice;
    private LocalDateTime resisterDateTime;
    private List<ProductResponse> products;
}
