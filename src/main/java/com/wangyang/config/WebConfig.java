package com.wangyang.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;

@Configuration
@EnableWebMvc
@ComponentScan({"com.wangyang.controller","com.wangyang.advice"})
public class WebConfig  implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowCredentials(true);
    }

    /**
     * if use addResourceHandlers
     * configureDefaultServletHandling is not use
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/upload/**")
            .addResourceLocations("file:///home/wy/Documents/spring-security-upload-jdbc/upload/")
            .addResourceLocations("classpath:/");

    }

    /**
     * jsp的视图解析器
     * @return
     */
//    @Bean
//    public ViewResolver internalResourceViewResolver(){
//        InternalResourceViewResolver resourceViewResolver =new InternalResourceViewResolver();
////        resourceViewResolver.setPrefix("/admin/");
//        resourceViewResolver.setSuffix(".jsp");
//        resourceViewResolver.setExposeContextBeansAsAttributes(true);
//        return resourceViewResolver;
//    }
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    @Bean
    public ViewResolver viewResolver(SpringTemplateEngine templateEngine){
        ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
        viewResolver.setCharacterEncoding("UTF-8");
        viewResolver.setTemplateEngine(templateEngine);
        return viewResolver;
    }
    @Bean
    public SpringTemplateEngine templateEngine(SpringResourceTemplateResolver templateResolver){

        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);
        return templateEngine;
    }
    @Bean
    public SpringResourceTemplateResolver templateResolver(){
        SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
        templateResolver.setPrefix("/html/");
        templateResolver.setSuffix(".html");
        templateResolver.setCharacterEncoding("UTF-8");
//        templateResolver.setTemplateMode("HTML5");
        return templateResolver;
    }
}
