package org.example.trends_backend.TweetService;

import org.example.trends_backend.TweetService.Controller.TweetController;
import org.example.trends_backend.TweetService.DTO.SaveTweetDTO;
import org.example.trends_backend.TweetService.Model.Tweet;
import org.example.trends_backend.TweetService.Reository.TagsRepository;
import org.example.trends_backend.TweetService.Reository.TweetRepository;
import org.example.trends_backend.TweetService.Service.TweetService;
import org.example.trends_backend.UserService.Controller.UserController;
import org.example.trends_backend.UserService.DTO.LoginDTO;
import org.example.trends_backend.UserService.DTO.UserSignupDTO;
import org.example.trends_backend.UserService.Repository.RoleRepository;
import org.example.trends_backend.UserService.Repository.TokenRepository;
import org.example.trends_backend.UserService.Repository.UserRepository;
import org.example.trends_backend.UserService.Service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class Tags {
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
    org.example.trends_backend.TweetService.Reository.TweetRepository TweetRepository;
    @Autowired
    TweetRepository tweetRepository;
    @Autowired
    TagsRepository tagsRepository;

    public void resetDatabase() {
        tokenRepository.deleteAll();
        userRepository.deleteAll();
        roleRepository.deleteAll();
        tweetRepository.deleteAll();
        tagsRepository.deleteAll();
    }
    public void Signup(){
        UserSignupDTO userSignupDTO = new UserSignupDTO();
        userSignupDTO.setUsername("Utkarsh");
        userSignupDTO.setPassword("123456789");
        userSignupDTO.setRole("Master");
        userController.signup(userSignupDTO);

        userSignupDTO.setUsername("Akash");
        userSignupDTO.setPassword("1234567890");
        userSignupDTO.setRole("GrandMaster");
        userController.signup(userSignupDTO);

    }

    public String AkashToken(){
        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setUsername("Akash");
        loginDTO.setPassword("1234567890");
        String token = userController.Login(loginDTO);
        return token;
    }
    public String UtkarshToken(){
        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setUsername("Utkarsh");
        loginDTO.setPassword("123456789");
        String token = userController.Login(loginDTO);
        return token;
    }


    @Test
    public void getAllTweetsByTag(){

        resetDatabase();
        Signup();

        String token = UtkarshToken();
        String Akashtoken = AkashToken();

        // 3. Create the tag "Profile" (ensuring it's unique)
        org.example.trends_backend.TweetService.Model.Tags tags = new org.example.trends_backend.TweetService.Model.Tags();
        tags.setName("Profile");



        // 4. Create and save a tweet with the "Profile" tag
        SaveTweetDTO saveTweetDTO = new SaveTweetDTO();
        saveTweetDTO.setAuthor("Utkarsh");
        saveTweetDTO.setToken(token);
        saveTweetDTO.setText("Tagging testcase");

        Set<org.example.trends_backend.TweetService.Model.Tags> tagSet = new HashSet<>();
        tagSet.add(tags); // Add the "Profile" tag
        saveTweetDTO.setTags(tagSet);
        tweetController.makeTweet(saveTweetDTO);

        // 5. Save another tweet with the same tag but different author/text
        saveTweetDTO.setAuthor("Akash");
        saveTweetDTO.setText("Tagging testcase2");
        tweetController.makeTweet(saveTweetDTO);

        saveTweetDTO.setAuthor("Akash Verma");
        saveTweetDTO.setText("I am Back");
        saveTweetDTO.setToken(Akashtoken);

        tweetController.makeTweet(saveTweetDTO);

        List<Tweet> tweetList = tweetController.getAllTweetsByTag("Profile");

        assertEquals(tweetList.get(0).getText(), "Tagging testcase");
        assertEquals(tweetList.get(1).getText(), "Tagging testcase2");
        assertEquals(tweetList.get(2).getText(), "I am Back");


    }
}
