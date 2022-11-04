package com.security.jdbc.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
@Configuration
public class SecurityConfig  extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		auth.jdbcAuthentication()
		
		//create database connection 
		.dataSource(dataSource)
		
		//fetch uname,upwd,enabled using username input entered in Login page
		.usersByUsernameQuery("select uname,upwd,uenabled from user where uname=?")
		////fetch uname,upwd,enabled using username input entered in Login page
		
		.authoritiesByUsernameQuery("select uname,urole from user where uname=?")
		
		//provide password Encoder Object reference
		.passwordEncoder(passwordEncoder);
		
		
		
//		auth.inMemoryAuthentication().withUser("saurabh").password(this.passwordEncoder().encode("saurabh")).authorities("STUDENT","EMPLOYEE","ADMIN");
//		auth.inMemoryAuthentication().withUser("jai").password(this.passwordEncoder().encode("jai")).authorities("STUDENT");
//		
		
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers("/home").permitAll()
		.antMatchers("/welcome").authenticated()
		.antMatchers("/admin").hasAuthority("ADMIN")
		.antMatchers("/student").hasAuthority("STUDENT")
		.antMatchers("/employee").hasAuthority("EMPLOYEE")
		
		.and()
		.formLogin()
		.defaultSuccessUrl("/welcome",true)
		
		.and()
		.logout()
		.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
		
		.and()
		.exceptionHandling()
	.accessDeniedPage("/denied");
				
	}
	
//	@Bean
//	public BCryptPasswordEncoder passwordEncoder() {
//		
//		return new BCryptPasswordEncoder();
//	}

}

