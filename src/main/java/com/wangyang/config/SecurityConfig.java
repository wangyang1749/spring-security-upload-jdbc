package com.wangyang.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
    @Autowired
    DataSource dataSource;

    @Bean
    PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
    /**
     * 配置如何通过拦截器保护请求
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/hello").hasRole("user")
                .antMatchers("/admin").hasRole("admin")
                .antMatchers(HttpMethod.POST, "/login").permitAll()
                .anyRequest().authenticated()
                .and()
                .addFilterBefore(new JwtLoginFilter("/login",authenticationManager()),UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(new JwtFilter(), UsernamePasswordAuthenticationFilter.class)
                .csrf().disable();
    }


    /**
     * 配置user-detail服务
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("admin")
                .password("123").roles("admin")
                .and()
                .withUser("sang")
                .password("456")
                .roles("user");
        /**
         * 基于内存的用户存储
         */
//        auth.inMemoryAuthentication().passwordEncoder(new MyPasswordEncoder())
//                .withUser("user")
//                .password("123456").roles("USER");
        /**
         * 基于数据库的内存存储
         */
//        auth.jdbcAuthentication().passwordEncoder(new MyPasswordEncoder()).dataSource(dataSource);

    }


    /**
     * 判断请求是否是ajax请求
     * @param request
     * @return
     */
    public static boolean isAjaxRequest(HttpServletRequest request) {
        String ajaxFlag = request.getHeader("X-Requested-With");
        return ajaxFlag != null && "XMLHttpRequest".equals(ajaxFlag);
    }

    protected void configure_(HttpSecurity http) throws Exception {
        http
                .exceptionHandling()
                .authenticationEntryPoint(new AuthenticationEntryPoint() {
                    /**
                     * 访问没有授权路径执行的操作
                     * @param request
                     * @param response
                     * @param authException
                     * @throws IOException
                     * @throws ServletException
                     */
                    @Override
                    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
                        response.sendRedirect(request.getContextPath()+"/login");
                    }
                })
                .and()
                .csrf().disable()
                .authorizeRequests()
                /**
                 * 静态资源不需要授权
                 */
                .antMatchers("/login","/css/**", "/js/**","/fonts/**")
                .permitAll()
                /**
                 * /test/**和/admin/**需要用户需要ROLE_USER的角色
                 * 这里资源和角色是硬编码，也就是说，固定角色的个数，并且每个角色所对应的资源已经在代码里写死
                 */
                .antMatchers("/test/**").hasRole("USER")
                .antMatchers("/admin/**").hasRole("USER")
                .and()
                .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/login")
                .defaultSuccessUrl("/hello")
                .usernameParameter("username")
                .passwordParameter("password")
                .successHandler(new SimpleUrlAuthenticationSuccessHandler(){
                    /**
                     * 在授权成功之后应该做的事情，重定向页面，还是返回成功json为ajax服务
                     * @param request
                     * @param response
                     * @param authentication
                     * @throws IOException
                     * @throws ServletException
                     */
                    @Override
                    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
                        if(isAjaxRequest(request)){
                            response.setStatus(HttpServletResponse.SC_OK);
                            response.getWriter().write("Login Success .......");
                        }else{
                            response.sendRedirect("admin");
                        }

                        super.onAuthenticationSuccess(request, response, authentication);
                    }
                })
                .failureHandler(new SimpleUrlAuthenticationFailureHandler(){
                    /**
                     *在授权失败之后应该做的事情，重定向页面，还是返回成功json为ajax服务
                     * @param request
                     * @param response
                     * @param exception
                     * @throws IOException
                     * @throws ServletException
                     */
                    @Override
                    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
//                        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Authentication failed");
                        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                        response.getWriter().write("error .....");
                    }
                })
                .and()
                .rememberMe()
                .tokenValiditySeconds(2419200)
                .key("saylovewall")
                .and()
                .logout()
                .logoutSuccessHandler(new LogoutSuccessHandler() {
                    @Override
                    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
                        response.getWriter().write("成功退出.....");
                    }
                })
                .logoutUrl("/logout")
                .permitAll();
    }
}

