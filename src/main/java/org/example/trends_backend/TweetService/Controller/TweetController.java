package org.example.trends_backend.TweetService.Controller;

import org.example.trends_backend.TweetService.DTO.TweetDTO;
import org.example.trends_backend.TweetService.Model.Tweet;
import org.example.trends_backend.TweetService.Service.TweetService;
import org.example.trends_backend.UserService.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Tweet")
public class TweetController {
    @Autowired
    TweetService tweetService;

    @PostMapping("/makeTweet")
    public void makeTweet(@RequestParam TweetDTO tweet){
       tweetService.makeTweet(tweet);
    }

    @GetMapping("/get{id}")
    public Tweet getTweetById(@PathVariable int id){
      return   tweetService.getTweet(id);
    }
    @GetMapping("getAll/{author}")
    public List<Tweet> getAllTweets(@PathVariable String author){
       return tweetService.getTweetsList(author);
    }
    @DeleteMapping("/del{id}")
    public void deleteTweet(@PathVariable int id){
        tweetService.deleteTweet(id);
    }

}
