package com.wangyang.security;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.ArrayList;
import java.util.List;

public class TestSpringSecurity {
    private static AuthenticationManager am=new SampleAuthenticationMannager() ;
    public static void main(String[] args) {
        try {
            Authentication request = new UsernamePasswordAuthenticationToken("admin","admin1");
            Authentication result = am.authenticate(request);
            SecurityContextHolder.getContext().setAuthentication(request);
        } catch (AuthenticationException e) {
            System.out.println("Authentication failed: " + e.getMessage());
        }
        System.out.println("Successfully authenticated. Security context contains: " +
                SecurityContextHolder.getContext().getAuthentication());

    }
}
class SampleAuthenticationMannager implements AuthenticationManager{
    static final List<GrantedAuthority> AUTHORITIES = new ArrayList<GrantedAuthority>();
    static {
        AUTHORITIES.add(new SimpleGrantedAuthority("ROLE_USER"));
    }
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        if (authentication.getName().equals(authentication.getCredentials())){
            return new UsernamePasswordAuthenticationToken(authentication.getName(),authentication.getCredentials(),AUTHORITIES);
        }
        throw   new BadCredentialsException("Bad Credentials");
    }
}
