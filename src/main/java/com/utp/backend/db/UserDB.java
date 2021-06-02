package com.utp.backend.db;

import com.utp.backend.models.User;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDB extends MongoRepository<User, String> {

    public User findByUsernameAndPassword(String user, String psswd);
    
}
