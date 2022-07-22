package com.ecommercecapstone.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class CartTest
{
    /**
     * Method under test: {@link Cart#canEqual(Object)}
     */
    @Test
    void testCanEqual()
    {
        assertFalse((new Cart(1)).canEqual("Other"));
    }

    /**
     * Method under test: {@link Cart#canEqual(Object)}
     */
    @Test
    void testCanEqual2()
    {
        Cart cart = new Cart(1);
        assertTrue(cart.canEqual(new Cart()));
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link Cart#Cart()}
     *   <li>{@link Cart#setQuantity(int)}
     *   <li>{@link Cart#toString()}
     *   <li>{@link Cart#getQuantity()}
     * </ul>
     */
    @Test
    void testConstructor()
    {
        Cart actualCart = new Cart();
        actualCart.setQuantity(1);
        String actualToStringResult = actualCart.toString();
        assertEquals(1, actualCart.getQuantity());
        assertEquals("Cart(quantity=1)", actualToStringResult);
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link Cart#Cart(int)}
     *   <li>{@link Cart#setQuantity(int)}
     *   <li>{@link Cart#toString()}
     *   <li>{@link Cart#getQuantity()}
     * </ul>
     */
    @Test
    void testConstructor2()
    {
        Cart actualCart = new Cart(1);
        actualCart.setQuantity(1);
        String actualToStringResult = actualCart.toString();
        assertEquals(1, actualCart.getQuantity());
        assertEquals("Cart(quantity=1)", actualToStringResult);
    }

    /**
     * Method under test: {@link Cart#equals(Object)}
     */
    @Test
    void testEquals()
    {
        assertNotEquals(new Cart(1), null);
        assertNotEquals(new Cart(1), "Different type to Cart");
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link Cart#equals(Object)}
     *   <li>{@link Cart#hashCode()}
     * </ul>
     */
    @Test
    void testEquals2()
    {
        Cart cart = new Cart(1);
        assertEquals(cart, cart);
        int expectedHashCodeResult = cart.hashCode();
        assertEquals(expectedHashCodeResult, cart.hashCode());
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link Cart#equals(Object)}
     *   <li>{@link Cart#hashCode()}
     * </ul>
     */
    @Test
    void testEquals3()
    {
        Cart cart = new Cart(1);
        Cart cart1 = new Cart(1);
        assertEquals(cart, cart1);
        int expectedHashCodeResult = cart.hashCode();
        assertEquals(expectedHashCodeResult, cart1.hashCode());
    }

    /**
     * Method under test: {@link Cart#equals(Object)}
     */
    @Test
    void testEquals4()
    {
        Cart cart = new Cart(3);
        assertNotEquals(cart, new Cart(1));
    }
}

