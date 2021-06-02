package com.utp.backend.util;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.security.Key;

public class GetKey {

    private static Key key;


    public static Key generate() {
        if (key == null){
            key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
        } 
        return key;      
    }
    
    
}
