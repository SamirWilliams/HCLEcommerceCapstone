package com.ecommercecapstone.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class UserTest
{
    /**
     * Method under test: {@link User#canEqual(Object)}
     */
    @Test
    void testCanEqual()
    {
        assertFalse((new User(123, "Jane", "Doe", "4105551212", "42 Main St", "Oxford", "21654", "GB",
                "jane.doe@example.org", true)).canEqual("Other"));
    }

    /**
     * Method under test: {@link User#canEqual(Object)}
     */
    @Test
    void testCanEqual2()
    {
        User user = new User(123, "Jane", "Doe", "4105551212", "42 Main St", "Oxford", "21654", "GB",
                "jane.doe@example.org", true);
        assertTrue(user.canEqual(new User(123, "Jane", "Doe", "4105551212", "42 Main St", "Oxford", "21654", "GB",
                "jane.doe@example.org", true)));
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link User#User()}
     *   <li>{@link User#setAddress(String)}
     *   <li>{@link User#setAdmin(boolean)}
     *   <li>{@link User#setCity(String)}
     *   <li>{@link User#setCountry(String)}
     *   <li>{@link User#setEmail(String)}
     *   <li>{@link User#setFirstName(String)}
     *   <li>{@link User#setLastName(String)}
     *   <li>{@link User#setPhoneNumber(String)}
     *   <li>{@link User#setUserId(int)}
     *   <li>{@link User#setZipCode(String)}
     *   <li>{@link User#toString()}
     *   <li>{@link User#getAddress()}
     *   <li>{@link User#getCity()}
     *   <li>{@link User#getCountry()}
     *   <li>{@link User#getEmail()}
     *   <li>{@link User#getFirstName()}
     *   <li>{@link User#getLastName()}
     *   <li>{@link User#getPhoneNumber()}
     *   <li>{@link User#getUserId()}
     *   <li>{@link User#getZipCode()}
     *   <li>{@link User#isAdmin()}
     * </ul>
     */
    @Test
    void testConstructor()
    {
        User actualUser = new User();
        actualUser.setAddress("42 Main St");
        actualUser.setAdmin(true);
        actualUser.setCity("Oxford");
        actualUser.setCountry("GB");
        actualUser.setEmail("jane.doe@example.org");
        actualUser.setFirstName("Jane");
        actualUser.setLastName("Doe");
        actualUser.setPhoneNumber("4105551212");
        actualUser.setUserId(123);
        actualUser.setZipCode("21654");
        String actualToStringResult = actualUser.toString();
        assertEquals("42 Main St", actualUser.getAddress());
        assertEquals("Oxford", actualUser.getCity());
        assertEquals("GB", actualUser.getCountry());
        assertEquals("jane.doe@example.org", actualUser.getEmail());
        assertEquals("Jane", actualUser.getFirstName());
        assertEquals("Doe", actualUser.getLastName());
        assertEquals("4105551212", actualUser.getPhoneNumber());
        assertEquals(123, actualUser.getUserId());
        assertEquals("21654", actualUser.getZipCode());
        assertTrue(actualUser.isAdmin());
        assertEquals(
                "User(userId=123, firstName=Jane, lastName=Doe, phoneNumber=4105551212, address=42 Main St, city=Oxford,"
                        + " zipCode=21654, country=GB, email=jane.doe@example.org, isAdmin=true)",
                actualToStringResult);
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link User#User(int, String, String, String, String, String, String, String, String, boolean)}
     *   <li>{@link User#setAddress(String)}
     *   <li>{@link User#setAdmin(boolean)}
     *   <li>{@link User#setCity(String)}
     *   <li>{@link User#setCountry(String)}
     *   <li>{@link User#setEmail(String)}
     *   <li>{@link User#setFirstName(String)}
     *   <li>{@link User#setLastName(String)}
     *   <li>{@link User#setPhoneNumber(String)}
     *   <li>{@link User#setUserId(int)}
     *   <li>{@link User#setZipCode(String)}
     *   <li>{@link User#toString()}
     *   <li>{@link User#getAddress()}
     *   <li>{@link User#getCity()}
     *   <li>{@link User#getCountry()}
     *   <li>{@link User#getEmail()}
     *   <li>{@link User#getFirstName()}
     *   <li>{@link User#getLastName()}
     *   <li>{@link User#getPhoneNumber()}
     *   <li>{@link User#getUserId()}
     *   <li>{@link User#getZipCode()}
     *   <li>{@link User#isAdmin()}
     * </ul>
     */
    @Test
    void testConstructor2()
    {
        User actualUser = new User(123, "Jane", "Doe", "4105551212", "42 Main St", "Oxford", "21654", "GB",
                "jane.doe@example.org", true);
        actualUser.setAddress("42 Main St");
        actualUser.setAdmin(true);
        actualUser.setCity("Oxford");
        actualUser.setCountry("GB");
        actualUser.setEmail("jane.doe@example.org");
        actualUser.setFirstName("Jane");
        actualUser.setLastName("Doe");
        actualUser.setPhoneNumber("4105551212");
        actualUser.setUserId(123);
        actualUser.setZipCode("21654");
        String actualToStringResult = actualUser.toString();
        assertEquals("42 Main St", actualUser.getAddress());
        assertEquals("Oxford", actualUser.getCity());
        assertEquals("GB", actualUser.getCountry());
        assertEquals("jane.doe@example.org", actualUser.getEmail());
        assertEquals("Jane", actualUser.getFirstName());
        assertEquals("Doe", actualUser.getLastName());
        assertEquals("4105551212", actualUser.getPhoneNumber());
        assertEquals(123, actualUser.getUserId());
        assertEquals("21654", actualUser.getZipCode());
        assertTrue(actualUser.isAdmin());
        assertEquals(
                "User(userId=123, firstName=Jane, lastName=Doe, phoneNumber=4105551212, address=42 Main St, city=Oxford,"
                        + " zipCode=21654, country=GB, email=jane.doe@example.org, isAdmin=true)",
                actualToStringResult);
    }

    /**
     * Method under test: {@link User#equals(Object)}
     */
    @Test
    void testEquals()
    {
        assertNotEquals(
                new User(123, "Jane", "Doe", "4105551212", "42 Main St", "Oxford", "21654", "GB", "jane.doe@example.org", true),
                null);
        assertNotEquals(
                new User(123, "Jane", "Doe", "4105551212", "42 Main St", "Oxford", "21654", "GB", "jane.doe@example.org", true),
                "Different type to User");
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link User#equals(Object)}
     *   <li>{@link User#hashCode()}
     * </ul>
     */
    @Test
    void testEquals2()
    {
        User user = new User(123, "Jane", "Doe", "4105551212", "42 Main St", "Oxford", "21654", "GB",
                "jane.doe@example.org", true);
        assertEquals(user, user);
        int expectedHashCodeResult = user.hashCode();
        assertEquals(expectedHashCodeResult, user.hashCode());
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link User#equals(Object)}
     *   <li>{@link User#hashCode()}
     * </ul>
     */
    @Test
    void testEquals3()
    {
        User user = new User(123, "Jane", "Doe", "4105551212", "42 Main St", "Oxford", "21654", "GB",
                "jane.doe@example.org", true);
        User user1 = new User(123, "Jane", "Doe", "4105551212", "42 Main St", "Oxford", "21654", "GB",
                "jane.doe@example.org", true);

        assertEquals(user, user1);
        int expectedHashCodeResult = user.hashCode();
        assertEquals(expectedHashCodeResult, user1.hashCode());
    }

    /**
     * Method under test: {@link User#equals(Object)}
     */
    @Test
    void testEquals4()
    {
        User user = new User(1, "Jane", "Doe", "4105551212", "42 Main St", "Oxford", "21654", "GB", "jane.doe@example.org",
                true);
        assertNotEquals(user, new User(123, "Jane", "Doe", "4105551212", "42 Main St", "Oxford", "21654", "GB",
                "jane.doe@example.org", true));
    }

    /**
     * Method under test: {@link User#equals(Object)}
     */
    @Test
    void testEquals5()
    {
        User user = new User(123, "Doe", "Doe", "4105551212", "42 Main St", "Oxford", "21654", "GB", "jane.doe@example.org",
                true);
        assertNotEquals(user, new User(123, "Jane", "Doe", "4105551212", "42 Main St", "Oxford", "21654", "GB",
                "jane.doe@example.org", true));
    }

    /**
     * Method under test: {@link User#equals(Object)}
     */
    @Test
    void testEquals6()
    {
        User user = new User(123, null, "Doe", "4105551212", "42 Main St", "Oxford", "21654", "GB", "jane.doe@example.org",
                true);
        assertNotEquals(user, new User(123, "Jane", "Doe", "4105551212", "42 Main St", "Oxford", "21654", "GB",
                "jane.doe@example.org", true));
    }

    /**
     * Method under test: {@link User#equals(Object)}
     */
    @Test
    void testEquals7()
    {
        User user = new User(123, "Jane", "Jane", "4105551212", "42 Main St", "Oxford", "21654", "GB",
                "jane.doe@example.org", true);
        assertNotEquals(user, new User(123, "Jane", "Doe", "4105551212", "42 Main St", "Oxford", "21654", "GB",
                "jane.doe@example.org", true));
    }

    /**
     * Method under test: {@link User#equals(Object)}
     */
    @Test
    void testEquals8()
    {
        User user = new User(123, "Jane", null, "4105551212", "42 Main St", "Oxford", "21654", "GB", "jane.doe@example.org",
                true);
        assertNotEquals(user, new User(123, "Jane", "Doe", "4105551212", "42 Main St", "Oxford", "21654", "GB",
                "jane.doe@example.org", true));
    }

    /**
     * Method under test: {@link User#equals(Object)}
     */
    @Test
    void testEquals9()
    {
        User user = new User(123, "Jane", "Doe", "+44 1865 4960636", "42 Main St", "Oxford", "21654", "GB",
                "jane.doe@example.org", true);
        assertNotEquals(user, new User(123, "Jane", "Doe", "4105551212", "42 Main St", "Oxford", "21654", "GB",
                "jane.doe@example.org", true));
    }

    /**
     * Method under test: {@link User#equals(Object)}
     */
    @Test
    void testEquals10()
    {
        User user = new User(123, "Jane", "Doe", null, "42 Main St", "Oxford", "21654", "GB", "jane.doe@example.org", true);
        assertNotEquals(user, new User(123, "Jane", "Doe", "4105551212", "42 Main St", "Oxford", "21654", "GB",
                "jane.doe@example.org", true));
    }

    /**
     * Method under test: {@link User#equals(Object)}
     */
    @Test
    void testEquals11()
    {
        User user = new User(123, "Jane", "Doe", "4105551212", "Jane", "Oxford", "21654", "GB", "jane.doe@example.org",
                true);
        assertNotEquals(user, new User(123, "Jane", "Doe", "4105551212", "42 Main St", "Oxford", "21654", "GB",
                "jane.doe@example.org", true));
    }

    /**
     * Method under test: {@link User#equals(Object)}
     */
    @Test
    void testEquals12()
    {
        User user = new User(123, "Jane", "Doe", "4105551212", null, "Oxford", "21654", "GB", "jane.doe@example.org", true);
        assertNotEquals(user, new User(123, "Jane", "Doe", "4105551212", "42 Main St", "Oxford", "21654", "GB",
                "jane.doe@example.org", true));
    }

    /**
     * Method under test: {@link User#equals(Object)}
     */
    @Test
    void testEquals13()
    {
        User user = new User(123, "Jane", "Doe", "4105551212", "42 Main St", "Jane", "21654", "GB", "jane.doe@example.org",
                true);
        assertNotEquals(user, new User(123, "Jane", "Doe", "4105551212", "42 Main St", "Oxford", "21654", "GB",
                "jane.doe@example.org", true));
    }

    /**
     * Method under test: {@link User#equals(Object)}
     */
    @Test
    void testEquals14()
    {
        User user = new User(123, "Jane", "Doe", "4105551212", "42 Main St", null, "21654", "GB", "jane.doe@example.org",
                true);
        assertNotEquals(user, new User(123, "Jane", "Doe", "4105551212", "42 Main St", "Oxford", "21654", "GB",
                "jane.doe@example.org", true));
    }

    /**
     * Method under test: {@link User#equals(Object)}
     */
    @Test
    void testEquals15()
    {
        User user = new User(123, "Jane", "Doe", "4105551212", "42 Main St", "Oxford", "OX1 1PT", "GB",
                "jane.doe@example.org", true);
        assertNotEquals(user, new User(123, "Jane", "Doe", "4105551212", "42 Main St", "Oxford", "21654", "GB",
                "jane.doe@example.org", true));
    }

    /**
     * Method under test: {@link User#equals(Object)}
     */
    @Test
    void testEquals16()
    {
        User user = new User(123, "Jane", "Doe", "4105551212", "42 Main St", "Oxford", null, "GB", "jane.doe@example.org",
                true);
        assertNotEquals(user, new User(123, "Jane", "Doe", "4105551212", "42 Main St", "Oxford", "21654", "GB",
                "jane.doe@example.org", true));
    }

    /**
     * Method under test: {@link User#equals(Object)}
     */
    @Test
    void testEquals17()
    {
        User user = new User(123, "Jane", "Doe", "4105551212", "42 Main St", "Oxford", "21654", "GBR",
                "jane.doe@example.org", true);
        assertNotEquals(user, new User(123, "Jane", "Doe", "4105551212", "42 Main St", "Oxford", "21654", "GB",
                "jane.doe@example.org", true));
    }

    /**
     * Method under test: {@link User#equals(Object)}
     */
    @Test
    void testEquals18()
    {
        User user = new User(123, "Jane", "Doe", "4105551212", "42 Main St", "Oxford", "21654", null,
                "jane.doe@example.org", true);
        assertNotEquals(user, new User(123, "Jane", "Doe", "4105551212", "42 Main St", "Oxford", "21654", "GB",
                "jane.doe@example.org", true));
    }

    /**
     * Method under test: {@link User#equals(Object)}
     */
    @Test
    void testEquals19()
    {
        User user = new User(123, "Jane", "Doe", "4105551212", "42 Main St", "Oxford", "21654", "GB", "Jane", true);
        assertNotEquals(user, new User(123, "Jane", "Doe", "4105551212", "42 Main St", "Oxford", "21654", "GB",
                "jane.doe@example.org", true));
    }

    /**
     * Method under test: {@link User#equals(Object)}
     */
    @Test
    void testEquals20()
    {
        User user = new User(123, "Jane", "Doe", "4105551212", "42 Main St", "Oxford", "21654", "GB", null, true);
        assertNotEquals(user, new User(123, "Jane", "Doe", "4105551212", "42 Main St", "Oxford", "21654", "GB",
                "jane.doe@example.org", true));
    }

    /**
     * Method under test: {@link User#equals(Object)}
     */
    @Test
    void testEquals21()
    {
        User user = new User(123, "Jane", "Doe", "4105551212", "42 Main St", "Oxford", "21654", "GB",
                "jane.doe@example.org", false);
        assertNotEquals(user, new User(123, "Jane", "Doe", "4105551212", "42 Main St", "Oxford", "21654", "GB",
                "jane.doe@example.org", true));
    }
}

