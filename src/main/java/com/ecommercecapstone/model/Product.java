package com.ecommercecapstone.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
	private int productId;
	private String productName;
	private String productImage;
	private double unitPrice;
	private String category;
}
