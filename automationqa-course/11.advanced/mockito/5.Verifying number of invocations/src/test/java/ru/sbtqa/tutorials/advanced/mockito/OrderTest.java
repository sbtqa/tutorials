package ru.sbtqa.tutorials.advanced.mockito;

import org.junit.jupiter.api.Test;
import org.mockito.InOrder;

import ru.sbtqa.tutorials.advanced.mockito.services.PromotionService;

import static java.math.BigDecimal.valueOf;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.atMost;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;

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
    void testFailIfInsufficientFunds() throws InsufficientFundsException {
        Item car = new Item("Car", valueOf(1_000_000));
        // Если будет вызван метод withdraw с любым значением аргумента, то бросить исключение InsufficientFundsException
        doThrow(InsufficientFundsException.class).when(account).withdraw(any());

        assertThrows(InsufficientFundsException.class, () -> order.buyItem(car, account));

        // Mockito.times(1) писать не обязательно - это значение по умолчанию
        verify(account, times(1)).withdraw(car.getPrice());
        // Проверка на то, что если операция withdraw была неуспешной (как в этом тесте), то метод getGiftsByItem не будет вызванё
        verify(promotionService, never()).getGiftsByItem(any());
        // Можно использовать Mockito.verifyZeroInteractions - проверит, что не было вызовов методов мока promotionService
        // Не стоит увлекаться и писать verifyNoMoreInteractions везде.
        // Лучше тест сохранить чистым и красивым, в случае необходимости можно использовать never.
        verifyZeroInteractions(promotionService); // == Mockito.verifyNoMoreInteractions(account);
        assertTrue(order.getItems().isEmpty());
    }

    @Test
    void testMultipleCalls() throws InsufficientFundsException {
        Item cake = new Item("Cake", valueOf(70));
        Item cola = new Item("Cola", valueOf(50));

        order.buyItem(cake, account);
        order.buyItem(cola, account);
        order.buyItem(cola, account);

        // buyItem с аргументом cake вызывался 1 раз
        verify(promotionService).getGiftsByItem(cake);
        // buyItem с аргументом cola вызывался 2 раза
        verify(promotionService, times(2)).getGiftsByItem(cola);
        // Исключительно для примера
        verify(promotionService, atLeastOnce()).getGiftsByItem(any());
        verify(promotionService, atLeast(2)).getGiftsByItem(any());
        verify(promotionService, atMost(3)).getGiftsByItem(any());
    }

    @Test
    void testInOrder() throws InsufficientFundsException {
        // Бывают случаи, когда недостаточно проверять только факт вызова метода мока с нужным аргументом.
        // Дополнительно нужно проверять порядок вызова методов. Например, в примере с Order'ом, getGiftsByItem
        // должен вызываться только после withdraw.

        Item car = new Item("Car", valueOf(1_000_000));

        order.buyItem(car, account);

        InOrder inOrder = inOrder(account, promotionService);
        inOrder.verify(account).withdraw(car.getPrice());
        inOrder.verify(promotionService).getGiftsByItem(car);

        // inOrder можно и для одного мока использовать - т.е. проверять порядок вызовов методов в одном моке.
    }
}
