
package com.practice.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration

@Order(2)
@EnableWebSecurity
public  class UserSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private DataSource dataSource;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication().dataSource(dataSource)
				.usersByUsernameQuery("select uname,upwd,enable from usertab where uname=?")
				.authoritiesByUsernameQuery("select uname,urole from usertab where uname=?")
				.passwordEncoder(new BCryptPasswordEncoder());

	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests().
		antMatchers("/userlogin").hasRole("User").
		anyRequest().authenticated().and()
				.formLogin()
				.loginPage("/userlogin")
				.permitAll().and().logout().
				invalidateHttpSession(true)
				.clearAuthentication(true).
				logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
				.logoutSuccessUrl("/userlogin?logout");
				
				
				
	}

}
