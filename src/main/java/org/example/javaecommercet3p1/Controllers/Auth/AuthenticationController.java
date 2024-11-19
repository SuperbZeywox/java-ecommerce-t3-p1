package org.example.javaecommercet3p1.Controllers.Auth;


import org.example.javaecommercet3p1.Entities.Auth.User;
import org.example.javaecommercet3p1.Services.Auth.AuthenticationService;
import org.example.javaecommercet3p1.Services.Auth.User.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/authentication")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;
    @Autowired
    private IUserService userService;

    @PostMapping("sign-up") //
    public ResponseEntity<User> signUp(@RequestBody User user) {

//        return null; // 41
//        return new ResponseEntity<>(HttpStatus.OK); //added
        if (userService.findByUsername(user.getUsername()).isPresent())
        {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(userService.saveUser(user), HttpStatus.CREATED);

    }

    @PostMapping("sign-in")//api/authentication/sign-in
    public ResponseEntity<?> signIn(@RequestBody User user)
    {
        return new ResponseEntity<>(authenticationService.signInAndReturnJWT(user), HttpStatus.OK);
    }






}
