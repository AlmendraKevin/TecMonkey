package com.uabc.edu.mx.tecmonkey.web;

import com.uabc.edu.mx.tecmonkey.model.AuthenticationRequest;
import com.uabc.edu.mx.tecmonkey.model.AuthenticationResponse;
import com.uabc.edu.mx.tecmonkey.service.MyUserDetailsService;
import com.uabc.edu.mx.tecmonkey.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtTokenUtil;

    @Autowired
    private MyUserDetailsService userDetailsService;

    @RequestMapping(value = "/authenticate",method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception{
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(),authenticationRequest.getPassword()));

        }
        catch (BadCredentialsException e){
            throw new Exception("Credenciales incorrectas",e);
        }
        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        final String jwt = jwtTokenUtil.generateToken(userDetails);
        System.out.println(ResponseEntity.ok(new AuthenticationResponse(jwt)));
        return ResponseEntity.ok(new AuthenticationResponse(jwt));
        //sesion manager buscar
    }
}
