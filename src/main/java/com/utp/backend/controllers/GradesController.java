package com.utp.backend.controllers;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.utp.backend.models.Grade;
import com.utp.backend.services.GradeServiceIMP;
import com.utp.backend.util.GetKey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;

@RestController
@CrossOrigin("*")
public class GradesController {

    @Autowired
    private GradeServiceIMP gService;

    @RequestMapping(value="/newgrade", method = RequestMethod.POST)
    public ResponseEntity<String> createGrade (@RequestBody Grade g, @RequestHeader("token") String token){

        try {            

            Jwts.parserBuilder().requireSubject("LOGIN").setSigningKey(GetKey.generate()).build().parseClaimsJws(token);            
            String val = validacion(g);

            if (val.isEmpty()){
                gService.newGrade(g);
                return ResponseEntity.status(HttpStatus.CREATED).body("Nota Registrada");
            }else{
                return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(val);
            }
        
        } catch (JwtException e) {
        
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("ERROR TOKEN");
        }
    }

    @RequestMapping(value="/byUser/{user}", method = RequestMethod.GET)
    public ResponseEntity<String> getGradesByUser(@PathVariable("user") String user, @RequestHeader("token") String token) throws JsonProcessingException {
        try {
            

            Jwts.parserBuilder().requireSubject("LOGIN").require("name", user).setSigningKey(GetKey.generate()).build().parseClaimsJws(token);
            
            List<Grade> grades = gService.listGrades(user);
            if (grades != null){
                ObjectMapper objectMapper = new ObjectMapper();
                String val = objectMapper.writeValueAsString(grades);
                return ResponseEntity.status(HttpStatus.FOUND).body(val);
            }else{
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontraron notas para este usuario");
            }
        
        } catch (JwtException e) {
        
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("ERROR TOKEN");
        }
        
        
    }

    @RequestMapping(value="/allGrades", method = RequestMethod.GET)
    public ResponseEntity<String> getGrades(@RequestHeader("token") String token) throws JsonProcessingException {

        try {
            

            Jwts.parserBuilder().requireSubject("LOGIN").setSigningKey(GetKey.generate()).build().parseClaimsJws(token);
            
            List<Grade> grades = gService.listGrades();
            if (grades != null){
                ObjectMapper objectMapper = new ObjectMapper();
                String val = objectMapper.writeValueAsString(grades);
                return ResponseEntity.status(HttpStatus.FOUND).body(val);
            }else{
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontraron notas");
            }
        
        } catch (JwtException e) {
        
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("ERROR TOKEN");
        }

        
    }

    private String validacion(Grade g){
        String val = "";
        
        if (g.getAsignatura() == null){
            val += "ERROR, falta la asignatura \n";
        }

        if (g.getCiclo() == null){
            val += "ERROR, falta el ciclo \n";
        }

        if (g.getDate() == null){
            val += "ERROR, falta la fecha \n";
        }

        if (g.getNota() == null){
            val += "ERROR, falta la nota \n";
        }else if (g.getNota() < 0L){
            val += "ERROR, la nota no puede ser negativa \n";
        }

        if (g.getUser() == null){
            val += "ERROR, falta el usuario \n";
        }

        return val;
    }
    
}
