package com.learning.example.basic.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
	
	  @Autowired 
	  PasswordEncoder passwordEncoder;
	  
	  @Bean 
	  public PasswordEncoder passwordEncoder() { 
		  return new BCryptPasswordEncoder(); 
	  }
	
	// Create 2 users for demo
	@Override 
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	  
		auth.inMemoryAuthentication()
				//.withUser("commonuser").password("{noop}textpassword").roles("USER");
	  			.withUser("user").password(passwordEncoder.encode("useruser")).roles("USER").and()
	  			.withUser("admin").password(passwordEncoder.encode("adamadmin")).roles("USER", "ADMIN").and()
	  			.withUser("superadmin").password(passwordEncoder.encode("poweradmin")).roles("USER", "ADMIN", "SUPER_ADMIN");
	}
	 

    // Secure the endpoins with HTTP Basic authentication
    @Override
    protected void configure(HttpSecurity http) throws Exception {

    	//HTTP Basic authentication
        http.httpBasic()
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/books/**").hasRole("USER")
                .antMatchers(HttpMethod.POST, "/books").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT, "/books/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.PATCH, "/books/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/books/**").hasRole("SUPER_ADMIN")
                .and()
                .csrf().disable()
                .formLogin().disable();
    }

    /*@Bean
    public UserDetailsService userDetailsService() {
        //ok for demo
        User.UserBuilder users = User.withDefaultPasswordEncoder();
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(users.username("user").password("password").roles("USER").build());
        manager.createUser(users.username("admin").password("password").roles("USER", "ADMIN").build());
        return manager;
    }*/
}
