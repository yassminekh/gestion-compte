package tn.iit.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**") // Autoriser les requêtes CORS pour toutes les routes /api
                .allowedOrigins("http://localhost:4200") // Autoriser les requêtes depuis ce domaine
                .allowedMethods("GET", "POST", "PUT", "DELETE")
                .allowedHeaders("*") // Autoriser tous les en-têtes
                .allowCredentials(true); // Autoriser les cookies et les en-têtes d'authentification
    }
}