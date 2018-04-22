package ru.sbtqa.tutorials.advanced.mockito;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.Mockito;

import java.util.List;

import ru.sbtqa.tutorials.advanced.mockito.services.PromotionService;

import static java.math.BigDecimal.valueOf;
import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

// До этого момента во всех примерах сопоставление значений переданных аргументов
// производилось в естественном для Java виде - по equals. Но это не всегда применимо.
// Например, equals не имеет реализации или он тяжеловесен. Или может нам нужна по каким-то
// причинам дополнительная гибкость в этом вопросе? Посмотрим в сторону argument matchers.
// Часто бывает, что чтобы тесты сделать красивыми, лучше реализовать equals, чем использовать
// свои argument matcher'ы.

/**
 * Tests for {@code Order} implementation.
 */
class OrderTest {
    /**
     * {@code BankAccount} mock.
     */
    private final BankAccount account = mock(BankAccount.class);

    /**
     * {@code PromotionService} mock.
     */
    private final PromotionService promotionService = mock(PromotionService.class);

    /**
     * {@code Order} real instance.
     */
    private final Order order = new Order(promotionService);

    @Test
    void testCandyForAnyItem() throws InsufficientFundsException {
        Item cake = new Item("Cake", valueOf(70));
        Item candy = new Item("Candy", valueOf(0));
        when(promotionService.getGiftsByItem(ArgumentMatchers.any())).thenReturn(singletonList(candy));

        // Обратить внимание на то, какие ещё any бывают

        order.buyItem(cake, account);

        verify(account).withdraw(cake.getPrice());
        verify(promotionService).getGiftsByItem(cake);
        List<Item> items = order.getItems();
        assertTrue(items.contains(cake));
        assertTrue(items.contains(candy));
    }

    @Test
    void testNoCandyForItemsLessThan100() throws InsufficientFundsException {
        Item cake = new Item("Cake", valueOf(70));
        Item candy = new Item("Candy", valueOf(0));
        when(promotionService.getGiftsByItem(ArgumentMatchers.argThat(
                (item) -> valueOf(100).compareTo(item.getPrice()) < 0
        ))).thenReturn(singletonList(candy));

        order.buyItem(cake, account);

        verify(account).withdraw(cake.getPrice());
        verify(promotionService).getGiftsByItem(cake);
        List<Item> items = order.getItems();
        assertTrue(items.contains(cake));
        assertFalse(items.contains(candy));
    }

    @Test
    void testCandyForItemsMoreThan100() throws InsufficientFundsException {
        Item car = new Item("Car", valueOf(1_000_000));
        Item candy = new Item("Candy", valueOf(0));
        when(promotionService.getGiftsByItem(ArgumentMatchers.argThat(
                (item) -> valueOf(100).compareTo(item.getPrice()) < 0
        ))).thenReturn(singletonList(candy));

        order.buyItem(car, account);

        verify(account).withdraw(car.getPrice());
        verify(promotionService).getGiftsByItem(car);
        List<Item> items = order.getItems();
        assertTrue(items.contains(car));
        assertTrue(items.contains(candy));
    }

    @Test
    void testArgumentMatchersOnVerify() throws InsufficientFundsException {
        Item car = new Item("Car", valueOf(1_000_000));
        Item candy = new Item("Candy", valueOf(0));
        when(promotionService.getGiftsByItem(ArgumentMatchers.argThat(
                (item) -> valueOf(100).compareTo(item.getPrice()) < 0
        ))).thenReturn(singletonList(candy));

        order.buyItem(car, account);

        // Argument matcher'ы могут использоваться и в блоках verify.
        // В verify удобно использовать argument matcher'ы, если нужна своя кастомизированная проверка,
        // с объектом с какими характеристиками вызывался метод (это может быть внутренний объект).
        verify(account).withdraw(ArgumentMatchers.any());
        verify(account).withdraw(ArgumentMatchers.argThat((price) -> valueOf(100).compareTo(price) < 0));

        // Проверит на то, что getGiftsByItem был вызван именно с тем же аргументом, что и был передан в buyItem
        // (проверка на равенство ссылок, а не equals)
        verify(promotionService).getGiftsByItem(ArgumentMatchers.same(car));
        List<Item> items = order.getItems();
        assertTrue(items.contains(car));
        assertTrue(items.contains(candy));
    }

    @Test
    void testCandyForAnyItemBddStyle() throws InsufficientFundsException {
        // Стандартным mockito'вским when-verify конструкциям есть алиасы для работы в BDD стиле
        // Перепишем тест testCandyForAnyItem с использованием такого стиля

        Item cake = new Item("Cake", valueOf(70));
        Item candy = new Item("Candy", valueOf(0));
        BDDMockito.given(promotionService.getGiftsByItem(ArgumentMatchers.any())).willReturn(singletonList(candy));

        order.buyItem(cake, account);

        BDDMockito.then(account).should().withdraw(cake.getPrice());
        BDDMockito.then(promotionService).should().getGiftsByItem(cake);
        List<Item> items = order.getItems();
        assertTrue(items.contains(cake));
        assertTrue(items.contains(candy));
    }

}
