package ru.sbtqa.tutorials.advanced.mockito;

import org.junit.jupiter.api.Test;

import ru.sbtqa.tutorials.advanced.mockito.services.PromotionService;

import static java.math.BigDecimal.valueOf;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/*
JUnit создаёт для каждого тестового метода новый инстанс класса с тестами.
Это не ошибка JUnit, а продуманное решение.
Основная причина почему это так - необходимость изолированности тестов.
См. https://martinfowler.com/bliki/JunitNewInstance.html
 */

/**
 * Tests for {@code Order} implementation.
 */
class OrderTest_Mockito_mock_class_members {
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
        Item cake = new Item("Cake", valueOf(70));

        order.buyItem(cake, account);

        assertAll(
                () -> verify(account).withdraw(cake.getPrice()),
                () -> verify(promotionService).getGiftsByItem(cake),
                () -> assertTrue(order.getItems().contains(cake), "Товар добавлен в список приобретённых")
        );
    }
}
