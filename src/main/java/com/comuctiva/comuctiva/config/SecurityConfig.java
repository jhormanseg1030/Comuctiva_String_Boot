package com.comuctiva.comuctiva.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {
    @Autowired
    private JwtRequestFilter jwtRequestFilter;
    
    @Autowired
    private CustomAccessDeniedHandler accessDeniedHandler;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth

            .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
            
                // Rutas públicas (sin autenticación) - DEBEN IR PRIMERO
                .requestMatchers("/api/usuario/login").permitAll()
                .requestMatchers(HttpMethod.POST, "/api/usuario").permitAll()
                .requestMatchers("/api/tipdoc/**", "/api/Unidad_Medida/**").permitAll()
                
                // Rutas de solo lectura (públicas)
                .requestMatchers(HttpMethod.GET, "/api/productos/**", "/api/comentarios/**").permitAll()
                
                // NUEVOS ENDPOINTS - Ventas y Compras (requieren autenticación)
                .requestMatchers(HttpMethod.GET, "/api/mis-ventas", "/api/mis-compras").hasAnyAuthority("Administrador", "Cliente")
                // Rutas de transporte - Administrador y Cliente
                .requestMatchers("/api/vehiculos/**", "/api/cotizaciones/**", "/api/fletes/**").hasAnyAuthority("Administrador", "Cliente")
                
                // DELETE - Solo Administrador
                .requestMatchers(HttpMethod.DELETE, "/api/**").hasAuthority("Administrador")
                
                .requestMatchers(HttpMethod.POST, "/api/producto/**").hasAnyAuthority("Administrador", "Cliente")
                // POST y PUT - Administrador y Cliente
                
                .requestMatchers(HttpMethod.POST, "/api/pedi_produc/").hasAnyAuthority("Administrador","Cliente")
                .requestMatchers(HttpMethod.POST, "/api/**").hasAnyAuthority("Administrador", "Cliente")
                .requestMatchers(HttpMethod.PUT, "/api/**").hasAnyAuthority("Administrador", "Cliente")
                // GET autenticado - Administrador y Cliente
                .requestMatchers(HttpMethod.GET, "/api/**").hasAnyAuthority("Administrador", "Cliente")
                
                // Cualquier otra petición requiere autenticación
                .anyRequest().authenticated()
            )
            .exceptionHandling(exceptions -> exceptions
                .accessDeniedHandler(accessDeniedHandler)
            )
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}
