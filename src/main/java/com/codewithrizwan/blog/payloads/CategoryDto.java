package com.codewithrizwan.blog.payloads;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CategoryDto {
	private Integer categoryId;
	
	@NotBlank
	@Size(min = 4,message = "Minimum size of 4 characters")
	private String categoryTitle;
	
	@NotBlank
	@Size(min = 10,message = "Minimum size of 10 characters")
	private String categoryDescription;
}
