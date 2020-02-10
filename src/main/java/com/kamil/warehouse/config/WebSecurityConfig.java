package com.kamil.warehouse.config;

import com.kamil.warehouse.auth.JwtAuthenticationFilter;
import com.kamil.warehouse.auth.JwtAuthorizationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    //@Qualifier("userDetailsServiceImpl") //ten interfejs ma wiele implementacji i nie udaje nam sie wstrzyknac odpowiedniej. My wstrzykujemy nasza implementacje i musimy to powiedziec
    private UserDetailsService userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    //włącza bezpieczeństwo owasp
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors()
                .and()
                .csrf()
                .ignoringAntMatchers("/**")
                .and()
                .headers()
                .frameOptions()
                .disable()
                .and()
                .addFilter(new JwtAuthenticationFilter(authenticationManager()))
                .addFilter(new JwtAuthorizationFilter(authenticationManager()));
    }

    @Override //pokazuje jaka inplementacje userdetailservice mam i jakiego encodera uzywam
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }
}
