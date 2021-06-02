package com.utp.backend.services;

import java.util.List;

import com.utp.backend.models.Grade;

public interface GradeService {
    public Grade newGrade(Grade grade);
    public List<Grade> listGrades(String user);
    public List<Grade> listGrades();
    
}
