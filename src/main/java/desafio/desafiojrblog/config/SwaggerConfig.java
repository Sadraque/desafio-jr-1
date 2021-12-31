package desafio.desafiojrblog.config;

import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Blog API")
                        .version("1.0")
                        .description("Desafio JR")
                        .termsOfService("https://swagger.io/terms/")
                        .license(new License().name("Apache 2.0").url("https://springdoc.org"))
                        .contact(new Contact()
                                .name("Sadraque Nunes")
                                .url("https://www.linkedin.com/in/sadraquenunesmartiniano/")
                                .email("sadraquenunesmartiniano@gmail.com")));

    }
}
