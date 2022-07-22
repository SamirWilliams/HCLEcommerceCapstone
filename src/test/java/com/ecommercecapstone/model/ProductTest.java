package com.ecommercecapstone.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;

class ProductTest
{
    /**
     * Method under test: {@link Product#canEqual(Object)}
     */
    @Test
    void testCanEqual()
    {
        assertFalse((new Product(123, "Product Name", "Product Image", 10.0d, "Category")).canEqual("Other"));
    }

    /**
     * Method under test: {@link Product#canEqual(Object)}
     */
    @Test
    void testCanEqual2()
    {
        Product product = new Product(123, "Product Name", "Product Image", 10.0d, "Category");
        assertTrue(product.canEqual(new Cart()));
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link Product#Product()}
     *   <li>{@link Product#setCategory(String)}
     *   <li>{@link Product#setProductId(int)}
     *   <li>{@link Product#setProductImage(String)}
     *   <li>{@link Product#setProductName(String)}
     *   <li>{@link Product#setUnitPrice(double)}
     *   <li>{@link Product#toString()}
     *   <li>{@link Product#getCategory()}
     *   <li>{@link Product#getProductId()}
     *   <li>{@link Product#getProductImage()}
     *   <li>{@link Product#getProductName()}
     *   <li>{@link Product#getUnitPrice()}
     * </ul>
     */
    @Test
    void testConstructor()
    {
        Product actualProduct = new Product();
        actualProduct.setCategory("Category");
        actualProduct.setProductId(123);
        actualProduct.setProductImage("Product Image");
        actualProduct.setProductName("Product Name");
        actualProduct.setUnitPrice(10.0d);
        String actualToStringResult = actualProduct.toString();
        assertEquals("Category", actualProduct.getCategory());
        assertEquals(123, actualProduct.getProductId());
        assertEquals("Product Image", actualProduct.getProductImage());
        assertEquals("Product Name", actualProduct.getProductName());
        assertEquals(10.0d, actualProduct.getUnitPrice());
        assertEquals("Product(productId=123, productName=Product Name, productImage=Product Image, unitPrice=10.0,"
                + " category=Category)", actualToStringResult);
    }

    /**
     * Method under test: {@link Product#Product(int, String, String, double, String)}
     */
    @Test
    void testConstructor2()
    {
        Product actualProduct = new Product(123, "Product Name", "Product Image", 10.0d, "Category");

        assertEquals("Category", actualProduct.getCategory());
        assertEquals(10.0d, actualProduct.getUnitPrice());
        assertEquals("Product Name", actualProduct.getProductName());
        assertEquals("Product Image", actualProduct.getProductImage());
        assertEquals(123, actualProduct.getProductId());
    }

    /**
     * Method under test: {@link Product#equals(Object)}
     */
    @Test
    void testEquals()
    {
        assertNotEquals(new Product(123, "Product Name", "Product Image", 10.0d, "Category"), null);
        assertNotEquals(new Product(123, "Product Name", "Product Image", 10.0d, "Category"), "Different type to Product");
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link Product#equals(Object)}
     *   <li>{@link Product#hashCode()}
     * </ul>
     */
    @Test
    void testEquals2()
    {
        Product product = new Product(123, "Product Name", "Product Image", 10.0d, "Category");
        assertEquals(product, product);
        int expectedHashCodeResult = product.hashCode();
        assertEquals(expectedHashCodeResult, product.hashCode());
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link Product#equals(Object)}
     *   <li>{@link Product#hashCode()}
     * </ul>
     */
    @Test
    void testEquals3()
    {
        Product product = new Product(123, "Product Name", "Product Image", 10.0d, "Category");
        Product product1 = new Product(123, "Product Name", "Product Image", 10.0d, "Category");

        assertEquals(product, product1);
        int expectedHashCodeResult = product.hashCode();
        assertEquals(expectedHashCodeResult, product1.hashCode());
    }

    /**
     * Method under test: {@link Product#equals(Object)}
     */
    @Test
    void testEquals4()
    {
        Product product = new Product(1, "Product Name", "Product Image", 10.0d, "Category");
        assertNotEquals(product, new Product(123, "Product Name", "Product Image", 10.0d, "Category"));
    }

    /**
     * Method under test: {@link Product#equals(Object)}
     */
    @Test
    void testEquals5()
    {
        Product product = new Product(123, "Product Image", "Product Image", 10.0d, "Category");
        assertNotEquals(product, new Product(123, "Product Name", "Product Image", 10.0d, "Category"));
    }

    /**
     * Method under test: {@link Product#equals(Object)}
     */
    @Test
    void testEquals6()
    {
        Product product = new Product(123, null, "Product Image", 10.0d, "Category");
        assertNotEquals(product, new Product(123, "Product Name", "Product Image", 10.0d, "Category"));
    }

    /**
     * Method under test: {@link Product#equals(Object)}
     */
    @Test
    void testEquals7()
    {
        Product product = new Product(123, "Product Name", "Product Name", 10.0d, "Category");
        assertNotEquals(product, new Product(123, "Product Name", "Product Image", 10.0d, "Category"));
    }

    /**
     * Method under test: {@link Product#equals(Object)}
     */
    @Test
    void testEquals8()
    {
        Product product = new Product(123, "Product Name", null, 10.0d, "Category");
        assertNotEquals(product, new Product(123, "Product Name", "Product Image", 10.0d, "Category"));
    }

    /**
     * Method under test: {@link Product#equals(Object)}
     */
    @Test
    void testEquals9()
    {
        Product product = new Product(123, "Product Name", "Product Image", 0.5d, "Category");
        assertNotEquals(product, new Product(123, "Product Name", "Product Image", 10.0d, "Category"));
    }

    /**
     * Method under test: {@link Product#equals(Object)}
     */
    @Test
    void testEquals10()
    {
        Product product = new Product(123, "Product Name", "Product Image", 10.0d, "Product Name");
        assertNotEquals(product, new Product(123, "Product Name", "Product Image", 10.0d, "Category"));
    }

    /**
     * Method under test: {@link Product#equals(Object)}
     */
    @Test
    void testEquals11()
    {
        Product product = new Product(123, "Product Name", "Product Image", 10.0d, null);
        assertNotEquals(product, new Product(123, "Product Name", "Product Image", 10.0d, "Category"));
    }

    /**
     * Method under test: {@link Product#equals(Object)}
     */
    @Test
    void testEquals12()
    {
        Product product = new Product(123, "Product Name", "Product Image", 10.0d, "Category");
        assertNotEquals(product, new Cart(1));
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link Product#equals(Object)}
     *   <li>{@link Product#hashCode()}
     * </ul>
     */
    @Test
    void testEquals13()
    {
        Product product = new Product(123, "Product Name", "Product Image", 10.0d, "Category");
        Cart cart = mock(Cart.class);
        when(cart.getCategory()).thenReturn("Category");
        when(cart.getProductImage()).thenReturn("Product Image");
        when(cart.getProductName()).thenReturn("Product Name");
        when(cart.getUnitPrice()).thenReturn(10.0d);
        when(cart.getProductId()).thenReturn(123);
        when(cart.canEqual((Object) any())).thenReturn(true);
        assertEquals(product, cart);
        int notExpectedHashCodeResult = product.hashCode();
        assertNotEquals(notExpectedHashCodeResult, cart.hashCode());
    }
}

