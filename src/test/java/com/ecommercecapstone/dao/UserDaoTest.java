package com.ecommercecapstone.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyBoolean;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.ecommercecapstone.model.User;
import com.mysql.cj.jdbc.ConnectionWrapper;
import com.mysql.cj.jdbc.JdbcConnection;
import com.mysql.cj.jdbc.MysqlPooledConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class UserDaoTest
{
    private static Connection connection;
    private static UserDao userDao;

    @BeforeAll
    static void beforeAll()
    {
        connection = mock(com.ecommercecapstone.connection.DBCon.getConnection().getClass());
        userDao = new UserDao(connection);
    }


    /**
     * Method under test: {@link UserDao#userLogin(String, String)}
     */
    @Test
    void testUserLogin3() throws SQLException
    {
        ResultSet resultSet = mock(ResultSet.class);
        when(resultSet.getInt((String) any())).thenReturn(1);
        when(resultSet.getString((String) any())).thenReturn("String");
        when(resultSet.next()).thenReturn(true).thenReturn(true).thenReturn(false);
        PreparedStatement preparedStatement = mock(PreparedStatement.class);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        doNothing().when(preparedStatement).setString(anyInt(), (String) any());
        Connection connection = mock(Connection.class);
        when(connection.prepareStatement((String) any())).thenReturn(preparedStatement);
        User actualUserLoginResult = (new UserDao(connection))
                .userLogin("SELECT * FROM users WHERE email = ? and password = SHA2(?, 512);", "iloveyou");
        assertEquals("String", actualUserLoginResult.getAddress());
        assertTrue(actualUserLoginResult.isAdmin());
        assertEquals("String", actualUserLoginResult.getZipCode());
        assertEquals(1, actualUserLoginResult.getUserId());
        assertEquals("String", actualUserLoginResult.getPhoneNumber());
        assertEquals("String", actualUserLoginResult.getLastName());
        assertEquals("String", actualUserLoginResult.getFirstName());
        assertEquals("String", actualUserLoginResult.getEmail());
        assertEquals("String", actualUserLoginResult.getCountry());
        assertEquals("String", actualUserLoginResult.getCity());
        verify(connection).prepareStatement((String) any());
        verify(preparedStatement).executeQuery();
        verify(preparedStatement, atLeast(1)).setString(anyInt(), (String) any());
        verify(resultSet).next();
        verify(resultSet, atLeast(1)).getInt((String) any());
        verify(resultSet, atLeast(1)).getString((String) any());
    }

    /**
     * Method under test: {@link UserDao#userLogin(String, String)}
     */
    @Test
    void testUserLogin4() throws SQLException
    {
        ResultSet resultSet = mock(ResultSet.class);
        when(resultSet.getInt((String) any())).thenThrow(new SQLException());
        when(resultSet.getString((String) any())).thenThrow(new SQLException());
        when(resultSet.next()).thenReturn(true).thenReturn(true).thenReturn(false);
        PreparedStatement preparedStatement = mock(PreparedStatement.class);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        doNothing().when(preparedStatement).setString(anyInt(), (String) any());
        Connection connection = mock(Connection.class);
        when(connection.prepareStatement((String) any())).thenReturn(preparedStatement);
        assertNull((new UserDao(connection)).userLogin("SELECT * FROM users WHERE email = ? and password = SHA2(?, 512);",
                "iloveyou"));
        verify(connection).prepareStatement((String) any());
        verify(preparedStatement).executeQuery();
        verify(preparedStatement, atLeast(1)).setString(anyInt(), (String) any());
        verify(resultSet).next();
        verify(resultSet).getInt((String) any());
    }


    /**
     * Method under test: {@link UserDao#userRegister(String, String, String, String, String, String, String, String, String)}
     */
    @Test
    void testUserRegister3() throws SQLException
    {
        ResultSet resultSet = mock(ResultSet.class);
        when(resultSet.next()).thenReturn(true).thenReturn(true).thenReturn(false);
        PreparedStatement preparedStatement = mock(PreparedStatement.class);
        when(preparedStatement.executeUpdate()).thenReturn(1);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        doNothing().when(preparedStatement).setString(anyInt(), (String) any());
        Connection connection = mock(Connection.class);
        when(connection.prepareStatement((String) any())).thenReturn(preparedStatement);
        assertEquals(-1,
                (new UserDao(connection)).userRegister(
                        "SELECT * FROM users WHERE email = ? OR password = SHA2(? , 512) OR phoneNumber = ?;", "Doe", "4105551212",
                        "jane.doe@example.org", "iloveyou", "42 Main St", "Oxford", "21654", "GB"));
        verify(connection).prepareStatement((String) any());
        verify(preparedStatement).executeQuery();
        verify(preparedStatement, atLeast(1)).setString(anyInt(), (String) any());
        verify(resultSet).next();
    }

    /**
     * Method under test: {@link UserDao#userRegister(String, String, String, String, String, String, String, String, String)}
     */
    @Test
    void testUserRegister4() throws SQLException
    {
        ResultSet resultSet = mock(ResultSet.class);
        when(resultSet.next()).thenThrow(new SQLException());
        PreparedStatement preparedStatement = mock(PreparedStatement.class);
        when(preparedStatement.executeUpdate()).thenReturn(1);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        doNothing().when(preparedStatement).setString(anyInt(), (String) any());
        Connection connection = mock(Connection.class);
        when(connection.prepareStatement((String) any())).thenReturn(preparedStatement);
        assertEquals(0,
                (new UserDao(connection)).userRegister(
                        "SELECT * FROM users WHERE email = ? OR password = SHA2(? , 512) OR phoneNumber = ?;", "Doe", "4105551212",
                        "jane.doe@example.org", "iloveyou", "42 Main St", "Oxford", "21654", "GB"));
        verify(connection).prepareStatement((String) any());
        verify(preparedStatement).executeQuery();
        verify(preparedStatement, atLeast(1)).setString(anyInt(), (String) any());
        verify(resultSet).next();
    }


    /**
     * Method under test: {@link UserDao#adminUserRegister(String, String, String, String, String, String, String, String, String, int)}
     */
    @Test
    void testAdminUserRegister3() throws SQLException
    {
        ResultSet resultSet = mock(ResultSet.class);
        when(resultSet.next()).thenReturn(true).thenReturn(true).thenReturn(false);
        PreparedStatement preparedStatement = mock(PreparedStatement.class);
        when(preparedStatement.executeUpdate()).thenReturn(1);
        doNothing().when(preparedStatement).setInt(anyInt(), anyInt());
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        doNothing().when(preparedStatement).setString(anyInt(), (String) any());
        Connection connection = mock(Connection.class);
        when(connection.prepareStatement((String) any())).thenReturn(preparedStatement);
        assertEquals(-1,
                (new UserDao(connection)).adminUserRegister(
                        "SELECT * FROM users WHERE email = ? OR password = SHA2(? , 512) OR phoneNumber = ?;", "Doe", "4105551212",
                        "jane.doe@example.org", "iloveyou", "42 Main St", "Oxford", "21654", "GB", 1));
        verify(connection).prepareStatement((String) any());
        verify(preparedStatement).executeQuery();
        verify(preparedStatement, atLeast(1)).setString(anyInt(), (String) any());
        verify(resultSet).next();
    }

    /**
     * Method under test: {@link UserDao#adminUserRegister(String, String, String, String, String, String, String, String, String, int)}
     */
    @Test
    void testAdminUserRegister4() throws SQLException
    {
        ResultSet resultSet = mock(ResultSet.class);
        when(resultSet.next()).thenThrow(new SQLException());
        PreparedStatement preparedStatement = mock(PreparedStatement.class);
        when(preparedStatement.executeUpdate()).thenReturn(1);
        doNothing().when(preparedStatement).setInt(anyInt(), anyInt());
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        doNothing().when(preparedStatement).setString(anyInt(), (String) any());
        Connection connection = mock(Connection.class);
        when(connection.prepareStatement((String) any())).thenReturn(preparedStatement);
        assertEquals(0,
                (new UserDao(connection)).adminUserRegister(
                        "SELECT * FROM users WHERE email = ? OR password = SHA2(? , 512) OR phoneNumber = ?;", "Doe", "4105551212",
                        "jane.doe@example.org", "iloveyou", "42 Main St", "Oxford", "21654", "GB", 1));
        verify(connection).prepareStatement((String) any());
        verify(preparedStatement).executeQuery();
        verify(preparedStatement, atLeast(1)).setString(anyInt(), (String) any());
        verify(resultSet).next();
    }

    /**
     * Method under test: {@link UserDao#selectAllUsers()}
     */
    @Test
    void testSelectAllUsers() throws SQLException
    {
        ResultSet resultSet = mock(ResultSet.class);
        when(resultSet.getInt((String) any())).thenReturn(1);
        when(resultSet.getString((String) any())).thenReturn("String");
        when(resultSet.next()).thenReturn(true).thenReturn(true).thenReturn(false);
        PreparedStatement preparedStatement = mock(PreparedStatement.class);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        Connection connection = mock(Connection.class);
        when(connection.prepareStatement((String) any())).thenReturn(preparedStatement);
        assertEquals(2, (new UserDao(connection)).selectAllUsers().size());
        verify(connection).prepareStatement((String) any());
        verify(preparedStatement).executeQuery();
        verify(resultSet, atLeast(1)).next();
        verify(resultSet, atLeast(1)).getInt((String) any());
        verify(resultSet, atLeast(1)).getString((String) any());
    }

    /**
     * Method under test: {@link UserDao#selectAllUsers()}
     */
    @Test
    void testSelectAllUsers2() throws SQLException
    {
        ResultSet resultSet = mock(ResultSet.class);
        when(resultSet.getInt((String) any())).thenThrow(new SQLException());
        when(resultSet.getString((String) any())).thenThrow(new SQLException());
        when(resultSet.next()).thenReturn(true).thenReturn(true).thenReturn(false);
        PreparedStatement preparedStatement = mock(PreparedStatement.class);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        Connection connection = mock(Connection.class);
        when(connection.prepareStatement((String) any())).thenReturn(preparedStatement);
        assertTrue((new UserDao(connection)).selectAllUsers().isEmpty());
        verify(connection).prepareStatement((String) any());
        verify(preparedStatement).executeQuery();
        verify(resultSet).next();
        verify(resultSet).getInt((String) any());
    }

    /**
     * Method under test: {@link UserDao#deleteUser(int)}
     */
    @Test
    void testDeleteUser() throws SQLException
    {
        PreparedStatement preparedStatement = mock(PreparedStatement.class);
        when(preparedStatement.executeUpdate()).thenReturn(1);
        doNothing().when(preparedStatement).setInt(anyInt(), anyInt());
        Connection connection = mock(Connection.class);
        when(connection.prepareStatement((String) any())).thenReturn(preparedStatement);
        assertTrue((new UserDao(connection)).deleteUser(1));
        verify(connection).prepareStatement((String) any());
        verify(preparedStatement).executeUpdate();
        verify(preparedStatement).setInt(anyInt(), anyInt());
    }

    /**
     * Method under test: {@link UserDao#deleteUser(int)}
     */
    @Test
    void testDeleteUser2() throws SQLException
    {
        PreparedStatement preparedStatement = mock(PreparedStatement.class);
        when(preparedStatement.executeUpdate()).thenThrow(new SQLException());
        doThrow(new SQLException()).when(preparedStatement).setInt(anyInt(), anyInt());
        Connection connection = mock(Connection.class);
        when(connection.prepareStatement((String) any())).thenReturn(preparedStatement);
        assertFalse((new UserDao(connection)).deleteUser(1));
        verify(connection).prepareStatement((String) any());
        verify(preparedStatement).setInt(anyInt(), anyInt());
    }

    /**
     * Method under test: {@link UserDao#updateUser(int, String, String, String, String, String, String, String, String, int)}
     */
    @Test
    void testUpdateUser3() throws SQLException
    {
        ResultSet resultSet = mock(ResultSet.class);
        when(resultSet.getInt((String) any())).thenReturn(1);
        when(resultSet.next()).thenReturn(true).thenReturn(true).thenReturn(false);
        PreparedStatement preparedStatement = mock(PreparedStatement.class);
        when(preparedStatement.executeUpdate()).thenReturn(1);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        doNothing().when(preparedStatement).setInt(anyInt(), anyInt());
        doNothing().when(preparedStatement).setString(anyInt(), (String) any());
        Connection connection = mock(Connection.class);
        when(connection.prepareStatement((String) any())).thenReturn(preparedStatement);
        assertEquals(1,
                (new UserDao(connection)).updateUser(123,
                        "SELECT COUNT(*) AS total FROM users WHERE email = ? OR phoneNumber = ? AND NOT userId = ?;", "Doe",
                        "4105551212", "jane.doe@example.org", "42 Main St", "Oxford", "21654", "GB", 1));
        verify(connection, atLeast(1)).prepareStatement((String) any());
        verify(preparedStatement).executeUpdate();
        verify(preparedStatement).executeQuery();
        verify(preparedStatement, atLeast(1)).setInt(anyInt(), anyInt());
        verify(preparedStatement, atLeast(1)).setString(anyInt(), (String) any());
        verify(resultSet, atLeast(1)).next();
        verify(resultSet, atLeast(1)).getInt((String) any());
    }

    /**
     * Method under test: {@link UserDao#updateUser(int, String, String, String, String, String, String, String, String, int)}
     */
    @Test
    void testUpdateUser4() throws SQLException
    {
        ResultSet resultSet = mock(ResultSet.class);
        when(resultSet.getInt((String) any())).thenThrow(new SQLException());
        when(resultSet.next()).thenReturn(true).thenReturn(true).thenReturn(false);
        PreparedStatement preparedStatement = mock(PreparedStatement.class);
        when(preparedStatement.executeUpdate()).thenReturn(1);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        doNothing().when(preparedStatement).setInt(anyInt(), anyInt());
        doNothing().when(preparedStatement).setString(anyInt(), (String) any());
        Connection connection = mock(Connection.class);
        when(connection.prepareStatement((String) any())).thenReturn(preparedStatement);
        assertEquals(0,
                (new UserDao(connection)).updateUser(123,
                        "SELECT COUNT(*) AS total FROM users WHERE email = ? OR phoneNumber = ? AND NOT userId = ?;", "Doe",
                        "4105551212", "jane.doe@example.org", "42 Main St", "Oxford", "21654", "GB", 1));
        verify(connection).prepareStatement((String) any());
        verify(preparedStatement).executeQuery();
        verify(preparedStatement).setInt(anyInt(), anyInt());
        verify(preparedStatement, atLeast(1)).setString(anyInt(), (String) any());
        verify(resultSet).next();
        verify(resultSet).getInt((String) any());
    }
}

