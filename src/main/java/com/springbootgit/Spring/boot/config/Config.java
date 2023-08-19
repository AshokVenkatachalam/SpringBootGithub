package com.springbootgit.Spring.boot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class Config {
	
	
	@Bean
	public UserDetailsService UserDetailsService(PasswordEncoder encoder) {
		
		UserDetails admin=User.withUsername("Ashok")
				.password(encoder.encode("1212"))
				.roles("ADMIN")
				.build();
		
		UserDetails admin1=User.withUsername("Raja")
				.password(encoder.encode("1234"))
				.roles("USER")
				.build();
		
		return new InMemoryUserDetailsManager(admin,admin1);	
	}
	
	@Bean
	public PasswordEncoder PasswordEncoder() {
		return new BCryptPasswordEncoder();
		
		
	}
	@Bean
	public SecurityFilterChain securityfilterchain(HttpSecurity http) throws Exception{
		
		http.csrf().disable()
		.authorizeHttpRequests()
		.antMatchers("/api/welcome").permitAll()
		.and()
		.authorizeHttpRequests().antMatchers("/api/**").authenticated()
		.and().formLogin()
		.and().build();
		
		
		
		
		return null;
		
		
	}

}
