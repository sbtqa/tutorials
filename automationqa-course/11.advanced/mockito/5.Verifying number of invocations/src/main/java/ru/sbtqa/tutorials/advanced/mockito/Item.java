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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Item item = (Item) o;

        if (name != null ? !name.equals(item.name) : item.name != null) return false;
        return price != null ? price.equals(item.price) : item.price == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (price != null ? price.hashCode() : 0);
        return result;
    }
}
