package com.comuctiva.comuctiva.config;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {
    
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
                      AccessDeniedException accessDeniedException) throws IOException {
        
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        
        String jsonResponse = String.format(
            "{\"error\": \"Acceso denegado\", \"mensaje\": \"No tienes permisos para realizar esta acción. Solo los Administradores pueden eliminar recursos.\", \"path\": \"%s\", \"method\": \"%s\"}",
            request.getRequestURI(),
            request.getMethod()
        );
        
        response.getWriter().write(jsonResponse);
    }
}
