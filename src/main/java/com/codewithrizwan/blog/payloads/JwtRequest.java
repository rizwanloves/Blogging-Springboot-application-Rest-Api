package com.codewithrizwan.blog.payloads;

import lombok.Data;

@Data
public class JwtRequest {
	private String email;
	private String password;
}
