package com.kamil.warehouse.auth;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

    public JwtAuthorizationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    // sprawdza, czy użytkownik przedstawił sie tokenem, jeśli tak, to wstawia usera z tokenu do kontekstu security
    // albo mówi, że użytkownik jest nieautoryzowany
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String token = request.getHeader("Authorization");
        if (token == null || !token.startsWith("Bearer")) { // jeśli by był Bearer to jest poprawny
            SecurityContextHolder.getContext().setAuthentication(null); // użytkownik nie jest autoryzowany
            chain.doFilter(request, response);
            return;
        }

        Claims claims = Jwts.parser().setSigningKey("sekretnyklucz") // rozparsowuje token
                .parseClaimsJws(token.replace("Bearer ", "")) // usuwam prefix tokenu
                .getBody();
        String authorities = claims.get("authorities", String.class);

        List<SimpleGrantedAuthority> simpleGrantedAuthorityList = new ArrayList<>();

        if (authorities != null || !authorities.isEmpty()) {
            simpleGrantedAuthorityList = Arrays.stream(authorities.split(","))
                    .map(auth -> new SimpleGrantedAuthority(auth))
                    .collect(Collectors.toList());
        }

        String userName = claims.getSubject();
        if (userName != null) {
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userName, null, simpleGrantedAuthorityList);
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            chain.doFilter(request, response);
        } else {
            SecurityContextHolder.getContext().setAuthentication(null); // niezalogowany
            response.setStatus(401);
        }
    }
}
