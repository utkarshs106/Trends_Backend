package org.example.trends_backend.TweetService.Service;

import org.example.trends_backend.TweetService.DTO.TweetDTO;
import org.example.trends_backend.TweetService.Model.Tweet;
import org.example.trends_backend.TweetService.Reository.TweetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TweetService {
    @Autowired
    TweetRepository tweetRepository;
    Date currentDate = new Date();

    public void makeTweet(TweetDTO tweet){
        Tweet t1 = new Tweet();
        t1.setAuthor(tweet.getAuthor());
        t1.setLikes(0);
        t1.setDislikes(0);
        t1.setText(tweet.getText());
        t1.setRetweetCount(0);
        t1.setCreatedAt(currentDate.toString());
        tweetRepository.save(t1);
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
}
