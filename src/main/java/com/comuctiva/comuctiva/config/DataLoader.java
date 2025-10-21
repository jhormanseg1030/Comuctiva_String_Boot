package com.comuctiva.comuctiva.config;

import com.comuctiva.comuctiva.models.Usuario;
import com.comuctiva.comuctiva.models.Tip_Doc;
import com.comuctiva.comuctiva.repositoryes.UsuarioRepositories;
import com.comuctiva.comuctiva.repositoryes.Tip_DocRepositories;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataLoader {
    @Bean
    public CommandLineRunner loadData(UsuarioRepositories usuarioRepo, Tip_DocRepositories tipDocRepo) {
        return args -> {
            // Crear tipo de documento de prueba
            Tip_Doc cc = new Tip_Doc();
            cc.setTipo("Cédula");
            tipDocRepo.save(cc);

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

            // Puedes agregar más datos de prueba aquí para otras entidades
        };
    }
}
