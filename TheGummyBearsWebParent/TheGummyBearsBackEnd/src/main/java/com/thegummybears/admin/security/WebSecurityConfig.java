package com.thegummybears.admin.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * 
 *[29]
 *
 */
 @Configuration
 @EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter  {

	
	//[29] - To access homeage without login
	/*
	 * @Override protected void configure(HttpSecurity http) throws Exception {
	 * http.authorizeRequests().anyRequest().permitAll(); }
	 */
	 
	
	//[29]
	  @Bean 
	  public PasswordEncoder passwordEndCoder() { 
		 return new BCryptPasswordEncoder(); 
	}
	
	  
	  //[48 - User login page]
	  @Override 
	  protected void configure(HttpSecurity http) throws Exception {
	  http.authorizeRequests()
	  .anyRequest()
	  .authenticated()
	  .and().formLogin()
	  			.loginPage("/login")
	  			.usernameParameter("email")
	  			.permitAll()
	  //[53-enable remeber me]
		
		  .and().rememberMe() .key("AbcDefgHijKlmnOpqrs_1234567890")
		  .tokenValiditySeconds(7 * 24 * 60 * 60);
		  //7days 24hrs 60Min 60Sec
	  }
	  
	  //[48 - User login]
	  @Override
		public void configure(WebSecurity web) throws Exception {
			web.ignoring().antMatchers("/images/**", "/js/**", "/webjars/**");
		}
	  
	  
	  //[49 - user Authen]
	  @Bean
	  public UserDetailsService userDetailsService() {
		  return new ThegummybearsDetailsService();
	  }
	  
	  //[49]
		public DaoAuthenticationProvider authenticationProvider() {
			DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
			authProvider.setUserDetailsService(userDetailsService());
			authProvider.setPasswordEncoder(passwordEndCoder());
			return authProvider;
		}
	 
		//[49 - ready to test]
		@Override
		protected void configure(AuthenticationManagerBuilder auth) throws Exception {
			auth.authenticationProvider(authenticationProvider());
		}
}
