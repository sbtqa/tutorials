package ru.sbtqa.tutorials.automationqa.api.demoapp.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig extends WebMvcConfigurationSupport {

    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("ru.sbtqa.tutorials.automationqa.api.demoapp.сontroller"))
                .build();
    }

    private ApiInfo meta() {
        return new ApiInfoBuilder()
                .title("SBERTECH Automation QA - Demo App")
                .build();
    }

    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger.html")
                .addResourceLocations("classpath:/META-INF/resources");

        registry.addResourceHandler("webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars");
    }
}
