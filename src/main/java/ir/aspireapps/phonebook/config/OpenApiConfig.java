package ir.aspireapps.phonebook.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI phonebookOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Phonebook")
                        .version("0.0.1-SNAPSHOT")
                        .description("""
                                Phonebook App (Spring Boot + Thymeleaf)
                                --------------------------------------
                                A ready to use Phonebook management system featuring robust security layers.
                                
                                Tech Stack:
                                  - Backend: Spring Boot 3.x, Spring Security, Spring Data JPA.
                                  - Frontend: Thymeleaf, Bootstrap / Tailwind.
                                  - Database: PostgreSQL (with update/validate strategies).
                                
                                Security Model:
                                  - Session-based authentication with standard Form Login.
                                  - Token-based 'Remember Me' persistent login cookies.
                                
                                Core Features:
                                  - Dual Authentication: Session-based login combined with Token-based Remember-Me.
                                  - User & Contacts Management: Isolated environment where users can securely manage their own contact directories.
                                """));
    }
}

