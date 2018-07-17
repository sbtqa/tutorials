package ru.sbtqa.tutorials.advanced.mockito;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ru.sbtqa.tutorials.advanced.mockito.services.PromotionService;

import static java.math.BigDecimal.valueOf;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/*
Использовать или нет BeforeEach - решать вам. Если вам нужен отдельный метод для инициализации - делайте.
Иначе - лучше использовать нестатические члены класса (в тестах важна изолированность и повторяемость).
 */

/**
 * Tests for {@code Order} implementation.
 */
class OrderTest_Mockito_mock_BeforeEach {
    /**
     * {@code BankAccount} mock.
     */
    private BankAccount account;

    /**
     * {@code PromotionService} mock.
     */
    private PromotionService promotionService;

    /**
     * {@code Order} real instance.
     */
    private Order order;

    @BeforeEach
    void setUp() {
        account = mock(BankAccount.class);
        promotionService = mock(PromotionService.class);
        order = new Order(promotionService);
    }

    @Test
    void testSucceedIfEnoughFunds() throws InsufficientFundsException {
        Item cake = new Item("Cake", valueOf(70));

        order.buyItem(cake, account);

        assertAll(
                () -> verify(account).withdraw(cake.getPrice()),
                () -> verify(promotionService).getGiftsByItem(cake),
                () -> assertTrue(order.getItems().contains(cake), "Товар добавлен в список приобретённых")
        );
    }
}
