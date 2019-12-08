package com.wangyang.config.spring;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.BeanNameViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configurable
@EnableWebMvc
@ComponentScan("com.wangyang.controller")
public class WebConfig implements WebMvcConfigurer {


    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedOrigins("*")
                .allowCredentials(true);
    }

    /**
     * 添加静态资源
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/");
    }

//    @Bean
//    public MultipartResolver multipartResolver(){
//        return new StandardServletMultipartResolver();
//    }


    /**
     * 自定义bean视图解析器
     * @return
     */
    @Bean
    public BeanNameViewResolver beanNameViewResolver(){
        return new BeanNameViewResolver();
    }

    /**
     * jsp的视图解析器
     * @return
     */
    @Bean
    public ViewResolver internalResourceViewResolver(){
        InternalResourceViewResolver resourceViewResolver =new InternalResourceViewResolver();
//        resourceViewResolver.setPrefix("/admin/");
        resourceViewResolver.setSuffix(".jsp");
        resourceViewResolver.setExposeContextBeansAsAttributes(true);
        return resourceViewResolver;
    }

    /**
     * Problem accessing /saylovewall/main.css. Reason:
     * 解决以上问题，保证webapp下的静态文件能够被访问
     * @param configurer
     */
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }
}
