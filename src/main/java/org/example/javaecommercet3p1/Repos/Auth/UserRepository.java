package org.example.javaecommercet3p1.Repos.Auth;

import org.example.javaecommercet3p1.Entities.Auth.Role;
import org.example.javaecommercet3p1.Entities.Auth.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);
    Optional<User> findUserById(Long id);

//    @Modifying
//    @Query("update User set role = :role where username = :username")
//    void updateUserRole(@Param("username") String username,
//                        @Param("role") Role role);

    @Modifying
    @Query("update User set role = :role where username = :username")
    void updateUserRole(@Param("username") String username, @Param("role") Role role);

//    User findUserByEmail(String theEmail);

    User findUserByEmail(String theEmail);













}
