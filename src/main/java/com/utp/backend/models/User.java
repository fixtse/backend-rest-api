package com.utp.backend.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Document(collection = "users")
@NoArgsConstructor
@Data
public class User {

    private @Id String id;   
    private @Indexed(unique = true) @NonNull String username;
    private @NonNull String password;

    public User(String username, String password) {
        
        this.username = username;
        this.password = password;
    }
    
}
