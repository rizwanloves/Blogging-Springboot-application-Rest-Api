package com.codewithrizwan.blog.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.codewithrizwan.blog.security.JwtAuthenticationEntryPoint;
import com.codewithrizwan.blog.security.JwtAuthenticationFilter;


@EnableWebSecurity
@EnableWebMvc
@Configuration
@EnableMethodSecurity
public class SecurityConfig {

	 public static final String[] PUBLIC_URLS = {"/api/v1/auth/**", "/v3/api-docs/**", "/v2/api-docs",
	            "/swagger-resources/**", "/swagger-ui/**", "/webjars/**","/api/users/"

	    };

    @Autowired
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;
    
    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

    	  http.
          csrf()
          .disable()
          .authorizeHttpRequests()
          .requestMatchers(PUBLIC_URLS)
          .permitAll()
          .requestMatchers(HttpMethod.GET)
          .permitAll()
          .anyRequest()
          .authenticated()
          .and().exceptionHandling()
          .authenticationEntryPoint(this.jwtAuthenticationEntryPoint)
          .and()
          .sessionManagement()
          .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

  http.addFilterBefore(this.jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    
    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {

        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(this.userDetailsService);
        provider.setPasswordEncoder(passwordEncoder);
        return provider;

    }

}