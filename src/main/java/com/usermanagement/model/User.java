package com.usermanagement.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
	private int userId;
	private String firstName;
	private String lastName;
	private String phoneNumber;
	private String address;
	private String city;
	private String zipCode;
	private String country;
	private String email;
	private boolean isAdmin;
}

