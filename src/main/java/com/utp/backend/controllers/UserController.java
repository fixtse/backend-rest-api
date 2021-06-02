package com.utp.backend.controllers;

import java.util.Date;

import com.utp.backend.models.User;
import com.utp.backend.services.UserServiceIMP;
import com.utp.backend.util.GetKey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;

@RestController
@CrossOrigin("*")
public class UserController {
    
    @Autowired
    private UserServiceIMP uService;   

    @RequestMapping(value="/newuser", method = RequestMethod.POST)
    public ResponseEntity<String> createUser (@RequestBody User u, @RequestHeader("token") String token){

        try {            

            Jwts.parserBuilder().requireSubject("LOGIN").setSigningKey(GetKey.generate()).build().parseClaimsJws(token);            
            String val = validacion(u);
            if (val.isEmpty()){
                uService.createUser(u);        
                return ResponseEntity.status(HttpStatus.CREATED).body("Usuario Creado Satisfactoriamente");
            }else{
                return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(val);
            }
            
        } catch (JwtException e) {
        
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("ERROR TOKEN");
        }
    }
    
    @RequestMapping(value="/login", method = RequestMethod.POST)
    public ResponseEntity<String> login (@RequestBody User u){
        String val = validacion(u);
        if (val.isEmpty()){
            User u2 = uService.login(u);
            if (u2 != null){                
                String jws = Jwts.builder()
                    .setIssuer("UTP")
                    .setSubject("LOGIN")
                    .claim("name", u.getUsername())                  
                    .setIssuedAt(new Date())
                    .signWith(GetKey.generate())
                    .compact();
                return ResponseEntity.status(HttpStatus.ACCEPTED).body(jws);
            }else{
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Usuario o contraseña incorrectos");
            }
        }else{
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(val);
        }
        
        
    }  
    
    private String validacion(User u){
        String val = "";
        
        if (u.getUsername() == null){
            val += "ERROR, falta el nombre de usuario \n";
        }else if(u.getUsername().length() <= 3){
            val += "ERROR, el nombre de usuario debe ser mayor a 3 dígitos";
        }

        if (u.getPassword() == null){
            val += "ERROR, falta la contraseña \n";
        }else{
            if(u.getPassword().length() <= 5){
                val += "ERROR, la contraseña debe ser mayor a 5 dígitos";
            }
            if (u.getPassword().contains(u.getUsername())){
                val += "ERROR, la contraseña no puede contener el nombre de usuario";
            }
        }

        return val;
    }
}
