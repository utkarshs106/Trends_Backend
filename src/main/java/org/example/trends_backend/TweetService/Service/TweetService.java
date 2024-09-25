package org.example.trends_backend.TweetService.Service;

import org.example.trends_backend.TweetService.DTO.SaveTweetDTO;
import org.example.trends_backend.TweetService.DTO.UpdateTweetDTO;
import org.example.trends_backend.TweetService.Model.*;
import org.example.trends_backend.TweetService.Reository.TweetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class TweetService {
    @Autowired
    TweetRepository tweetRepository;
    Date currentDate = new Date();

    public int makeTweet(SaveTweetDTO tweet){
        Tweet t1 = new Tweet();
        t1.setAuthor(tweet.getAuthor());
        t1.setLikes(0);
        t1.setDislikes(0);
        t1.setText(tweet.getText());
        t1.setRetweetCount(0);
        t1.setCreatedAt(currentDate.toString());
        t1.setTags(tweet.getTags());


       Tweet response =  tweetRepository.save(t1);
       return response.getId();
    }
    public List<Tweet> getTweetsList(String author){
        return tweetRepository.findByAuthor(author);
    }
    public Tweet getTweet(int id){
        return tweetRepository.findById(id);
    }
    public void deleteTweet(int id){
        tweetRepository.deleteById(id);
    }
    public void updateTweet(UpdateTweetDTO tweetDTO){
       tweetRepository.updateTweet(tweetDTO.getTweetId(),tweetDTO.getTweetText(),tweetDTO.getAuthorName());;
    }
    public List<Tweet> getAllTweetsByTagName(String Tagname){
       return tweetRepository.findAllByTagName(Tagname);
    }
}
