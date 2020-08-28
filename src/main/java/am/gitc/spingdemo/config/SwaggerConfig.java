package am.gitc.spingdemo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

  @Bean
  public Docket api() {
    return new Docket(DocumentationType.SWAGGER_2)
        .select()
        .apis(RequestHandlerSelectors.basePackage("am.gitc.spingdemo.controller"))
        .paths(PathSelectors.any()).build().pathMapping("/")
        .apiInfo(apiInfo());
  }

  private ApiInfo apiInfo() {
    return new ApiInfo(
        "Gitc Spring Demo Api.",
        "Rest Endpoints for Gitc Spring Demo API",
        "1.0.0",
        "Terms of service",
        new Contact("Gitc Spring Demo", "www.gitc.springdemo.am", "test@gitc.com"),
        "License of Gitc Spring Demo API", "API license URL", Collections.emptyList());
  }
}