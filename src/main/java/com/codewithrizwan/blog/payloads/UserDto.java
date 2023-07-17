package com.codewithrizwan.blog.payloads;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;


@Data
public class UserDto {
	
	
	private int id;
	
	@NotEmpty
	@Size(min = 4,message = "Username must be of minimun 4 characters !!")
	private String name ;
	
	@Email(message = "Email address is not valid")
	private String email;
	
	@NotEmpty
	@Size(min=4,max=20, message = "Password must be min of 4 characters and max of 20 characters!!")
	private String password;
	
	@NotNull
	private String about;
	
	
	
}
