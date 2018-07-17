package ru.sbtqa.tutorials.advanced.mockito;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import ru.sbtqa.tutorials.advanced.mockito.services.PromotionService;

import static java.math.BigDecimal.valueOf;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.atMost;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

/*
   Одной из важных возможностей мокито является проверка числа и порядка вызовов методов мока.
   Посмотрим как такой возможностью пользоваться.
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

    /*
    Добавим очередной тестовый метод, который проверит корректность работы модуля в случае недостатка
    средств на счёте.
     */

    @Test
    void testFailIfInsufficientFunds() throws InsufficientFundsException {
        Item car = new Item("Car", valueOf(1_000_000));
        doThrow(InsufficientFundsException.class).when(account).withdraw(car.getPrice());

        assertAll(
                () -> assertThrows(InsufficientFundsException.class, () -> order.buyItem(car, account)
                        , "Брошено исключение InsufficientFundsException, так как средств на счёте недостаточно для покупки машины"),
                () -> assertAll(
                        // Вторым аргументом в verify можно передать число вызовов метода.
                        // times(1) писать не обязательно - это значение по умолчанию
                        () -> verify(account, times(1)).withdraw(car.getPrice()),
                        // Проверка на то, что если операция withdraw была неуспешной (как в этом тесте), то метод getGiftsByItem не будет вызван
                        () -> verify(promotionService, never()).getGiftsByItem(any()),
                        () -> assertTrue(order.getItems().isEmpty(), "Список приобретённых товаров остался пустым")
                )
        );
    }

    @Test
    void testVerifyNoMoreInteractions() throws InsufficientFundsException {
        // Можно использовать verifyZeroInteractions или verifyNoMoreInteractions - проверит,
        // что не было вызовов методов мока за исключением тех, что были уже проверены с помощью verify.
        // Не стоит увлекаться и писать verifyNoMoreInteractions везде. Лучше тест сохранить чистым и красивым,
        // в случае необходимости можно использовать never, как мы делали в предыдущем тестовом методе.

        Item cake = new Item("Cake", valueOf(70));
        Item cola = new Item("Cola", valueOf(50));

        order.buyItem(cake, account);
        order.buyItem(cola, account);

        assertAll(
                () -> verify(promotionService).getGiftsByItem(cake),
                () -> verify(promotionService).getGiftsByItem(cola),
                () -> verifyNoMoreInteractions(promotionService) // == verifyZeroInteractions(promotionService)
        );
    }

    @Test
    void testMultipleCalls() throws InsufficientFundsException {
        // Посмотрим, какие ещё есть возможности по проверке числа вызовов на примере.

        Item cake = new Item("Cake", valueOf(70));
        Item cola = new Item("Cola", valueOf(50));

        order.buyItem(cake, account);
        order.buyItem(cola, account);
        order.buyItem(cola, account);

        assertAll(
                () -> verify(promotionService).getGiftsByItem(cake), // buyItem с аргументом cake вызывался 1 раз
                () -> verify(promotionService, times(2)).getGiftsByItem(cola), // buyItem с аргументом cola вызывался 2 раза
                () -> verify(promotionService, atLeastOnce()).getGiftsByItem(any()),
                () -> verify(promotionService, atLeast(2)).getGiftsByItem(any()),
                () -> verify(promotionService, atMost(3)).getGiftsByItem(any())
        );
    }

    @Test
    void testInOrder() throws InsufficientFundsException {
        // Бывают случаи, когда недостаточно проверять только факт вызова метода мока с нужным аргументом.
        // Дополнительно нужна проверка порядка вызова методов. Например, в примере с Order'ом, список
        // подарков должен быть запрошен только после успешного списания средств со счёта.
        // Такую проверку можно организовать при помощи мокитовского inOrder.

        Item car = new Item("Car", valueOf(1_000_000));

        order.buyItem(car, account);

        // Аргументоами inOrder являются моки порядок взаимодействия методов которых и будет проверяться.
        InOrder inOrder = inOrder(account, promotionService);
        inOrder.verify(account).withdraw(car.getPrice());
        inOrder.verify(promotionService).getGiftsByItem(car);

        // В inOrder можно добавить и verifyNoMoreInteractions, но, как я уже говорил,
        // не стоит засорять тест лишними проверками
        inOrder.verifyNoMoreInteractions();

        // inOrder можно и для одного мока использовать - т.е. проверять порядок вызовов методов в одном моке.
    }
}
