package sample.cafekiosk.learning;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;

import java.util.Collection;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class GuavaLearningTest {

   @DisplayName("주어진 개수만큼 List를 몫이 딱 떨어지게 파티셔닝 한다.")
   @Test
   void partitionLearningTest() {
      // given
      List<Integer> integers = List.of(1, 2, 3, 4, 5, 6);

      // when
      List<List<Integer>> partition = Lists.partition(integers, 3);

      // then
      assertThat(partition).hasSize(2)
              .isEqualTo(List.of(
                      List.of(1, 2, 3), List.of(4, 5, 6)
              ));
   }

   @DisplayName("주어진 개수만큼 List를 나머지가 떨어지게 파티셔닝 한다.")
   @Test
   void partitionLearningTest2() {
      // given
      List<Integer> integers = List.of(1, 2, 3, 4, 5, 6);

      // when
      List<List<Integer>> partition = Lists.partition(integers, 4);

      // then
      assertThat(partition).hasSize(2)
              .isEqualTo(List.of(
                      List.of(1, 2, 3, 4), List.of(5, 6)
              ));
   }

   @DisplayName("멀티맵을 활용해본다.")
   @Test
   void multiMapLearningTest() {
      // given
      Multimap<String, String> multiMap = ArrayListMultimap.create();
      multiMap.put("커피", "아메리카노");
      multiMap.put("커피", "카페라떼");
      multiMap.put("커피", "카푸치노");
      multiMap.put("베이커리", "크로아상");
      multiMap.put("베이커리", "식빵");
      // when
      Collection<String> coffeeMap = multiMap.get("커피");
      // then
      assertThat(coffeeMap).hasSize(3)
              .isEqualTo(List.of("아메리카노", "카페라떼", "카푸치노"
              ));
   }

   @DisplayName("멀티맵 기능 확인")
   @TestFactory
   Collection<DynamicTest> multiMapLearningTest2() {
      // given
      Multimap<String, String> multimap = ArrayListMultimap.create();
      multimap.put("커피", "아메리카노");
      multimap.put("커피", "카페라떼");
      multimap.put("커피", "카푸치노");
      multimap.put("베이커리", "크루아상");
      multimap.put("베이커리", "식빵");

      return List.of(
              DynamicTest.dynamicTest("1개 value 삭제", () -> {
                 // when
                 multimap.remove("커피", "카푸치노");

                 // then
                 Collection<String> results = multimap.get("커피");
                 assertThat(results).hasSize(2)
                         .isEqualTo(List.of("아메리카노", "카페라떼"));
              }),
              DynamicTest.dynamicTest("1개 key 삭제", () -> {
                 // when
                 multimap.removeAll("커피");

                 // then
                 Collection<String> results = multimap.get("커피");
                 assertThat(results).isEmpty();
              })
      );
   }
}
