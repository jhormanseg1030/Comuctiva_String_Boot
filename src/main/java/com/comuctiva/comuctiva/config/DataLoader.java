package com.comuctiva.comuctiva.config;

import com.comuctiva.comuctiva.models.Usuario;
import com.comuctiva.comuctiva.models.Tip_Doc;
import com.comuctiva.comuctiva.models.Rol;
import com.comuctiva.comuctiva.models.Rol_Usuario;
import com.comuctiva.comuctiva.models.Rol_UsuarioId;
import com.comuctiva.comuctiva.repositoryes.UsuarioRepositories;
import com.comuctiva.comuctiva.repositoryes.Tip_DocRepositories;
import com.comuctiva.comuctiva.repositoryes.RolRepositories;
import com.comuctiva.comuctiva.repositoryes.Rol_UsuarioRepositories;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataLoader {
    @Bean
    public CommandLineRunner loadData(UsuarioRepositories usuarioRepo, Tip_DocRepositories tipDocRepo, RolRepositories rolRepo, Rol_UsuarioRepositories rolUsuarioRepo) {
        return args -> {
            // Imprimir todos los tipos de documento para depuración
            System.out.println("Tipos de documento en la base de datos:");
            tipDocRepo.findAll().forEach(t -> System.out.println("- id: " + t.getId_tipdocu() + ", tipo: " + t.getTipo()));

            // Obtener tipo de documento por id directamente
            Tip_Doc cc = tipDocRepo.findById(1).orElse(null); // Cedula de Ciudadania
            Tip_Doc ti = tipDocRepo.findById(3).orElse(null); // Tarjeta de Identidad

            if (cc == null || ti == null) {
                throw new RuntimeException("No se encontró el tipo de documento por id. Verifica los ids en la base de datos.");
            }

            // Crear rol Admin
            Rol adminRol = new Rol();
            adminRol.setNom_rol("Admin");
            rolRepo.save(adminRol);

            // Crear usuario admin con TI
            Usuario admin = new Usuario();
            admin.setNom_Usu("Admin");
            admin.setApell1("Principal");
            admin.setApell2("");
            admin.setTel(999999999L);
            admin.setTel2(888888888L);
            admin.setCorreo("admin@comuctiva.com");
            admin.setNumDoc(22222222L);
            admin.setPassword("admin123");
            admin.setTip_Doc(cc);
            usuarioRepo.save(admin);

            // Asignar rol Admin al usuario admin
            Rol_Usuario rolAdmin = new Rol_Usuario();
            rolAdmin.setUsuario(admin);
            rolAdmin.setRol(adminRol);
            rolAdmin.setEstado(true);
            rolAdmin.setId(new Rol_UsuarioId(admin.getId_Usuario(), adminRol.getId_rol()));
            rolUsuarioRepo.save(rolAdmin);

            // Crear usuario de prueba
            Usuario user = new Usuario();
            user.setNom_Usu("Juan");
            user.setApell1("Pérez");
            user.setApell2("Gómez");
            user.setTel(123456789L);
            user.setTel2(987654321L);
            user.setCorreo("juan@example.com");
            user.setNumDoc(11111111L);
            user.setPassword("1234");
            user.setTip_Doc(cc);
            usuarioRepo.save(user);
        };
    }
}
