package ru.sbtqa.tutorials.advanced.mockito.services;

import java.util.List;

import ru.sbtqa.tutorials.advanced.mockito.Item;

/**
 * Promotion service. Use this interface when you want to implement some promotional activities.
 */
public interface PromotionService {
    /**
     * Returns the gifted items.
     *
     * @param item the item that was bought
     * @return the gifted items
     */
    List<Item> getGiftsByItem(Item item);
}
