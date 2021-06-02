package com.utp.backend.services;

import java.util.List;

import com.utp.backend.db.GradeDB;
import com.utp.backend.models.Grade;

import org.springframework.stereotype.Service;

@Service
public class GradeServiceIMP implements GradeService {

    private final GradeDB gradeDB;


    public GradeServiceIMP(GradeDB gradeDB) {
        this.gradeDB = gradeDB;
    }

    @Override
    public Grade newGrade(Grade grade) {
        
        return gradeDB.save(grade);
    }

    @Override
    public List<Grade> listGrades(String user) {
        
        return gradeDB.findAllByUser(user);
    }

    @Override
    public List<Grade> listGrades() {
        
        return gradeDB.findAll();
    }

    
    
}
