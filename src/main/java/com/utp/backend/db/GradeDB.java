package com.utp.backend.db;

import java.util.List;

import com.utp.backend.models.Grade;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GradeDB extends MongoRepository<Grade, String> {
    public List<Grade> findAll();
    public List<Grade> findAllByUser(String user);
}
