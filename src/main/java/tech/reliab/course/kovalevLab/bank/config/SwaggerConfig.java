package tech.reliab.course.kovalevLab.bank.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Bank API")
                        .description("API для управления банковскими операциями")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Luvv")
                                .email("your.luvv@example.com")
                                .url("https://luvvwebsite.com"))
                        .license(new License()
                                .name("MIT License")
                                .url("https://opensource.org/licenses/MIT")))

                .externalDocs(new ExternalDocumentation()
                        .description("Дополнительная документация")
                        .url("https://docs.bank.com/api"));
    }
}