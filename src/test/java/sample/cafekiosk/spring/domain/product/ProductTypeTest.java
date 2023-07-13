package sample.cafekiosk.spring.domain.product;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ProductTypeTest {

    @DisplayName("재고 차감 해당 상품이 아닌지 확인 한다.")
    @Test
    void containsStockTypeIsFalseCheck() {
        // given
        ProductType productType = ProductType.HANDMADE;

        // when
        boolean result = ProductType.containsStockType(productType);

        // then
        assertThat(result).isFalse();
    }

    @DisplayName("재고 차감 해당 상품인지 확인 한다.")
    @Test
    void containsStockTypeIsTrueCheck() {
        // given
        ProductType bottle = ProductType.BOTTLE;
        ProductType bakery = ProductType.BAKERY;

        // when
        boolean result1 = ProductType.containsStockType(bottle);
        boolean result2 = ProductType.containsStockType(bakery);

        // then
        assertThat(result1).isTrue();
        assertThat(result2).isTrue();
    }

}