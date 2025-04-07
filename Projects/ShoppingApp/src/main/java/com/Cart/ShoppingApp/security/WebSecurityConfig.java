package com.Cart.ShoppingApp.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.Cart.ShoppingApp.security.JWT.AuthEntryPointJwt;
import com.Cart.ShoppingApp.security.JWT.AuthTokenFilter;
import com.Cart.ShoppingApp.security.services.UserDetailsServiceImpl;



@Configuration
@EnableWebSecurity
//@EnableMethodSecurity
public class WebSecurityConfig {
	 @Autowired
	    UserDetailsServiceImpl userDetailsService;

	    @Autowired
	    private AuthEntryPointJwt unauthorizedHandler;

	    @Bean
	    public AuthTokenFilter authenticationJwtTokenFilter() {
	    	System.out.println("___________inside authenticationJwtTokenFilter method of WebSecurityConfig");
	        return new AuthTokenFilter();
	    }


	    @Bean
	    public DaoAuthenticationProvider authenticationProvider() {
	    	System.out.println("___________inside authenticationProvider method of WebSecurityConfig");

	        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

	        authProvider.setUserDetailsService(userDetailsService);
	        authProvider.setPasswordEncoder(passwordEncoder());

	        return authProvider;
	    }


	    @Bean
	    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
	    	System.out.println("___________inside authenticationManager method of WebSecurityConfig");

	        return authConfig.getAuthenticationManager();
	    }

	    @Bean
	    public PasswordEncoder passwordEncoder() {
	        return new BCryptPasswordEncoder();
	    }



	    @Bean
	    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
	    	System.out.println("___________inside filterChain method of WebSecurityConfig");

	        http.csrf(csrf -> csrf.disable())
	                .exceptionHandling(exception -> exception.authenticationEntryPoint(unauthorizedHandler))
	                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
	                .authorizeHttpRequests(auth ->
	                        auth.requestMatchers("/api/auth/**").permitAll()
	                        .requestMatchers("/api/auth/signup").permitAll()
	                        .requestMatchers("api/auth/signin").permitAll()
	                                .requestMatchers("/v3/api-docs/**").permitAll()
	                                .requestMatchers("/api/admin/**").permitAll()
	                                //.requestMatchers("/api/public/**").permitAll()
	                                .requestMatchers("/swagger-ui/**").permitAll()
	                                .requestMatchers("/api/test/**").permitAll()
	                                .requestMatchers("/images/**").permitAll()
	                                .anyRequest().authenticated()
	                );

	        http.authenticationProvider(authenticationProvider());

	        http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);

	        return http.build();
	    }

	    @Bean
	    public WebSecurityCustomizer webSecurityCustomizer() {
	    	System.out.println("___________inside webSecurityCustomizer method of WebSecurityConfig");

	        return (web -> web.ignoring().requestMatchers("/v2/api-docs",
	                "/configuration/ui",
	                "/swagger-resources/**",
	                "/configuration/security",
	                "/swagger-ui.html",
	                "/webjars/**"));
	    }
}
