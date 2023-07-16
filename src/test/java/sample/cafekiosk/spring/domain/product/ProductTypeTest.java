package sample.cafekiosk.spring.domain.product;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static sample.cafekiosk.spring.domain.product.ProductType.BOTTLE;

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
        ProductType bottle = BOTTLE;
        ProductType bakery = ProductType.BAKERY;

        // when
        boolean result1 = ProductType.containsStockType(bottle);
        boolean result2 = ProductType.containsStockType(bakery);

        // then
        assertThat(result1).isTrue();
        assertThat(result2).isTrue();
    }

    @DisplayName("재고 타입을 확인한다.")
    @ParameterizedTest // DisplayName 같은 경우도 custom 가능 -> 공식 문서 참고
    @CsvSource({"HANDMADE,false", "BOTTLE, true", "BAKERY, true"})
    void containsStockTypeParameterizedTest(ProductType productType, boolean expected) {
        // when
        boolean actual = ProductType.containsStockType(productType);

        // then
        assertThat(actual).isEqualTo(expected);
    }



}