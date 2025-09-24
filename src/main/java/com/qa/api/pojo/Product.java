package com.qa.api.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class Product {

	private Integer Id;
	private String title;
	private double price;
	private String description;
	private String category;
	private String Image;
	private Rating rating;
	
	@Data
	@Builder
	@AllArgsConstructor
	@NoArgsConstructor
	public static class Rating {
		private double rate;
		private double count;
		
	}
	
	
	
	
}
