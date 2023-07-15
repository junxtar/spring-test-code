package sample.cafekiosk.spring.api.service.order;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import sample.cafekiosk.spring.client.MailSendClient;
import sample.cafekiosk.spring.domain.history.mail.MailSendHistory;
import sample.cafekiosk.spring.domain.history.mail.MailSendHistoryRepository;
import sample.cafekiosk.spring.domain.order.Order;
import sample.cafekiosk.spring.domain.order.OrderRepository;
import sample.cafekiosk.spring.domain.orderproduct.OrderProductRepository;
import sample.cafekiosk.spring.domain.product.Product;
import sample.cafekiosk.spring.domain.product.ProductRepository;
import sample.cafekiosk.spring.domain.product.ProductType;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static sample.cafekiosk.spring.domain.order.OrderStatus.PAYMENT_COMPLETED;
import static sample.cafekiosk.spring.domain.product.ProductSellingStatus.SELLING;
import static sample.cafekiosk.spring.domain.product.ProductType.BAKERY;
import static sample.cafekiosk.spring.domain.product.ProductType.BOTTLE;
import static sample.cafekiosk.spring.domain.product.ProductType.HANDMADE;

@ActiveProfiles("test")
@SpringBootTest
class OrderStatisticsServiceTest {

   @Autowired
   private OrderStatisticsService orderStatisticsService;

   @Autowired
   private OrderRepository orderRepository;

   @Autowired
   private OrderProductRepository orderProductRepository;

   @Autowired
   private ProductRepository productRepository;

   @Autowired
   private MailSendHistoryRepository mailSendHistoryRepository;

   @MockBean
   private MailSendClient mailSendClient;

   @AfterEach
   void tearDown() {
      orderProductRepository.deleteAllInBatch();
      orderRepository.deleteAllInBatch();
      productRepository.deleteAllInBatch();
      mailSendHistoryRepository.deleteAllInBatch();
   }

   @DisplayName("결제 완료 주문들을 조회하여 매출 통계를 메일을 전송한다.")
   @Test
   void sendOrderStatisticsMail() {
       // given
      LocalDateTime yesterday = LocalDateTime.of(2023, 3, 4, 23, 59, 59);
      LocalDateTime today = LocalDateTime.of(2023, 3, 5, 0, 0);
      LocalDateTime tomorrow = LocalDateTime.of(2023, 3, 6, 0, 0);
      Product product1 = createProduct(BOTTLE, "001", 4000);
      Product product2 = createProduct(BAKERY, "002", 4500);
      Product product3 = createProduct(HANDMADE, "003", 7000);
      List<Product> products = List.of(product1, product2, product3);
      productRepository.saveAll(products);

      Order order1 = createPaymentCompletedOrder(yesterday, products);
      Order order2 = createPaymentCompletedOrder(today, products);
      Order order3 = createPaymentCompletedOrder(tomorrow, products);

      // stubbing
      when(mailSendClient.sendMail(any(String.class),any(String.class), any(String.class),any(String.class)))
              .thenReturn(true);

      // when
      boolean result = orderStatisticsService.sendOrderStatisticsMail(LocalDate.of(2023, 3, 5), "test@test.com");

      // then
      assertThat(result).isTrue();

      List<MailSendHistory> histories = mailSendHistoryRepository.findAll();
      assertThat(histories).hasSize(1)
              .extracting("content")
              .contains("총 매출 합계는 15500원입니다.");

   }

   private Order createPaymentCompletedOrder(LocalDateTime now, List<Product> products) {
      Order order = Order.builder()
              .products(products)
              .orderStatus(PAYMENT_COMPLETED)
              .registerDateTime(now)
              .build();
      return orderRepository.save(order);
   }

   private Product createProduct(ProductType type, String productNumber, int price) {
      return Product.builder()
              .type(type)
              .productNumber(productNumber)
              .price(price)
              .sellingStatus(SELLING)
              .name("Test")
              .build();
   }
}