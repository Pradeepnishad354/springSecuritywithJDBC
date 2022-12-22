package com.practice.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.practice.service.UserService;

@Configuration
@EnableWebSecurity
@Order(1)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter  {
	
//	@Autowired
//	private BCryptPasswordEncoder passwordEncoder;
//	
	@Autowired
	private DataSource dataSource;
	
	
	@Autowired
	private UserService userService;
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider auth=new DaoAuthenticationProvider();
		
		auth.setUserDetailsService(userService);
		auth.setPasswordEncoder(passwordEncoder());
		
		
		return auth;
		
		
	}
	
	
	

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider());
		
		auth.jdbcAuthentication();
//		.dataSource(dataSource).usersByUsernameQuery("select uname,upwd,enable from usertab where uname=?").
//		authoritiesByUsernameQuery("select uname,urole from usertab where uname=?")
//		.passwordEncoder(new BCryptPasswordEncoder());
		
		
//	auth.inMemoryAuthentication().withUser("amanverma@gmail.com").password(this.passwordEncoder().encode("123456")).authorities("ADMIN");
//    auth.inMemoryAuthentication().withUser("pradeepnishad@gmail.com").password(this.passwordEncoder().encode("123456")).authorities("USER");
//	
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/registration**").permitAll()
//		.antMatchers("/registration**").hasRole("ADMIN")
//	    .antMatchers(("/userlogin")).hasRole("USER")	
		.anyRequest().authenticated()
		.and()
		.formLogin()
		.loginPage("/login")
		.permitAll()
		.and()
		.logout()
		.invalidateHttpSession(true)
		.clearAuthentication(true)
		.logoutRequestMatcher(new  AntPathRequestMatcher("/logout"))
    	.logoutSuccessUrl("/login?logout");
		
		
		
//		http.authorizeRequests().antMatchers("/userlogin").hasRole("User")
//		.and()
//		.formLogin()
//		.loginPage("/userlogin").permitAll()
//		.and()
//		.logout()
//		.invalidateHttpSession(true)
//		.clearAuthentication(true)
//		.logoutRequestMatcher(new  AntPathRequestMatcher("/logout"))
//    	.logoutSuccessUrl("/userlogin?logout");
		
	}

}
