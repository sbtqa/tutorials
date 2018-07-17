package ru.sbtqa.tutorials.advanced.mockito;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import ru.sbtqa.tutorials.advanced.mockito.services.PromotionService;

import static java.math.BigDecimal.valueOf;
import static java.util.Collections.emptyList;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Tests for {@code Order} implementation. Uses an state-based verification approach.
 */
class OrderTestStateVerification {
    private BankAccount account;
    private Order order;

    @BeforeEach
    void setUp() {
        // Note that we use real implementation of {@code BankAccount} - PaymentBankAccount class.
        // We should NOT mix PaymentBankAccount test with Order test since they are different units.
        account = new PaymentBankAccount();
        // Deposits 100 to the bank account.
        account.deposit(valueOf(100));
        // Real Order to be tested.
        order = new Order(new PromotionServiceSpy());
    }

    @Test
    void testSucceededIfEnoughFunds() throws InsufficientFundsException {
        Item cake = new Item("Cake", valueOf(70));

        order.buyItem(cake, account);

        assertAll(
                () -> assertEquals(valueOf(30), account.getBalance(), "Баланс уменьшился на сумму покупки"),
                () -> assertTrue(order.getItems().contains(cake), "Товар добавлен в список приобретённых")
        );
    }

    @Test
    void testFailedIfInsufficientFunds() {
        Item car = new Item("Car", valueOf(1_000_000));
        assertAll(
                () -> assertThrows(InsufficientFundsException.class,
                        () -> order.buyItem(car, account), "Брошено исключение InsufficientFundsException, так как средств на счёте недостаточно для покупки машины"),
                () -> assertAll("Покупка не совершена, состояние объектов не изменилось",
                        () -> assertEquals(valueOf(100), account.getBalance()),
                        () -> assertTrue(order.getItems().isEmpty())
                )
        );
    }
}

/**
 * Tests for {@code Order} implementation. Uses an behaviour-based verification approach.
 */
class OrderTestBehaviourVerification {
    /**
     * {@code BankAccount} mock.
     */
    private BankAccount account = mock(BankAccount.class);

    /**
     * {@code PromotionService} sample spy.
     */
    private PromotionServiceSpy promotionServiceSpy = new PromotionServiceSpy();

    /**
     * {@code Order} real instance.
     */
    private Order order = new Order(promotionServiceSpy);

    @Test
    void testSucceedIfEnoughFunds() throws InsufficientFundsException {
        Item cake = new Item("Cake", valueOf(70));

        order.buyItem(cake, account);

        assertAll(
                () -> verify(account).withdraw(cake.getPrice()),
                () -> assertTrue(order.getItems().contains(cake), "Товар добавлен в список приобретённых"),
                () -> assertTrue(promotionServiceSpy.getGiftsByItemCalled, "Метод getGiftsByItem вызван")
        );
    }

    @Test
    void testFailIfInsufficientFunds() throws InsufficientFundsException {
        Item car = new Item("Car", valueOf(1_000_000));
        doThrow(InsufficientFundsException.class).when(account).withdraw(car.getPrice());

        assertAll(
                () -> assertThrows(InsufficientFundsException.class, () -> order.buyItem(car, account)
                        , "Брошено исключение InsufficientFundsException, так как средств на счёте недостаточно для покупки машины"),
                () -> assertAll(
                        () -> verify(account).withdraw(car.getPrice()),
                        () -> assertTrue(order.getItems().isEmpty(), "Список приобретённых товаров остался пустым")
                )
        );
    }
}

/**
 * Sample spy implementation of the {@code PromotionService}.
 */
class PromotionServiceSpy implements PromotionService {
    /**
     * True if method getGiftsByItem was called.
     */
    boolean getGiftsByItemCalled = false;

    @Override
    public List<Item> getGiftsByItem(Item item) {
        getGiftsByItemCalled = true;
        return emptyList();
    }
}