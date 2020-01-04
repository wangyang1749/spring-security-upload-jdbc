# Introduction
本项目的目的是用来学习`Spring Security` `Spring data` `jpa` `Hibernate` `Mybatis` 
`jwt` 

## web层介绍
## spring mvc 异常处理
+ 一种是使用 HandlerExceptionResolver 接口
> Spring 已经提供默认的实现类 SimpleMappingExceptionResolver
>404 表示请求路径错误，服务器找不到对应的资源，属于容器抛出的异常， 跟spring 无关
HandlerExceptionResolver 是spring 的异常拦截类，只处理程序运行过程中的错误

```xml
<error-page>
   <error-code>404</error-code>
   <location>/404.jsp</location>
</error-page>
```
```java
@Override
public void onStartup(ServletContext servletContext) throws ServletException {
    AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
    ctx.register(ConfigMVC.class);
    ctx.setServletContext(servletContext);
    Dynamic servlet = servletContext.addServlet("dispatcher", new DispatcherServlet(ctx));
    servlet.addMapping("/");
    servlet.setLoadOnStartup(1);
    servlet.setInitParameter("throwExceptionIfNoHandlerFound", "true");
}
```

```java
@Override
protected DispatcherServlet createDispatcherServlet(WebApplicationContext servletAppContext) {
    final DispatcherServlet dispatcherServlet = (DispatcherServlet) super.createDispatcherServlet(servletAppContext);
    dispatcherServlet.setThrowExceptionIfNoHandlerFound(true);
    return dispatcherServlet;
}
```

```java
@Override
public void customizeRegistration(ServletRegistration.Dynamic registration) {
    registration.setInitParameter("throwExceptionIfNoHandlerFound", "true");
}
```

```java
@ControllerAdvice
public class ErrorController {
    @ResponseBody
    @ExceptionHandler(NoHandlerFoundException.class)
    public String error404(Exception ex) {

        return "404";
    }
}
```


```java
@Component
public class DefaultExceptionHandler implements HandlerExceptionResolver {
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {
        System.out.println("find exception");
        return null;
    }
}
```

```java
@Bean
public SimpleMappingExceptionResolver simpleMappingExceptionResolver(){
    SimpleMappingExceptionResolver resolver = new SimpleMappingExceptionResolver();
    resolver.setExceptionAttribute("e");
    /**
     * 对应页面的异常类型
     */
    resolver.addStatusCode("admin", 400);
    resolver.setDefaultErrorView("admin");
    Properties mapping = new Properties();
    mapping.put("com.wangyang.controller.MyException" , "admin");
    resolver.setExceptionMappings(mapping);
    return resolver;
}
```

+ Controller 内部单独实现

### Spring Security
> 
>
### 实现SpringSecurity+Jwt+token验证

## 持久层的介绍
> 在包 `com.wangyang.dao` 是接口定义, `com.wangyang.dao.impl` 是各种框架对接口的实现 .  
> 在全局的配置文件中, 确保 `com.wangyang.service` 只扫描到一种dao的实现,  
> 否则会出现多个bean重复定义. 
> 每一种持久化框架的实现代码,可以通过 `com.transaction` 中的测试代码进行测试,  
> 测试代码提供了配套的javaConfig配置文件
### Hibernate 

## 配置介绍
+ H2 databases的访问地址<http://localhost:8080/console>
+ 

# 备份
    @Bean
    public DataSource dataSource_(){
        return new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .addScript("classpath:schema.sql")
                .build();
    }
    
    @Bean
    public  DataSource dataSource(){
        BasicDataSource ds = new BasicDataSource();
        ds.setDriverClassName("org.sqlite.JDBC");
        ds.setUrl("jdbc:sqlite:dbs/xwfa.db");
        System.out.println("-------配置spring的数据源----------");
        return ds;
    }
    