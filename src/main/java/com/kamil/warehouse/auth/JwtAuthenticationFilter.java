package com.kamil.warehouse.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private AuthenticationManager authenticationManager;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getParameter("userName"), request.getParameter("password")));
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        Map<String, Object> claims = new HashMap<>();
        String authorities = authResult.getAuthorities() // pobieramy role, które były utworzone w UserDetailsServiceImpl, magia springowa, sam mapuje z UserDetails mapuje na authentication
                .stream()
                .map(x -> ((GrantedAuthority) x).getAuthority()) // pobieramy nazwy ról
                .collect(Collectors.joining(",")); // łączymy nazwy ról po przecinku
        claims.put("authorities", authorities); // wstawiamy do mapy

        String token = Jwts.builder() // tworzy token
                .setClaims(claims) // ustawiamy mapę claims, w której są nazwy ról połączone przecinkami
                .setSubject(((UserDetails) authResult.getPrincipal()).getUsername()) // ustawiamy nazwe użytkownika
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60)) // jak długo token jest walidowany
                .signWith(SignatureAlgorithm.HS512, "sekretnyklucz") // sposób hashowania tokenu
                .compact();// wygeneruj token

        response.setHeader("Authorization", "Bearer " + token); // ustawiamy header do responsu z naszym tokenem
        Map<String, String> responseBody = new HashMap<>();
        responseBody.put("token", token);
        new ObjectMapper().writeValue(response.getWriter(), responseBody); // tworzy json z mapy
    }
}
