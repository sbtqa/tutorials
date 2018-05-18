package ru.sbtqa.tutorials.advanced.mockito;

import org.junit.jupiter.api.Test;

import java.util.List;

import ru.sbtqa.tutorials.advanced.mockito.services.PromotionService;

import static java.math.BigDecimal.valueOf;
import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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
    void testSucceedIfEnoughFunds() throws InsufficientFundsException {
        // Если вернуться к примеру с заказом, то напишем заглушку, которая всегда возвращает
        // объект candy для всех, кто купил cake.

        Item cake = new Item("Cake", valueOf(70));
        Item candy = new Item("Candy", valueOf(0));
        when(promotionService.getGiftsByItem(cake)).thenReturn(singletonList(candy));

        order.buyItem(cake, account);

        verify(account).withdraw(cake.getPrice());
        verify(promotionService).getGiftsByItem(cake);
        List<Item> items = order.getItems();
        assertTrue(items.contains(cake));
        assertTrue(items.contains(candy));
    }
}
