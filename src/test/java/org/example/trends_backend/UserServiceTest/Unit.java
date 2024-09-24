package org.example.trends_backend.UserServiceTest;

import io.jsonwebtoken.Claims;
import org.example.trends_backend.UserService.Controller.UserController;
import org.example.trends_backend.UserService.DTO.LoginDTO;
import org.example.trends_backend.UserService.DTO.TokenResponse;
import org.example.trends_backend.UserService.DTO.UserSignupDTO;
import org.example.trends_backend.UserService.DTO.VerifyTokenDTO;
import org.example.trends_backend.UserService.Repository.UserRepository;
import org.example.trends_backend.UserService.Service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class Unit {
    @Autowired
    UserController userController;
    @Autowired
    UserService userService;
    @Autowired
    UserRepository userRepository;


    @Test
    public void Unit1(){

        UserSignupDTO userSignupDTO = new UserSignupDTO();
        userSignupDTO.setUsername("Utkarsh");
        userSignupDTO.setPassword("123456789");
        userSignupDTO.setRole("Master");
        userController.signup(userSignupDTO);

        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setUsername("Utkarsh");
        loginDTO.setPassword("123456789");
        String token = userController.Login(loginDTO);

        VerifyTokenDTO tokenDTO = new VerifyTokenDTO();
        tokenDTO.setToken(token);
        TokenResponse response =  userController.verifyToken(tokenDTO);

        assertEquals(response.getRole(), "Master");
        assertEquals(response.getName(), "Utkarsh");


    }
}
