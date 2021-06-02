package com.utp.backend.services;

import com.utp.backend.db.UserDB;
import com.utp.backend.models.User;

import org.springframework.stereotype.Service;


@Service
public class UserServiceIMP implements UserService {

    private final UserDB userDB;


    public UserServiceIMP(UserDB userDB) {
        this.userDB = userDB;
    }
    

    @Override
    public User createUser(User user) {
        
        return userDB.save(user);
    }

    @Override
    public User login(User user) {
        
        return userDB.findByUsernameAndPassword(user.getUsername(), user.getPassword());
        
    }
    
}
