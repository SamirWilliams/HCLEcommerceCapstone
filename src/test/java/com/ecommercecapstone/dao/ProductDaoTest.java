package com.ecommercecapstone.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyBoolean;
import static org.mockito.Mockito.anyDouble;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.ecommercecapstone.model.Cart;
import com.ecommercecapstone.model.Product;
import com.mysql.cj.jdbc.ConnectionWrapper;
import com.mysql.cj.jdbc.JdbcConnection;
import com.mysql.cj.jdbc.MysqlPooledConnection;
import com.mysql.cj.jdbc.ha.LoadBalancedMySQLConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

class ProductDaoTest
{
    /**
     * Method under test: {@link ProductDao#ProductDao(Connection)}
     */
    @Test
    void testConstructor()
    {
        assertTrue((new ProductDao(mock(Connection.class))).getAllProducts().isEmpty());
    }

    /**
     * Method under test: {@link ProductDao#getAllProducts()}
     */
    @Test
    void testGetAllProducts() throws SQLException
    {
        ResultSet resultSet = mock(ResultSet.class);
        when(resultSet.getDouble((String) any())).thenReturn(10.0d);
        when(resultSet.getInt((String) any())).thenReturn(1);
        when(resultSet.getString((String) any())).thenReturn("String");
        when(resultSet.next()).thenReturn(true).thenReturn(true).thenReturn(false);
        PreparedStatement preparedStatement = mock(PreparedStatement.class);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        Connection connection = mock(Connection.class);
        when(connection.prepareStatement((String) any())).thenReturn(preparedStatement);
        List<Product> actualAllProducts = (new ProductDao(connection)).getAllProducts();
        assertEquals(2, actualAllProducts.size());
        Product getResult = actualAllProducts.get(0);
        assertEquals(10.0d, getResult.getUnitPrice());
        Product getResult1 = actualAllProducts.get(1);
        assertEquals(10.0d, getResult1.getUnitPrice());
        assertEquals("String", getResult1.getProductName());
        assertEquals("String", getResult1.getProductImage());
        assertEquals(1, getResult1.getProductId());
        assertEquals("String", getResult1.getCategory());
        assertEquals("String", getResult.getProductName());
        assertEquals("String", getResult.getProductImage());
        assertEquals(1, getResult.getProductId());
        assertEquals("String", getResult.getCategory());
        verify(connection).prepareStatement((String) any());
        verify(preparedStatement).executeQuery();
        verify(resultSet, atLeast(1)).next();
        verify(resultSet, atLeast(1)).getDouble((String) any());
        verify(resultSet, atLeast(1)).getInt((String) any());
        verify(resultSet, atLeast(1)).getString((String) any());
    }

    /**
     * Method under test: {@link ProductDao#getCartProducts(List)}
     */
    @Test
    void testGetCartProducts()
    {
        ProductDao productDao = new ProductDao(mock(Connection.class));
        assertTrue(productDao.getCartProducts(new ArrayList<>()).isEmpty());
    }



    /**
     * Method under test: {@link ProductDao#getCartProducts(List)}
     */
    @Test
    void testGetCartProducts4() throws SQLException
    {
        ResultSet resultSet = mock(ResultSet.class);
        when(resultSet.getDouble((String) any())).thenReturn(10.0d);
        when(resultSet.getInt((String) any())).thenReturn(1);
        when(resultSet.getString((String) any())).thenReturn("String");
        when(resultSet.next()).thenReturn(true).thenReturn(true).thenReturn(false);
        PreparedStatement preparedStatement = mock(PreparedStatement.class);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        doNothing().when(preparedStatement).setInt(anyInt(), anyInt());
        Connection connection = mock(Connection.class);
        when(connection.prepareStatement((String) any())).thenReturn(preparedStatement);
        ProductDao productDao = new ProductDao(connection);

        ArrayList<Cart> cartList = new ArrayList<>();
        cartList.add(new Cart(1));
        assertEquals(2, productDao.getCartProducts(cartList).size());
        verify(connection).prepareStatement((String) any());
        verify(preparedStatement).executeQuery();
        verify(preparedStatement).setInt(anyInt(), anyInt());
        verify(resultSet, atLeast(1)).next();
        verify(resultSet, atLeast(1)).getDouble((String) any());
        verify(resultSet, atLeast(1)).getInt((String) any());
        verify(resultSet, atLeast(1)).getString((String) any());
    }

