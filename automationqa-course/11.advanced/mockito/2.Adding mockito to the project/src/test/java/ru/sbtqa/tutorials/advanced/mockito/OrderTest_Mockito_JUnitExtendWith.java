package ru.sbtqa.tutorials.advanced.mockito;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import ru.sbtqa.tutorials.advanced.mockito.services.PromotionService;

import static java.math.BigDecimal.valueOf;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;

/*
Для проектов с JUnit4 нужно использовать аннотацию RunWith или механизм правил JUnit4 (аннотация
Rule public MockitoRule mockitoRule = MockitoJUnit.rule())
 */

/**
 * Tests for {@code Order} implementation.
 */
@ExtendWith(MockitoExtension.class)
class OrderTest_Mockito_JUnitExtendWith {
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
