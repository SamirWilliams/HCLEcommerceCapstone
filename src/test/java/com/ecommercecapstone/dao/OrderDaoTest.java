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
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.ecommercecapstone.model.Cart;
import com.ecommercecapstone.model.Order;
import com.ecommercecapstone.model.User;
import com.mysql.cj.jdbc.ConnectionWrapper;
import com.mysql.cj.jdbc.JdbcConnection;
import com.mysql.cj.jdbc.MysqlPooledConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class OrderDaoTest
{
    private Connection connection;
    @BeforeEach
    void setUp()
    {
        connection= mock(com.ecommercecapstone.connection.DBCon.getConnection().getClass());
    }

    /**
     * Method under test: {@link OrderDao#listUserOrders(int)}
     */
    @Test
    void testListUserOrders() throws SQLException
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
        assertEquals(2, (new OrderDao(connection)).listUserOrders(1).size());
        verify(connection).prepareStatement((String) any());
        verify(preparedStatement).executeQuery();
        verify(preparedStatement).setInt(anyInt(), anyInt());
        verify(resultSet, atLeast(1)).next();
        verify(resultSet, atLeast(1)).getDouble((String) any());
        verify(resultSet, atLeast(1)).getInt((String) any());
        verify(resultSet, atLeast(1)).getString((String) any());
    }

    /**
     * Method under test: {@link OrderDao#listUserOrders(int)}
     */
    @Test
    void testListUserOrders2() throws SQLException
    {
        try
        {
            ResultSet resultSet = mock(ResultSet.class);
            when(resultSet.getDouble((String) any())).thenThrow(new SQLException());
            when(resultSet.getInt((String) any())).thenThrow(new SQLException());
            when(resultSet.getString((String) any())).thenThrow(new SQLException());
            when(resultSet.next()).thenReturn(true).thenReturn(true).thenReturn(false);
            PreparedStatement preparedStatement = mock(PreparedStatement.class);
            when(preparedStatement.executeQuery()).thenReturn(resultSet);
            doNothing().when(preparedStatement).setInt(anyInt(), anyInt());
            Connection connection = mock(Connection.class);
            when(connection.prepareStatement((String) any())).thenReturn(preparedStatement);
            assertTrue((new OrderDao(connection)).listUserOrders(1).isEmpty());
            verify(connection).prepareStatement((String) any());
            verify(preparedStatement).executeQuery();
            verify(preparedStatement).setInt(anyInt(), anyInt());
            verify(resultSet).next();
            verify(resultSet).getInt((String) any());
        } catch (SQLException ignored)
        {

        }
    }

    /**
     * Method under test: {@link OrderDao#cancelOrder(int)}
     */
    @Test
    void testCancelOrder()
    {
        assertFalse((new OrderDao(connection)).cancelOrder(123));
    }

    /**
     * Method under test: {@link OrderDao#cancelOrder(int)}
     */
    @Test
    void testCancelOrder4() throws SQLException
    {
        try
        {
            PreparedStatement preparedStatement = mock(PreparedStatement.class);
            when(preparedStatement.executeUpdate()).thenThrow(new SQLException());
            doThrow(new SQLException()).when(preparedStatement).setInt(anyInt(), anyInt());
            when(connection.prepareStatement((String) any())).thenReturn(preparedStatement);
            assertFalse((new OrderDao(connection)).cancelOrder(0));
            verify(connection).prepareStatement((String) any());
            verify(preparedStatement).setInt(anyInt(), anyInt());
        } catch (SQLException ignored)
        {

        }
    }

    /**
     * Method under test: {@link OrderDao#insertOrder(Order)}
     */
    @Test
    void testInsertOrder() throws SQLException
    {
        ResultSet resultSet = mock(ResultSet.class);
        when(resultSet.getInt((String) any())).thenReturn(1);
        when(resultSet.next()).thenReturn(true).thenReturn(true).thenReturn(false);
        PreparedStatement preparedStatement = mock(PreparedStatement.class);
        doNothing().when(preparedStatement).setDouble(anyInt(), anyDouble());
        when(preparedStatement.executeUpdate()).thenReturn(1);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        doNothing().when(preparedStatement).setInt(anyInt(), anyInt());
        Connection connection = mock(Connection.class);
        when(connection.prepareStatement((String) any())).thenReturn(preparedStatement);
        OrderDao orderDao = new OrderDao(connection);
        assertTrue(orderDao.insertOrder(new Order(123, 123, "2020-03-01", 10.0d, 1)));
        verify(connection, atLeast(1)).prepareStatement((String) any());
        verify(preparedStatement, atLeast(1)).executeUpdate();
        verify(preparedStatement).executeQuery();
        verify(preparedStatement).setDouble(anyInt(), anyDouble());
        verify(preparedStatement, atLeast(1)).setInt(anyInt(), anyInt());
        verify(resultSet, atLeast(1)).next();
        verify(resultSet, atLeast(1)).getInt((String) any());
    }

    /**
     * Method under test: {@link OrderDao#insertOrder(Order)}
     */
    @Test
    void testInsertOrder2() throws SQLException
    {
        try
        {
            ResultSet resultSet = mock(ResultSet.class);
            when(resultSet.getInt((String) any())).thenReturn(1);
            when(resultSet.next()).thenReturn(true).thenReturn(true).thenReturn(false);
            PreparedStatement preparedStatement = mock(PreparedStatement.class);
            doThrow(new SQLException()).when(preparedStatement).setDouble(anyInt(), anyDouble());
            when(preparedStatement.executeUpdate()).thenReturn(1);
            when(preparedStatement.executeQuery()).thenReturn(resultSet);
            doNothing().when(preparedStatement).setInt(anyInt(), anyInt());
            Connection connection = mock(Connection.class);
            when(connection.prepareStatement((String) any())).thenReturn(preparedStatement);
            OrderDao orderDao = new OrderDao(connection);
            assertFalse(orderDao.insertOrder(new Order(123, 123, "2020-03-01", 10.0d, 1)));
            verify(connection, atLeast(1)).prepareStatement((String) any());
            verify(preparedStatement).executeUpdate();
            verify(preparedStatement).executeQuery();
            verify(preparedStatement).setDouble(anyInt(), anyDouble());
            verify(preparedStatement, atLeast(1)).setInt(anyInt(), anyInt());
            verify(resultSet, atLeast(1)).next();
            verify(resultSet, atLeast(1)).getInt((String) any());
        } catch (SQLException ignored)
        {
        }
    }

    /**
     * Method under test: {@link OrderDao#insertOrder(Order)}
     */
    @Test
    void testInsertOrder3() throws SQLException
    {
        try
        {
            ResultSet resultSet = mock(ResultSet.class);
            when(resultSet.getInt((String) any())).thenReturn(1);
            when(resultSet.next()).thenReturn(true).thenReturn(true).thenReturn(false);
            PreparedStatement preparedStatement = mock(PreparedStatement.class);
            doNothing().when(preparedStatement).setDouble(anyInt(), anyDouble());
            when(preparedStatement.executeUpdate()).thenReturn(1);
            when(preparedStatement.executeQuery()).thenReturn(resultSet);
            doNothing().when(preparedStatement).setInt(anyInt(), anyInt());
            Connection connection = mock(Connection.class);
            when(connection.prepareStatement((String) any())).thenReturn(preparedStatement);

            OrderDao orderDao = new OrderDao(connection);
            ArrayList<Cart> cart_list = new ArrayList<>();
            orderDao.insertOrder(cart_list, new User(123, "Jane", "Doe", "4105551212", "42 Main St", "Oxford", "21654", "GB",
                    "jane.doe@example.org", true));
            assertFalse(orderDao.insertOrder(new Order(123, 123, "2020-03-01", 10.0d, 1)));
            verify(connection, atLeast(1)).prepareStatement((String) any());
            verify(preparedStatement, atLeast(1)).executeUpdate();
            verify(preparedStatement, atLeast(1)).executeQuery();
            verify(preparedStatement, atLeast(1)).setInt(anyInt(), anyInt());
            verify(resultSet, atLeast(1)).next();
            verify(resultSet, atLeast(1)).getInt((String) any());
        } catch (SQLException ignored)
        {

        }
    }

    /**
     * Method under test: {@link OrderDao#insertOrder(List, User)}
     */
    @Test
    void testInsertOrder4() throws SQLException
    {
        ResultSet resultSet = mock(ResultSet.class);
        when(resultSet.getInt((String) any())).thenReturn(1);
        when(resultSet.next()).thenReturn(true).thenReturn(true).thenReturn(false);
        PreparedStatement preparedStatement = mock(PreparedStatement.class);
        when(preparedStatement.executeUpdate()).thenReturn(1);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        doNothing().when(preparedStatement).setInt(anyInt(), anyInt());
        Connection connection = mock(Connection.class);
        when(connection.prepareStatement((String) any())).thenReturn(preparedStatement);
        OrderDao orderDao = new OrderDao(connection);
        ArrayList<Cart> cart_list = new ArrayList<>();
        assertFalse(orderDao.insertOrder(cart_list, new User(123, "Jane", "Doe", "4105551212", "42 Main St", "Oxford",
                "21654", "GB", "jane.doe@example.org", true)));
        verify(connection, atLeast(1)).prepareStatement((String) any());
        verify(preparedStatement).executeUpdate();
        verify(preparedStatement).executeQuery();
        verify(preparedStatement).setInt(anyInt(), anyInt());
        verify(resultSet, atLeast(1)).next();
        verify(resultSet, atLeast(1)).getInt((String) any());
    }

    /**
     * Method under test: {@link OrderDao#insertOrder(List, User)}
     */
    @Test
    void testInsertOrder5() throws SQLException
    {
        try
        {
            ResultSet resultSet = mock(ResultSet.class);
            when(resultSet.getInt((String) any())).thenThrow(new SQLException());
            when(resultSet.next()).thenReturn(true).thenReturn(true).thenReturn(false);
            PreparedStatement preparedStatement = mock(PreparedStatement.class);
            when(preparedStatement.executeUpdate()).thenReturn(1);
            when(preparedStatement.executeQuery()).thenReturn(resultSet);
            doNothing().when(preparedStatement).setInt(anyInt(), anyInt());
            Connection connection = mock(Connection.class);
            when(connection.prepareStatement((String) any())).thenReturn(preparedStatement);
            OrderDao orderDao = new OrderDao(connection);
            ArrayList<Cart> cart_list = new ArrayList<>();
            assertFalse(orderDao.insertOrder(cart_list, new User(123, "Jane", "Doe", "4105551212", "42 Main St", "Oxford",
                    "21654", "GB", "jane.doe@example.org", true)));
            verify(connection, atLeast(1)).prepareStatement((String) any());
            verify(preparedStatement).executeUpdate();
            verify(preparedStatement).executeQuery();
            verify(preparedStatement).setInt(anyInt(), anyInt());
            verify(resultSet).next();
            verify(resultSet).getInt((String) any());
        } catch (SQLException ignored)
        {

        }
    }

    /**
     * Method under test: {@link OrderDao#insertOrder(List, User)}
     */
    @Test
    void testInsertOrder6() throws SQLException
    {
        ResultSet resultSet = mock(ResultSet.class);
        when(resultSet.getInt((String) any())).thenReturn(1);
        when(resultSet.next()).thenReturn(true).thenReturn(true).thenReturn(false);
        PreparedStatement preparedStatement = mock(PreparedStatement.class);
        when(preparedStatement.executeUpdate()).thenReturn(1);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        doNothing().when(preparedStatement).setInt(anyInt(), anyInt());
        Connection connection = mock(Connection.class);
        when(connection.prepareStatement((String) any())).thenReturn(preparedStatement);

        OrderDao orderDao = new OrderDao(connection);
        ArrayList<Cart> cart_list = new ArrayList<>();
        orderDao.insertOrder(cart_list, new User(123, "Jane", "Doe", "4105551212", "42 Main St", "Oxford", "21654", "GB",
                "jane.doe@example.org", true));
        ArrayList<Cart> cart_list1 = new ArrayList<>();
        assertFalse(orderDao.insertOrder(cart_list1, new User(123, "Jane", "Doe", "4105551212", "42 Main St", "Oxford",
                "21654", "GB", "jane.doe@example.org", true)));
        verify(connection, atLeast(1)).prepareStatement((String) any());
        verify(preparedStatement, atLeast(1)).executeUpdate();
        verify(preparedStatement, atLeast(1)).executeQuery();
        verify(preparedStatement, atLeast(1)).setInt(anyInt(), anyInt());
        verify(resultSet, atLeast(1)).next();
        verify(resultSet, atLeast(1)).getInt((String) any());
    }

    /**
     * Method under test: {@link OrderDao#insertOrder(List, User)}
     */
    @Test
    void testInsertOrder7() throws SQLException
    {
        ResultSet resultSet = mock(ResultSet.class);
        when(resultSet.getInt((String) any())).thenReturn(1);
        when(resultSet.next()).thenReturn(true).thenReturn(true).thenReturn(false);
        PreparedStatement preparedStatement = mock(PreparedStatement.class);
        doNothing().when(preparedStatement).setDouble(anyInt(), anyDouble());
        when(preparedStatement.executeUpdate()).thenReturn(1);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        doNothing().when(preparedStatement).setInt(anyInt(), anyInt());
        Connection connection = mock(Connection.class);
        when(connection.prepareStatement((String) any())).thenReturn(preparedStatement);
        OrderDao orderDao = new OrderDao(connection);

        ArrayList<Cart> cartList = new ArrayList<>();
        cartList.add(new Cart(1));
        assertTrue(orderDao.insertOrder(cartList, new User(123, "Jane", "Doe", "4105551212", "42 Main St", "Oxford",
                "21654", "GB", "jane.doe@example.org", true)));
        verify(connection, atLeast(1)).prepareStatement((String) any());
        verify(preparedStatement, atLeast(1)).executeUpdate();
        verify(preparedStatement).executeQuery();
        verify(preparedStatement).setDouble(anyInt(), anyDouble());
        verify(preparedStatement, atLeast(1)).setInt(anyInt(), anyInt());
        verify(resultSet, atLeast(1)).next();
        verify(resultSet, atLeast(1)).getInt((String) any());
    }
}

