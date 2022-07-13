package com.usermanagement.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order extends Product{

	private int orderId;
	private int userId;
	private double orderPrice;
	private int quantity;

}
