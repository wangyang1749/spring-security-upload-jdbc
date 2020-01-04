package com.wangyang.advice;

import com.wangyang.pojo.R;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * global exception and response decoration
 */
//@RestControllerAdvice
public class ResultResponseAdvice implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        System.out.println("beforeBodyWrite---响应封装");
        if (body instanceof R) {
            return body;
        }

        return R.ok("success",body);
//            return new ResponseEntity<R>(R.ok("success",body), HttpStatus.UNAUTHORIZED);

    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity error404(Exception ex) {
        return  new ResponseEntity<R>(R.error(ex.getMessage()),HttpStatus.NOT_FOUND);
//        return R.error(ex.getMessage());
    }
}
