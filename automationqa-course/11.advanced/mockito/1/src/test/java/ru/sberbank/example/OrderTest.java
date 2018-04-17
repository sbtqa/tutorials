package ru.sberbank.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static java.math.BigDecimal.valueOf;
import static java.util.Collections.emptyList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 *
 */
class OrderTestStateVerification {
  private BankAccount account;
  private Order order;

  @BeforeEach
  void setUp() {
    account = new PaymentBankAccount();
    account.deposit(valueOf(100));
    order = new Order(new PromotionServiceSpy());
  }

  @Test
  void testSucceededIfEnoughFunds()
          throws InsufficientFundsException {
    Item cake = new Item("Cake", valueOf(70));
    order.buyItem(cake, account);
    assertEquals(valueOf(30), account.getBalance());
    assertTrue(order.getItems().contains(cake));
  }

  @Test
  void testFailedIfInsufficientFunds() {
    Item car = new Item("Car", valueOf(1_000_000));
    assertThrows(InsufficientFundsException.class,
            () -> order.buyItem(car, account));
    assertEquals(valueOf(100), account.getBalance());
    assertTrue(order.getItems().isEmpty());
  }
}

class OrderTestBehaviourVerification {
  private BankAccount account = mock(BankAccount.class);
  private Order order = new Order(new PromotionServiceSpy());

  @Test
  void testSucceedIfEnoughFunds()
          throws InsufficientFundsException {
    Item cake = new Item("Cake", valueOf(70));
    order.buyItem(cake, account);
    verify(account).withdraw(cake.getPrice());
    assertTrue(order.getItems().contains(cake));
  }

  @Test
  void testFailIfInsufficientFunds()
          throws InsufficientFundsException {
    Item car = new Item("Car", valueOf(1_000_000));
    doThrow(InsufficientFundsException.class)
            .when(account)
            .withdraw(car.getPrice());
    assertThrows(InsufficientFundsException.class
            , () -> order.buyItem(car, account));
    verify(account).withdraw(car.getPrice());
    assertTrue(order.getItems().isEmpty());
  }
}

class OrderTest {
  private BankAccount account = mock(BankAccount.class);
  private PromotionServiceSpy promotionServiceSpy = new PromotionServiceSpy();
  private Order order = new Order(promotionServiceSpy);

  @Test
  void testSucceedIfEnoughFunds()
          throws InsufficientFundsException {
    Item cake = new Item("Cake", valueOf(70));
    order.buyItem(cake, account);
    verify(account).withdraw(cake.getPrice());
    assertTrue(order.getItems().contains(cake));
    assertTrue(promotionServiceSpy.getGiftsByItemCalled);
  }
}

class PromotionServiceSpy implements PromotionService {
  boolean getGiftsByItemCalled = false;

  @Override
  public List<Item> getGiftsByItem(Item item) {
    getGiftsByItemCalled = true;
    return emptyList();
  }
}