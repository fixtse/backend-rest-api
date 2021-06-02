package com.utp.backend.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;
import lombok.NoArgsConstructor;


@Document(collection = "grades")
@NoArgsConstructor
@Data
public class Grade {

    private @Id String id;   
    private String user;
    private String asignatura;
    private Long ciclo;
    private String date;
    private Long Nota;


    public Grade(String user, String asignatura, Long ciclo, String date, Long Nota) {        
        this.user = user;
        this.asignatura = asignatura;
        this.ciclo = ciclo;
        this.date = date;
        this.Nota = Nota;
    }
    
}
