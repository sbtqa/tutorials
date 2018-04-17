package ru.sberbank.example;

import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.unmodifiableList;

interface PromotionService {
  List<Item> getGiftsByItem(Item item);
}

/**
 *
 */
public class Order {
  private final List<Item> items = new ArrayList<>();
  private final List<Item> itemsView = unmodifiableList(items);
  private final PromotionService promotionService;

  public Order(PromotionService promotionService) {
    this.promotionService = promotionService;
  }

  public List<Item> getItems() {
    return itemsView;
  }

  public void buyItem(Item item, BankAccount account)
          throws InsufficientFundsException {
    account.withdraw(item.getPrice());
    items.add(item);
    List<Item> gifts = promotionService.getGiftsByItem(item);
    items.addAll(gifts);
  }
}
