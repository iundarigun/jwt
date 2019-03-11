package br.com.devcave.jwt.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.List;

@Controller
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket swaggerAuth() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("Auth")
                .select()
                .apis(RequestHandlerSelectors.basePackage("br.com.devcave.jwt.controller"))
                .paths(PathSelectors.ant("/auth/**"))
                .build();
    }

    @Bean
    public Docket swaggerProducts() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("Products")
                .select()
                .apis(RequestHandlerSelectors.basePackage("br.com.devcave.jwt.controller"))
                .paths(PathSelectors.ant("/products/**"))
                .build()
                .securityContexts(List.of(securityContext()))
                .securitySchemes(List.of(apiKey()));
    }

    @GetMapping("/")
    public String index() {
        return "redirect:swagger-ui.html";
    }

    private ApiKey apiKey() {
        return new ApiKey("JWT", "Authorization", "header");
    }

    private SecurityContext securityContext() {
        return SecurityContext.builder()
                .securityReferences(List.of(new SecurityReference("JWT", new AuthorizationScope[0])))
                .build();
    }

}
