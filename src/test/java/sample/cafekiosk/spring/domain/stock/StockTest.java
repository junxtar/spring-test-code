package sample.cafekiosk.spring.domain.stock;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class StockTest {

    @DisplayName("재고의 수량이 제공된 수량보다 작은지 확인한다.")
    @Test
    void isQuantityLessThan() {
        // given
        Stock stock = Stock.create("001", 1);
        int quantity = 2;

        // when
        boolean result = stock.isQuantityLessThan(quantity);

        // then
        assertThat(result).isTrue();
    }

    @DisplayName("재고의 수량이 감소가 되었는지 확인한다.")
    @Test
    void reduceQuantity() {
        // given
        Stock stock = Stock.create("001", 4);
        int quantity = 2;

        // when
        stock.reduceQuantity(quantity);

        // then
        assertThat(stock.getQuantity()).isEqualTo(2);
    }

    @DisplayName("재고의 수량보다 많은 수량으로 차감 시도하는 경우 예외가 발생한다.")
    @Test
    void reduceQuantityException() {
        // given
        Stock stock = Stock.create("001", 2);
        int quantity = 3;

        // when, then
        assertThatThrownBy(() ->stock.reduceQuantity(quantity))
                .isInstanceOf(IllegalArgumentException.class)
                        .hasMessage("차감할 재고가 없습니다.");
    }

}