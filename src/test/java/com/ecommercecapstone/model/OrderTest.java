package com.ecommercecapstone.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class OrderTest
{
    /**
     * Method under test: {@link Order#canEqual(Object)}
     */
    @Test
    void testCanEqual()
    {
        assertFalse((new Order(123, 123, "2020-03-01", 10.0d, 1)).canEqual("Other"));
    }

    /**
     * Method under test: {@link Order#canEqual(Object)}
     */
    @Test
    void testCanEqual2()
    {
        Order order = new Order(123, 123, "2020-03-01", 10.0d, 1);
        assertTrue(order.canEqual(new Order(123, 123, "2020-03-01", 10.0d, 3)));
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link Order#Order()}
     *   <li>{@link Order#setOrderDate(String)}
     *   <li>{@link Order#setOrderId(int)}
     *   <li>{@link Order#setOrderPrice(double)}
     *   <li>{@link Order#setQuantity(int)}
     *   <li>{@link Order#setUserId(int)}
     *   <li>{@link Order#toString()}
     *   <li>{@link Order#getOrderDate()}
     *   <li>{@link Order#getOrderId()}
     *   <li>{@link Order#getOrderPrice()}
     *   <li>{@link Order#getQuantity()}
     *   <li>{@link Order#getUserId()}
     * </ul>
     */
    @Test
    void testConstructor()
    {
        Order actualOrder = new Order();
        actualOrder.setOrderDate("2020-03-01");
        actualOrder.setOrderId(123);
        actualOrder.setOrderPrice(10.0d);
        actualOrder.setQuantity(1);
        actualOrder.setUserId(123);
        String actualToStringResult = actualOrder.toString();
        assertEquals("2020-03-01", actualOrder.getOrderDate());
        assertEquals(123, actualOrder.getOrderId());
        assertEquals(10.0d, actualOrder.getOrderPrice());
        assertEquals(1, actualOrder.getQuantity());
        assertEquals(123, actualOrder.getUserId());
        assertEquals("Order(orderId=123, userId=123, orderDate=2020-03-01, orderPrice=10.0, quantity=1)",
                actualToStringResult);
    }

    /**
     * Method under test: {@link Order#Order(int, int, String, double, int)}
     */
    @Test
    void testConstructor2()
    {
        Order actualOrder = new Order(123, 123, "2020-03-01", 10.0d, 1);

        assertEquals(123, actualOrder.getUserId());
        assertEquals(1, actualOrder.getQuantity());
        assertEquals(10.0d, actualOrder.getOrderPrice());
        assertEquals(123, actualOrder.getOrderId());
        assertEquals("2020-03-01", actualOrder.getOrderDate());
    }

    /**
     * Method under test: {@link Order#equals(Object)}
     */
    @Test
    void testEquals()
    {
        assertNotEquals(new Order(123, 123, "2020-03-01", 10.0d, 1), null);
        assertNotEquals(new Order(123, 123, "2020-03-01", 10.0d, 1), "Different type to Order");
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link Order#equals(Object)}
     *   <li>{@link Order#hashCode()}
     * </ul>
     */
    @Test
    void testEquals2()
    {
        Order order = new Order(123, 123, "2020-03-01", 10.0d, 1);
        assertEquals(order, order);
        int expectedHashCodeResult = order.hashCode();
        assertEquals(expectedHashCodeResult, order.hashCode());
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link Order#equals(Object)}
     *   <li>{@link Order#hashCode()}
     * </ul>
     */
    @Test
    void testEquals3()
    {
        Order order = new Order(123, 123, "2020-03-01", 10.0d, 1);
        Order order1 = new Order(123, 123, "2020-03-01", 10.0d, 1);

        assertEquals(order, order1);
        int expectedHashCodeResult = order.hashCode();
        assertEquals(expectedHashCodeResult, order1.hashCode());
    }

    /**
     * Method under test: {@link Order#equals(Object)}
     */
    @Test
    void testEquals4()
    {
        Order order = new Order(1, 123, "2020-03-01", 10.0d, 1);
        assertNotEquals(order, new Order(123, 123, "2020-03-01", 10.0d, 1));
    }

    /**
     * Method under test: {@link Order#equals(Object)}
     */
    @Test
    void testEquals5()
    {
        Order order = new Order(123, 1, "2020-03-01", 10.0d, 1);
        assertNotEquals(order, new Order(123, 123, "2020-03-01", 10.0d, 1));
    }

    /**
     * Method under test: {@link Order#equals(Object)}
     */
    @Test
    void testEquals6()
    {
        Order order = new Order(123, 123, "2020/03/01", 10.0d, 1);
        assertNotEquals(order, new Order(123, 123, "2020-03-01", 10.0d, 1));
    }

    /**
     * Method under test: {@link Order#equals(Object)}
     */
    @Test
    void testEquals7()
    {
        Order order = new Order(123, 123, null, 10.0d, 1);
        assertNotEquals(order, new Order(123, 123, "2020-03-01", 10.0d, 1));
    }

    /**
     * Method under test: {@link Order#equals(Object)}
     */
    @Test
    void testEquals8()
    {
        Order order = new Order(123, 123, "2020-03-01", 0.5d, 1);
        assertNotEquals(order, new Order(123, 123, "2020-03-01", 10.0d, 1));
    }

    /**
     * Method under test: {@link Order#equals(Object)}
     */
    @Test
    void testEquals9()
    {
        Order order = new Order(123, 123, "2020-03-01", 10.0d, 3);
        assertNotEquals(order, new Order(123, 123, "2020-03-01", 10.0d, 1));
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link Order#equals(Object)}
     *   <li>{@link Order#hashCode()}
     * </ul>
     */
    @Test
    void testEquals10()
    {
        Order order = new Order(123, 123, null, 10.0d, 1);
        Order order1 = new Order(123, 123, null, 10.0d, 1);

        assertEquals(order, order1);
        int expectedHashCodeResult = order.hashCode();
        assertEquals(expectedHashCodeResult, order1.hashCode());
    }
}