    /**
     * Method under test: {@link ProductDao#getTotalCartPrice(ArrayList)}
     */
    @Test
    void testGetTotalCartPrice()
    {
        ProductDao productDao = new ProductDao(mock(Connection.class));
        assertEquals(0.0d, productDao.getTotalCartPrice(new ArrayList<>()));
    }



    /**
     * Method under test: {@link ProductDao#getTotalCartPrice(ArrayList)}
     */
    @Test
    void testGetTotalCartPrice4() throws SQLException
    {
        ResultSet resultSet = mock(ResultSet.class);
        when(resultSet.getDouble((String) any())).thenReturn(10.0d);
        when(resultSet.next()).thenReturn(true).thenReturn(true).thenReturn(false);
        PreparedStatement preparedStatement = mock(PreparedStatement.class);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        doNothing().when(preparedStatement).setInt(anyInt(), anyInt());
        Connection connection = mock(Connection.class);
        when(connection.prepareStatement((String) any())).thenReturn(preparedStatement);
        ProductDao productDao = new ProductDao(connection);

        ArrayList<Cart> cartList = new ArrayList<>();
        cartList.add(new Cart(1));
        assertEquals(20.0d, productDao.getTotalCartPrice(cartList));
        verify(connection).prepareStatement((String) any());
        verify(preparedStatement).executeQuery();
        verify(preparedStatement).setInt(anyInt(), anyInt());
        verify(resultSet, atLeast(1)).next();
        verify(resultSet, atLeast(1)).getDouble((String) any());
    }

    /**
     * Method under test: {@link ProductDao#addProduct(String, String, double, String)}
     */
    @Test
    void testAddProduct()
    {
        assertFalse((new ProductDao(null)).addProduct("Name", "Image", 10.0d, "Category"));
        assertFalse((new ProductDao(new LoadBalancedMySQLConnection(null))).addProduct("Name", "Image", 10.0d, "Category"));
    }


    /**
     * Method under test: {@link ProductDao#updateProduct(int, String, String, double, String)}
     */
    @Test
    void testUpdateProduct()
    {
        assertFalse((new ProductDao(null)).updateProduct(1, "Name", "Image", 10.0d, "Category"));
        assertFalse(
                (new ProductDao(new LoadBalancedMySQLConnection(null))).updateProduct(1, "Name", "Image", 10.0d, "Category"));
    }


    /**
     * Method under test: {@link ProductDao#deleteProduct(int)}
     */
    @Test
    void testDeleteProduct() throws SQLException
    {
        PreparedStatement preparedStatement = mock(PreparedStatement.class);
        when(preparedStatement.executeUpdate()).thenReturn(1);
        doNothing().when(preparedStatement).setInt(anyInt(), anyInt());
        Connection connection = mock(Connection.class);
        when(connection.prepareStatement((String) any())).thenReturn(preparedStatement);
        assertTrue((new ProductDao(connection)).deleteProduct(1));
        verify(connection).prepareStatement((String) any());
        verify(preparedStatement).executeUpdate();
        verify(preparedStatement).setInt(anyInt(), anyInt());
    }

    /**
     * Method under test: {@link ProductDao#getSingleProduct(int)}
     */
    @Test
    void testGetSingleProduct() throws SQLException
    {
        ResultSet resultSet = mock(ResultSet.class);
        when(resultSet.getDouble((String) any())).thenReturn(10.0d);
        when(resultSet.getInt((String) any())).thenReturn(1);
        when(resultSet.getString((String) any())).thenReturn("String");
        when(resultSet.next()).thenReturn(true).thenReturn(true).thenReturn(false);
        PreparedStatement preparedStatement = mock(PreparedStatement.class);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        doNothing().when(preparedStatement).setInt(anyInt(), anyInt());
        Connection connection = mock(Connection.class);
        when(connection.prepareStatement((String) any())).thenReturn(preparedStatement);
        Product actualSingleProduct = (new ProductDao(connection)).getSingleProduct(1);
        assertEquals("String", actualSingleProduct.getCategory());
        assertEquals(10.0d, actualSingleProduct.getUnitPrice());
        assertEquals("String", actualSingleProduct.getProductName());
        assertEquals("String", actualSingleProduct.getProductImage());
        assertEquals(1, actualSingleProduct.getProductId());
        verify(connection).prepareStatement((String) any());
        verify(preparedStatement).executeQuery();
        verify(preparedStatement).setInt(anyInt(), anyInt());
        verify(resultSet, atLeast(1)).next();
        verify(resultSet, atLeast(1)).getDouble((String) any());
        verify(resultSet, atLeast(1)).getInt((String) any());
        verify(resultSet, atLeast(1)).getString((String) any());
    }
}

