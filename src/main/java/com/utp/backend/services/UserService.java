package com.utp.backend.services;

import com.utp.backend.models.User;

public interface UserService {
    public User createUser(User user);
    public User login(User user);    
}
