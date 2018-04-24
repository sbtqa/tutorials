package ru.sbtqa.tutorials.advanced.mockito;

import java.math.BigDecimal;

/**
 * Order item. This implementation is immutable.
 */
public final class Item {
    /**
     * The name of the item.
     */
    private final String name;

    /**
     * The price of the item.
     */
    private final BigDecimal price;

    /**
     * Initializes a newly created {@code Item} object. Note that implementation Items are
     * immutable.
     */
    public Item(String name, BigDecimal price) {
        this.name = name;
        this.price = price;
    }

    /**
     * Returns the name of this item.
     *
     * @return the name of this item
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the price of this item.
     *
     * @return the price of this item
     */
    public BigDecimal getPrice() {
        return price;
    }
}
