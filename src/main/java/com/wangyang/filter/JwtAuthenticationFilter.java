package com.wangyang.filter;

import com.wangyang.util.TokenAuthenticationHelper;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

@Deprecated
public class JwtAuthenticationFilter extends OncePerRequestFilter {



    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {

        try {
            System.out.println("doFilterInternal----------请求过滤");
            Authentication authentication = TokenAuthenticationHelper.getAuthentication(httpServletRequest);
//            new HttpStatusLoginFailureHandler().onAuthenticationFailure (httpServletRequest, httpServletResponse, new InsufficientAuthenticationException("JWT is Empty"));
            // 对用 token 获取到的用户进行校验
//            Collection<? extends GrantedAuthority> authorities = Arrays.stream("ROLE_admin,".split(","))
//                    .map(SimpleGrantedAuthority::new)
//                    .collect(Collectors.toList());
//            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken("admin", null, authorities);

            SecurityContextHolder.getContext().setAuthentication(authentication);
            filterChain.doFilter(httpServletRequest, httpServletResponse);
        } catch (Exception e) {
           httpServletResponse.getWriter().write(e.getMessage());
        }

    }
    class HttpStatusLoginFailureHandler implements AuthenticationFailureHandler{

        @Override
        public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                            AuthenticationException exception) throws IOException, ServletException {
            response.getWriter().write("message"+exception.getMessage());
            return;
//        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        }

    }


}


