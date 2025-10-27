package com.comuctiva.comuctiva.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.lang.NonNull;
import java.io.IOException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {
    @Autowired
    private JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(@NonNull jakarta.servlet.http.HttpServletRequest request,
                                    @NonNull jakarta.servlet.http.HttpServletResponse response,
                                    @NonNull jakarta.servlet.FilterChain chain)
            throws jakarta.servlet.ServletException, IOException {
        
        System.out.println("=== JWT FILTER ===");
        System.out.println("Request URI: " + request.getRequestURI());
        System.out.println("Request Method: " + request.getMethod());
        
        final String authHeader = request.getHeader("Authorization");
        System.out.println("Authorization Header: " + (authHeader != null ? "presente" : "ausente"));
        
        String username = null;
        String jwt = null;
        java.util.List<org.springframework.security.core.authority.SimpleGrantedAuthority> authorities = new java.util.ArrayList<>();

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            jwt = authHeader.substring(7);
            username = jwtUtil.extractUsername(jwt);
            java.util.List<String> roles = jwtUtil.extractRoles(jwt);
            for (String role : roles) {
                authorities.add(new org.springframework.security.core.authority.SimpleGrantedAuthority(role));
            }
            System.out.println("Token JWT extraído, username: " + username + ", roles: " + roles);
        }

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            if (jwtUtil.validateToken(jwt)) {
                System.out.println("Token válido, autenticando usuario: " + username);
                UsernamePasswordAuthenticationToken authToken =
                        new UsernamePasswordAuthenticationToken(username, null, authorities);
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
            } else {
                System.out.println("Token inválido");
            }
        }
        
        System.out.println("=== FIN JWT FILTER ===");
        chain.doFilter(request, response);
    }
}
