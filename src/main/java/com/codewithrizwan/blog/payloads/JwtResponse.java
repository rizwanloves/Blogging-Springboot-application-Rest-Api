package com.codewithrizwan.blog.payloads;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class JwtResponse {
	private String jwtToken;
	private String username;
}
