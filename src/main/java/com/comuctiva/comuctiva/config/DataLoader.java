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
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataLoader {
    @Bean
    public CommandLineRunner loadData(UsuarioRepositories usuarioRepo, Tip_DocRepositories tipDocRepo, RolRepositories rolRepo, Rol_UsuarioRepositories rolUsuarioRepo, PasswordEncoder passwordEncoder) {
        return args -> {
            // Imprimir todos los tipos de documento para depuración
            System.out.println("Tipos de documento en la base de datos:");
            tipDocRepo.findAll().forEach(t -> System.out.println("- id: " + t.getId_tipdocu() + ", tipo: " + t.getTipo()));

            // Verificar si ya existen datos de prueba Y actualizar contraseña del Admin
            Usuario adminExistente = usuarioRepo.findByNumDoc(22222222L);
            if (adminExistente != null) {
                System.out.println("⚠️  Actualizando contraseña del usuario Admin...");
                adminExistente.setPassword(passwordEncoder.encode("admin123"));
                usuarioRepo.save(adminExistente);
                System.out.println("✅ Contraseña del Admin actualizada correctamente con BCrypt.");
                return;
            }

            // Obtener tipo de documento por id directamente
            Tip_Doc cc = tipDocRepo.findById(1).orElse(null); // Cedula de Ciudadania
            Tip_Doc ti = tipDocRepo.findById(3).orElse(null); // Tarjeta de Identidad

            if (cc == null || ti == null) {
                throw new RuntimeException("No se encontró el tipo de documento por id. Verifica los ids en la base de datos.");
            }

            // Crear rol Admin y Cliente (SIN Vendedor) - Verificar si ya existen
            Rol adminRol = rolRepo.findAll().stream()
                    .filter(r -> r.getNom_rol().equals("Administrador"))
                    .findFirst()
                    .orElseGet(() -> {
                        Rol rol = new Rol();
                        rol.setNom_rol("Administrador");
                        return rolRepo.save(rol);
                    });

            Rol clienteRol = rolRepo.findAll().stream()
                    .filter(r -> r.getNom_rol().equals("Cliente"))
                    .findFirst()
                    .orElseGet(() -> {
                        Rol rol = new Rol();
                        rol.setNom_rol("Cliente");
                        return rolRepo.save(rol);
                    });

            // Crear usuario admin con CC
            Usuario admin = new Usuario();
            admin.setNom_Usu("Admin");
            admin.setApell1("Sistema");
            admin.setApell2("Principal");
            admin.setTel(3001111111L);
            admin.setTel2(3009999999L);
            admin.setCorreo("admin@comuctiva.com");
            admin.setNumDoc(22222222L);
            // Encriptar la contraseña del administrador
            admin.setPassword(passwordEncoder.encode("admin123"));
            admin.setTip_Doc(cc);
            usuarioRepo.save(admin);

            // Asignar rol Admin al usuario admin
            Rol_Usuario rolAdmin = new Rol_Usuario();
            rolAdmin.setUsuario(admin);
            rolAdmin.setRol(adminRol);
            rolAdmin.setEstado(true);
            rolAdmin.setId(new Rol_UsuarioId(admin.getId_Usuario(), adminRol.getId_rol()));
            rolUsuarioRepo.save(rolAdmin);

            // Crear usuarios clientes de prueba
            Usuario cliente1 = new Usuario();
            cliente1.setNom_Usu("Ana");
            cliente1.setApell1("Rodríguez");
            cliente1.setApell2("Sánchez");
            cliente1.setTel(3004444444L);
            cliente1.setTel2(3006666666L);
            cliente1.setCorreo("ana@cliente.com");
            cliente1.setNumDoc(55555555L);
            cliente1.setPassword(passwordEncoder.encode("1234"));
            cliente1.setTip_Doc(cc);
            usuarioRepo.save(cliente1);

            // Asignar rol Cliente
            Rol_Usuario rolCliente1 = new Rol_Usuario();
            rolCliente1.setUsuario(cliente1);
            rolCliente1.setRol(clienteRol);
            rolCliente1.setEstado(true);
            rolCliente1.setId(new Rol_UsuarioId(cliente1.getId_Usuario(), clienteRol.getId_rol()));
            rolUsuarioRepo.save(rolCliente1);

            // Cliente 2
            Usuario cliente2 = new Usuario();
            cliente2.setNom_Usu("Pedro");
            cliente2.setApell1("López");
            cliente2.setApell2("González");
            cliente2.setTel(3005555555L);
            cliente2.setTel2(3005555555L);
            cliente2.setCorreo("pedro@cliente.com");
            cliente2.setNumDoc(66666666L);
            cliente2.setPassword(passwordEncoder.encode("1234"));
            cliente2.setTip_Doc(cc);
            usuarioRepo.save(cliente2);

            Rol_Usuario rolCliente2 = new Rol_Usuario();
            rolCliente2.setUsuario(cliente2);
            rolCliente2.setRol(clienteRol);
            rolCliente2.setEstado(true);
            rolCliente2.setId(new Rol_UsuarioId(cliente2.getId_Usuario(), clienteRol.getId_rol()));
            rolUsuarioRepo.save(rolCliente2);

            System.out.println("✅ Datos iniciales cargados: 1 Admin y 2 Clientes (sin Vendedores)");
        };
    }
}
