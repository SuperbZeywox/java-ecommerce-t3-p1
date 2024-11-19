package org.example.javaecommercet3p1.Services.Auth;

import org.example.javaecommercet3p1.Entities.Auth.User;

public interface AuthenticationService {


    User signInAndReturnJWT(User signInRequest);
}
