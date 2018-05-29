package ru.sbtqa.tutorials.advanced.mockito;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import ru.sbtqa.tutorials.advanced.mockito.services.PromotionService;

import static java.math.BigDecimal.valueOf;
import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

/*
   BDD стиль приобрёл огромную популярность в мире в последнее время. В этом стиле пишут свой код
   программисты, тестировщики, оформляют требования аналитики. Мокито имеет довольно хороший свой
   DSL (domain specific language). В какой момент авторы мокито решили его адаптировать к понятным
   практикам BDD терминам given-when-then и добавили в язык соответствующие выражения. По факту, они
   являются лишь синонимами к уже имеющимся конструкциям мокитовского DSL. Давайте сделаем пример
   с использованием новых языковых конструкций. Например, перепишем на given-when-then тест
   testCandyForAnyItem.
 */

/**
 * Tests for {@code Order} implementation.
 */
@ExtendWith(MockitoExtension.class)
class OrderTest {
    /**
     * {@code BankAccount} mock.
     */
    @Mock
    private BankAccount account;

    /**
     * {@code PromotionService} mock.
     */
    @Mock
    private PromotionService promotionService;

    /**
     * {@code Order} real instance.
     */
    private Order order;

    @BeforeEach
    void setUp() {
        order = new Order(promotionService);
    }

    @Test
    void testCandyForAnyItemBddStyle() throws InsufficientFundsException {
        // Стандартным mockito'вским when-verify конструкциям есть алиасы для работы в BDD стиле.
        // Перепишем тест testCandyForAnyItem с использованием такого стиля.

        // Given
        Item cake = new Item("Cake", valueOf(70));
        Item candy = new Item("Candy", valueOf(0));
        given(promotionService.getGiftsByItem(any())).willReturn(singletonList(candy));

        // When
        order.buyItem(cake, account);

        // Then
        List<Item> items = order.getItems();
        assertAll(
                () -> then(promotionService).should().getGiftsByItem(cake),
                () -> assertTrue(items.contains(cake), "Пирожное добавлено в список приобретённых товаров"),
                () -> assertTrue(items.contains(candy), "Подарок добавлен в список приобретённых товаров")
        );

        // От себя замечу - BDDMockito - это хорошо, но большинство программистов уже привыкли к стандартному DSL от mockito
        // и продолжают использовать именно его.
    }

}
