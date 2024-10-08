package org.example.trends_backend.TweetService.Controller;

import org.example.trends_backend.TweetService.DTO.FetchFeedDTO;
import org.example.trends_backend.TweetService.DTO.FetchTweetDTO;
import org.example.trends_backend.TweetService.DTO.SaveTweetDTO;
import org.example.trends_backend.TweetService.DTO.UpdateTweetDTO;
import org.example.trends_backend.TweetService.Model.Tweet;
import org.example.trends_backend.TweetService.Reository.TweetRepository;
import org.example.trends_backend.TweetService.Service.TweetService;
import org.example.trends_backend.UserService.Controller.UserController;
import org.example.trends_backend.UserService.DTO.TokenResponse;
import org.example.trends_backend.UserService.DTO.VerifyTokenDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Tweet")
public class TweetController {
    @Autowired
    TweetService tweetService;
    @Autowired
    UserController userController;
    @Autowired
    private TweetRepository tweetRepository;

    TokenResponse validaitingToken(String token) {
        VerifyTokenDTO verifyTokenDTO = new VerifyTokenDTO();
        verifyTokenDTO.setToken(token);

        return userController.verifyToken(verifyTokenDTO);
    }

    @PostMapping("/makeTweet")
    public int makeTweet(@RequestParam SaveTweetDTO tweet){
      TokenResponse response = validaitingToken(tweet.getToken());
      if(response.getName() != null){
         return tweetService.makeTweet(tweet);
      }else{
          System.out.println("token unverified");
      }
        return 0;
    }

    @PatchMapping("/update")
    public void updateTweet(@RequestParam UpdateTweetDTO tweet){
        TokenResponse response = validaitingToken(tweet.getToken());
        if(response.getName() != null){
            tweetService.updateTweet(tweet);
        }
    }

    @GetMapping("/get")
    public Tweet getTweetById(@RequestParam FetchTweetDTO fetchTweetDTOn){
        TokenResponse response = validaitingToken(fetchTweetDTOn.getToken());
        if(response != null){

            System.out.println("Error Printed"+fetchTweetDTOn.getId());
            return  tweetService.getTweet(fetchTweetDTOn.getId());
        }else{
            System.out.println("token unverified");
            return null;
        }
    }

    @GetMapping("getAll/{author}")
    public List<Tweet> getAllTweets(@PathVariable String author){
       return tweetService.getTweetsList(author);
    }
    @DeleteMapping("/del{id}")
    public void deleteTweet(@PathVariable int id){
        tweetService.deleteTweet(id);
    }

    @GetMapping("/allTweetByTag{tag}")
    public List<Tweet> getAllTweetsByTag(@PathVariable String tag){
        return tweetService.getAllTweetsByTagName(tag);
    }
    @PostMapping("/getFeed")
    public List<Tweet> getTweetsForFeed(@RequestBody FetchFeedDTO fetchFeedDTO){
        TokenResponse response = validaitingToken(fetchFeedDTO.getToken());
        if(response != null){
            return tweetService.getfeed(fetchFeedDTO);
        }
        return null;
    }

}
