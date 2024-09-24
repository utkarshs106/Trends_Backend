package org.example.trends_backend.UserService.Controller;

import org.example.trends_backend.UserService.DTO.LoginDTO;
import org.example.trends_backend.UserService.DTO.TokenResponse;
import org.example.trends_backend.UserService.DTO.UserSignupDTO;
import org.example.trends_backend.UserService.DTO.VerifyTokenDTO;
import org.example.trends_backend.UserService.Exception.SmallPasswordException;
import org.example.trends_backend.UserService.Exception.UserNotFoundException;
import org.example.trends_backend.UserService.Model.User;
import org.example.trends_backend.UserService.Service.UserService;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/UserController")
public class UserController {
    String key = "csc8-23ij-ed90-de90";
    @Autowired
    public UserService userService;

    @PostMapping("/signup")
    @ExceptionHandler(SmallPasswordException.class)
    public User signup(@RequestBody UserSignupDTO userSignupDTO) {
        System.out.println(userSignupDTO.getUsername());
        System.out.println(userSignupDTO.getPassword());
        System.out.println(userSignupDTO.getRole());
       return userService.signup(userSignupDTO.getUsername(),userSignupDTO.getPassword(),userSignupDTO.getRole());
    }

    @PostMapping("/login")
    @ExceptionHandler(UserNotFoundException.class)
    public String Login(@RequestBody LoginDTO loginDTO) {
        // The token is returned
        return userService.login(loginDTO.getUsername(),loginDTO.getPassword(),key);
    }

    @PostMapping("/generateToken")
    public String generateToken(@RequestBody VerifyTokenDTO verifyTokenDTO) {
        return  null;
    }

    @PostMapping("/verifyToken")
    public TokenResponse verifyToken(@RequestBody VerifyTokenDTO verifyTokenDTO) {
        return userService.verifyToken(verifyTokenDTO.getToken(),key);
    }
}
