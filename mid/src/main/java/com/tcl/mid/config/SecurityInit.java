package com.tcl.mid.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
//@EnableGlobalMethodSecurity(prePostEnabled = true)
//@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
public class SecurityInit extends WebSecurityConfigurerAdapter {
	/** variable created for the logger */
	private static Logger logger = LogManager.getLogger();

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		logger.info("----------------------Started---------------------------------------");
		http.authorizeRequests().antMatchers("/login").permitAll().and().logout().logoutUrl("/logout").permitAll().and()
				.csrf().disable();
	}
}