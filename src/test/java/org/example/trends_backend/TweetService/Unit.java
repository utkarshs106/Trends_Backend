package org.example.trends_backend.TweetService;

import org.example.trends_backend.TweetService.Controller.TweetController;
import org.example.trends_backend.TweetService.DTO.FetchTweetDTO;
import org.example.trends_backend.TweetService.DTO.SaveTweetDTO;
import org.example.trends_backend.TweetService.DTO.UpdateTweetDTO;
import org.example.trends_backend.TweetService.Model.Tweet;
import org.example.trends_backend.TweetService.Reository.TweetRepository;
import org.example.trends_backend.TweetService.Service.TweetService;
import org.example.trends_backend.UserService.Controller.UserController;
import org.example.trends_backend.UserService.DTO.LoginDTO;
import org.example.trends_backend.UserService.DTO.UserSignupDTO;
import org.example.trends_backend.UserService.Repository.RoleRepository;
import org.example.trends_backend.UserService.Repository.TokenRepository;
import org.example.trends_backend.UserService.Repository.UserRepository;
import org.example.trends_backend.UserService.Service.UserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class Unit {
    @Autowired
    UserController userController;
    @Autowired
    UserService userService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    TweetService tweetService;
    @Autowired
    TweetController tweetController;
    @Autowired
    TokenRepository tokenRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    TweetRepository TweetRepository;
    @Autowired
    private TweetRepository tweetRepository;


    @Test
    public void makeTweet(){

        tokenRepository.deleteAll();
        userRepository.deleteAll();
        roleRepository.deleteAll();
        tweetRepository.deleteAll();

        UserSignupDTO userSignupDTO = new UserSignupDTO();
        userSignupDTO.setUsername("Utkarsh");
        userSignupDTO.setPassword("123456789");
        userSignupDTO.setRole("Master");
        userController.signup(userSignupDTO);

        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setUsername("Utkarsh");
        loginDTO.setPassword("123456789");
        String token = userController.Login(loginDTO);
        SaveTweetDTO saveTweetDTO = new SaveTweetDTO();

        saveTweetDTO.setAuthor("Utkarsh");
        saveTweetDTO.setToken(token);
        saveTweetDTO.setText("Hello World");

       int id =  tweetController.makeTweet(saveTweetDTO);
        FetchTweetDTO fetchTweetDTO = new FetchTweetDTO();
        fetchTweetDTO.setId(id);
        fetchTweetDTO.setToken(token);

       Tweet res =  tweetController.getTweetById(fetchTweetDTO);

       assertEquals("Hello World", res.getText());
       assertEquals("Utkarsh", res.getAuthor());

    }
    @Test
    public  void makelistOfTweet(){

        tokenRepository.deleteAll();
        userRepository.deleteAll();
        roleRepository.deleteAll();
        tweetRepository.deleteAll();

        UserSignupDTO userSignupDTO = new UserSignupDTO();
        userSignupDTO.setUsername("Utkarsh");
        userSignupDTO.setPassword("123456789");
        userSignupDTO.setRole("Master");
        userController.signup(userSignupDTO);

        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setUsername("Utkarsh");
        loginDTO.setPassword("123456789");
        String token = userController.Login(loginDTO);
        SaveTweetDTO saveTweetDTO = new SaveTweetDTO();

        saveTweetDTO.setAuthor("Utkarsh");
        saveTweetDTO.setToken(token);
        saveTweetDTO.setText("Hello World");

        int id =  tweetController.makeTweet(saveTweetDTO);
        FetchTweetDTO fetchTweetDTO = new FetchTweetDTO();
        fetchTweetDTO.setId(id);
        fetchTweetDTO.setToken(token);
        Tweet res =  tweetController.getTweetById(fetchTweetDTO);

        assertEquals("Hello World", res.getText());
        assertEquals("Utkarsh", res.getAuthor());

        saveTweetDTO.setText("I am doing great");
        saveTweetDTO.setAuthor("Utkarsh");

        int id2 = tweetController.makeTweet(saveTweetDTO);

       List<Tweet> list =  tweetController.getAllTweets("Utkarsh");
       assertEquals( id,list.get(0).getId());
       assertEquals(id2,list.get(1).getId());
       assertEquals("Hello World", list.get(0).getText());
       assertEquals("I am doing great", list.get(1).getText());

    }
    @Test
    public void updateTweet(){
        tokenRepository.deleteAll();
        userRepository.deleteAll();
        roleRepository.deleteAll();
        tweetRepository.deleteAll();

        UserSignupDTO userSignupDTO = new UserSignupDTO();
        userSignupDTO.setUsername("Utkarsh");
        userSignupDTO.setPassword("123456789");
        userSignupDTO.setRole("Master");
        userController.signup(userSignupDTO);

        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setUsername("Utkarsh");
        loginDTO.setPassword("123456789");
        String token = userController.Login(loginDTO);
        SaveTweetDTO saveTweetDTO = new SaveTweetDTO();

        String tweetText = "Hello World";
        saveTweetDTO.setAuthor("Utkarsh");
        saveTweetDTO.setToken(token);
        saveTweetDTO.setText(tweetText);

        int id =  tweetController.makeTweet(saveTweetDTO);
        UpdateTweetDTO updateTweetDTO = new UpdateTweetDTO();

        updateTweetDTO.setTweetText("Welcome");
        updateTweetDTO.setAuthorName("Utkarsh");
        updateTweetDTO.setToken(token);
        updateTweetDTO.setTweetId(id);

        tweetController.updateTweet(updateTweetDTO);

        FetchTweetDTO fetchTweetDTO = new FetchTweetDTO();
        fetchTweetDTO.setId(id);
        fetchTweetDTO.setToken(token);

        assertEquals("Welcome", tweetController.getTweetById(fetchTweetDTO).getText());
    }

}
