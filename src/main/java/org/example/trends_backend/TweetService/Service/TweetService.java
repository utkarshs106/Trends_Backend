package org.example.trends_backend.TweetService.Service;

import org.example.trends_backend.TweetService.DTO.SaveTweetDTO;
import org.example.trends_backend.TweetService.DTO.UpdateTweetDTO;
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

    public int makeTweet(SaveTweetDTO tweet){
        Tweet t1 = new Tweet();
        t1.setAuthor(tweet.getAuthor());
        t1.setLikes(0);
        t1.setDislikes(0);
        t1.setText(tweet.getText());
        t1.setRetweetCount(0);
        t1.setCreatedAt(currentDate.toString());
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
        System.out.println(tweetDTO.getTweetText());
       tweetRepository.updateTweet(tweetDTO.getTweetId(),tweetDTO.getTweetText(),tweetDTO.getAuthorName());;
    }
}
