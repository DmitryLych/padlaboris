package com.instinctools.padlaboris.application.security.config;

import com.instinctools.padlaboris.application.security.filter.AuthenticationTokenFilter;
import com.instinctools.padlaboris.application.security.service.jwt.TokenAuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Custom WebSecurityConfiguration.
 */
@EnableWebSecurity
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final TokenAuthenticationService tokenAuthenticationService;

    @Override
    protected void configure(final HttpSecurity http) throws Exception {

        http.authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                .antMatchers("/auth").permitAll()
                .antMatchers("/**").hasAuthority("ADMIN")
                .antMatchers("/signUp", "/users/**", "/patients/**").hasAuthority("DOCTOR")
                .antMatchers("/patients/**").hasAuthority("PATIENT")
                .anyRequest().authenticated()
                .and()
                .addFilterBefore(new AuthenticationTokenFilter(tokenAuthenticationService),
                        UsernamePasswordAuthenticationFilter.class)
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .csrf().disable();
    }
}
