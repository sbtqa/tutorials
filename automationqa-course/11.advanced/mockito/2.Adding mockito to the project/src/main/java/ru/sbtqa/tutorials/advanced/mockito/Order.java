package ru.sbtqa.tutorials.advanced.mockito;

import java.util.ArrayList;
import java.util.List;

import ru.sbtqa.tutorials.advanced.mockito.services.PromotionService;

import static java.util.Collections.unmodifiableList;

/**
 * The client order.
 */
public class Order {
    private final List<Item> items = new ArrayList<>();
    private final List<Item> itemsView = unmodifiableList(items);
    private final PromotionService promotionService;

    /**
     * Constructs new client order.
     *
     * @param promotionService the promotion service to use in this client order
     */
    public Order(PromotionService promotionService) {
        this.promotionService = promotionService;
    }

    /**
     * Returns bought items.
     *
     * @return unmodifiable list of the items that was bought in this order
     */
    public List<Item> getItems() {
        return itemsView;
    }

    /**
     * Implements operation of buying order item. Withdraws funds from the specified bank account.
     * If succeeded when adds item to the list of bought. After that gifted items (if any) added to
     * the same list.
     *
     * @param item    the item to buy
     * @param account the bank account to use
     * @throws InsufficientFundsException if insufficient funds on the specified bank account
     */
    public void buyItem(Item item, BankAccount account) throws InsufficientFundsException {
        account.withdraw(item.getPrice());
        items.add(item);
        List<Item> gifts = promotionService.getGiftsByItem(item);
        items.addAll(gifts);
    }
}
