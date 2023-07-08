package sample.cafekiosk.spring.domain.product;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public enum ProductSellingStatus {

    SELLING("판매중"),
    HOLD("보류중"),
    STOP_SELLING("판매 정지");

    private final String text;

    public static List<ProductSellingStatus> forDisplay() {
        return List.of(SELLING, HOLD);
    }
}
