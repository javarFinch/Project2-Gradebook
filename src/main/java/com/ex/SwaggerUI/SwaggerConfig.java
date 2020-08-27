package com.ex.SwaggerUI;

import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@EnableWebMvc
public class SwaggerConfig extends WebMvcConfigurerAdapter {

    /* @Override
     *//* public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");

        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }*/
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addRedirectViewController("/docApi/v2/api-docs", "/v2/api-docs");
        registry.addRedirectViewController("/docApi/swagger-resources/configuration/ui", "/swagger-resources/configuration/ui");
        registry.addRedirectViewController("/docApi/swagger-resources/configuration/security", "/swagger-resources/configuration/security");
        registry.addRedirectViewController("/docApi/swagger-resources", "/swagger-resources");
        registry.addViewController("/swagger-ui/").setViewName("forward:" + "/swagger-ui/index.html");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/swagger-ui.html**").addResourceLocations("classpath:/WEB-INF/resources/swagger-ui.html");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/WEB-INF/resources/webjars/");
        registry.addResourceHandler("/swagger-ui/**").addResourceLocations("classpath:/WEB-INF/resources/webjars/springfox-swagger-ui/").resourceChain(false);
    }
}
