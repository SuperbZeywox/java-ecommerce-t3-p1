package org.example.javaecommercet3p1.security.jwt;

import org.example.javaecommercet3p1.security.UserPrincipal;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;

//import javax.servlet.http.HttpServletRequest;

public interface IJwtProvider {


    String generateToken(UserPrincipal auth);

    Authentication getAuthentication(HttpServletRequest request);

    boolean validateToken(HttpServletRequest request);
}
