package com.uabc.edu.mx.tecmonkey.filters;

import com.uabc.edu.mx.tecmonkey.service.MyUserDetailsService;
import com.uabc.edu.mx.tecmonkey.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    @Autowired
    private MyUserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request,HttpServletResponse response,FilterChain chain) throws ServletException, IOException {


        Cookie[] cookies = request.getCookies();
        Cookie jwtCookie = new Cookie("jwt",null);
        for (Cookie cookie : Arrays.asList(cookies)) {
            if(cookie.getName().equals("jwt")){
                jwtCookie = cookie;
                break;
            }
        }
         String authorizationCookie = jwtCookie.getValue();
      /*  System.out.println(authorizationCookie);
        for (int x =0;x<cookies.length;x++){
            System.out.println("Nombre "+cookies[x].getName());
            System.out.println("Valor "+cookies[x].getValue());
        }*/

        final String authorizationHeader = authorizationCookie;
        String username = null;
        String jwt = null;
        if (authorizationHeader !=null && authorizationHeader.startsWith("Bearer")){
            jwt = authorizationHeader.substring(7);
            username = jwtUtil.extractUsername(jwt);
        }
        if (username !=null && SecurityContextHolder.getContext().getAuthentication()==null){
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
            if (jwtUtil.validateToken(jwt,userDetails)){
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                        userDetails,null,userDetails.getAuthorities());
                usernamePasswordAuthenticationToken
                        .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }
        chain.doFilter(request,response);
    }
}
