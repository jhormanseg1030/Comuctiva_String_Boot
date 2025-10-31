package com.comuctiva.comuctiva.config;

import com.comuctiva.comuctiva.models.Usuario;
import com.comuctiva.comuctiva.repositoryes.UsuarioRepositories;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Script de migración para encriptar contraseñas existentes.
 * 
 * IMPORTANTE: Este script solo debe ejecutarse UNA VEZ para migrar contraseñas
 * de texto plano a BCrypt. Después de la migración, este archivo debe ser:
 * 1. Comentado (agregando //) antes de @Component
 * 2. O eliminado del proyecto
 * 
 * Para activar este script, descomenta la anotación @Component
 */
// @Component  // <-- DESCOMENTA ESTA LÍNEA SOLO PARA MIGRAR CONTRASEÑAS EXISTENTES
public class PasswordMigrationRunner implements CommandLineRunner {

    private final UsuarioRepositories usuarioRepositories;
    private final PasswordEncoder passwordEncoder;

    public PasswordMigrationRunner(UsuarioRepositories usuarioRepositories, PasswordEncoder passwordEncoder) {
        this.usuarioRepositories = usuarioRepositories;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("=== INICIO DE MIGRACIÓN DE CONTRASEÑAS ===");
        
        // Obtener todos los usuarios
        var usuarios = usuarioRepositories.findAll();
        int migrados = 0;
        int omitidos = 0;

        for (Usuario usuario : usuarios) {
            String passwordActual = usuario.getPassword();
            
            // Verificar si la contraseña ya está encriptada (BCrypt empieza con $2a$ o $2b$)
            if (passwordActual != null && (passwordActual.startsWith("$2a$") || passwordActual.startsWith("$2b$"))) {
                System.out.println("Usuario " + usuario.getNom_Usu() + " (ID: " + usuario.getId_Usuario() + ") ya tiene contraseña encriptada. Omitiendo...");
                omitidos++;
                continue;
            }

            // Encriptar la contraseña actual
            if (passwordActual != null && !passwordActual.trim().isEmpty()) {
                String passwordEncriptada = passwordEncoder.encode(passwordActual);
                usuario.setPassword(passwordEncriptada);
                usuarioRepositories.save(usuario);
                
                System.out.println("✓ Contraseña migrada para usuario: " + usuario.getNom_Usu() + " (ID: " + usuario.getId_Usuario() + ")");
                migrados++;
            } else {
                System.out.println("⚠ Usuario " + usuario.getNom_Usu() + " (ID: " + usuario.getId_Usuario() + ") no tiene contraseña. Omitiendo...");
                omitidos++;
            }
        }

        System.out.println("=== FIN DE MIGRACIÓN DE CONTRASEÑAS ===");
        System.out.println("Total de usuarios migrados: " + migrados);
        System.out.println("Total de usuarios omitidos: " + omitidos);
        System.out.println("\n⚠ IMPORTANTE: Desactiva este script comentando @Component o eliminando el archivo.");
    }
}
