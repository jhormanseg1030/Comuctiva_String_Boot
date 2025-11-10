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
            
                // ========== RUTAS PÚBLICAS ==========
                .requestMatchers("/api/usuario/login").permitAll()
                .requestMatchers(HttpMethod.POST, "/api/usuario").permitAll()
                .requestMatchers(HttpMethod.GET, "/api/producto/imagen/**").permitAll()
                .requestMatchers("/api/tipdoc/**", "/api/Unidad_Medida/**").permitAll()

                    // ========== RUTAS DE COMPRA Y COMP_PRODUC ========== 🆕
                .requestMatchers(HttpMethod.GET, "/api/compra/**").hasAnyAuthority("Administrador", "Cliente")
                .requestMatchers(HttpMethod.POST, "/api/compra").hasAnyAuthority("Administrador", "Cliente")
                .requestMatchers(HttpMethod.PUT, "/api/compra/**").hasAnyAuthority("Administrador", "Cliente")
                .requestMatchers(HttpMethod.DELETE, "/api/compra/**").hasAnyAuthority("Administrador", "Cliente")
                
                .requestMatchers(HttpMethod.GET, "/api/comp_produc/**").hasAnyAuthority("Administrador", "Cliente")
                .requestMatchers(HttpMethod.POST, "/api/comp_produc").hasAnyAuthority("Administrador", "Cliente")
                .requestMatchers(HttpMethod.PUT, "/api/comp_produc/**").hasAnyAuthority("Administrador", "Cliente")
                .requestMatchers(HttpMethod.DELETE, "/api/comp_produc/**").hasAnyAuthority("Administrador", "Cliente")
                
                // ========== MIS VENTAS Y MIS COMPRAS ==========
                .requestMatchers(HttpMethod.GET, "/api/mis-ventas").hasAnyAuthority("Administrador", "Cliente")
                .requestMatchers(HttpMethod.GET, "/api/mis-compras").hasAnyAuthority("Administrador", "Cliente")
                
                // ========== RUTAS DE MIS PRODUCTOS (ESPECÍFICAS - DEBEN IR PRIMERO) ==========
                // 🔥 ESTAS RUTAS REQUIEREN AUTENTICACIÓN
                .requestMatchers(HttpMethod.GET, "/api/producto/mis-productos").hasAnyAuthority("Administrador", "Cliente")
                .requestMatchers(HttpMethod.GET, "/api/producto/mis-productos/**").hasAnyAuthority("Administrador", "Cliente")
                .requestMatchers(HttpMethod.PUT, "/api/producto/mis-productos/**").hasAnyAuthority("Administrador", "Cliente")
                .requestMatchers(HttpMethod.DELETE, "/api/producto/mis-productos/**").hasAnyAuthority("Administrador", "Cliente")
                
                // ========== RUTAS GENERALES DE PRODUCTO (GENÉRICAS - VAN DESPUÉS) ==========
                // 🔥 GET sin autenticación
                .requestMatchers(HttpMethod.GET, "/api/producto", "/api/producto/**").permitAll()
                .requestMatchers(HttpMethod.GET, "/api/productos/**").permitAll()
                
                // 🔥 POST requiere autenticación
                .requestMatchers(HttpMethod.POST, "/api/producto").hasAnyAuthority("Administrador", "Cliente")
                
                // 🔥 PUT requiere autenticación (rutas generales, no mis-productos)
                .requestMatchers(HttpMethod.PUT, "/api/producto/**").hasAnyAuthority("Administrador", "Cliente")
                
                // 🔥 DELETE requiere ADMIN
                .requestMatchers(HttpMethod.DELETE, "/api/producto/**").hasAuthority("Administrador")
                
                // ========== RUTAS DE COMENTARIOS ==========
                .requestMatchers(HttpMethod.GET, "/api/comentario", "/api/comentario/**").hasAnyAuthority("Administrador", "Cliente")
                .requestMatchers(HttpMethod.POST, "/api/comentario", "/api/comentario/**").hasAnyAuthority("Administrador", "Cliente")
                .requestMatchers(HttpMethod.PUT, "/api/comentario/**").hasAnyAuthority("Administrador", "Cliente")
                .requestMatchers(HttpMethod.DELETE, "/api/comentario/**").hasAnyAuthority("Administrador", "Cliente")

                // ========== OTRAS RUTAS ==========
                .requestMatchers("/api/carrito/**").hasAnyAuthority("Administrador", "Cliente")
                .requestMatchers(HttpMethod.GET, "/api/mis-ventas", "/api/mis-compras").hasAnyAuthority("Administrador", "Cliente")
                .requestMatchers("/api/vehiculos/**", "/api/cotizaciones/**", "/api/fletes/**").hasAnyAuthority("Administrador", "Cliente")
                .requestMatchers(HttpMethod.POST, "/api/pedi_produc/").hasAnyAuthority("Administrador","Cliente")
                .requestMatchers(HttpMethod.POST, "/api/**").hasAnyAuthority("Administrador", "Cliente")
                .requestMatchers(HttpMethod.PUT, "/api/**").hasAnyAuthority("Administrador", "Cliente")
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
