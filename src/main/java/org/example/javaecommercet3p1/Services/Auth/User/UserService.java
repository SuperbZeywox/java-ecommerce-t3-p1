package org.example.javaecommercet3p1.Services.Auth.User;

import org.example.javaecommercet3p1.Entities.Auth.Role;
import org.example.javaecommercet3p1.Entities.Auth.User;
import org.example.javaecommercet3p1.Repos.Auth.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UserService implements IUserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired @Qualifier("passwordEncoder")
//    @Autowired @Lazy
    private PasswordEncoder passwordEncoder;


    @Override
    public User saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(Role.USER);
        user.setCreatedTime(LocalDateTime.now());
        return userRepository.save(user);

    }

    @Override
    public Optional<User> findByUsername(String username) {
        System.out.println("findByUsername");
        return userRepository.findByUsername(username);
    }

    @Override
//    @Transactional // required when executing an update/delete query.
    @Transactional(transactionManager = "authTransactionManager") // required when executing an update/delete query.
    public void makeAdmin(String username) {
        userRepository.updateUserRole(username, Role.ADMIN);
    }




}
