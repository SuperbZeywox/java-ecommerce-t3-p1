package org.example.javaecommercet3p1.Services.Auth.User;

import org.example.javaecommercet3p1.Entities.Auth.User;

import java.util.Optional;

public interface IUserService {


    User saveUser(User user);

    Optional<User> findByUsername(String username);

//    @Transactional
    void makeAdmin(String username);





}


