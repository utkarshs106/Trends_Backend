package org.example.trends_backend.TweetService.Service;

import jakarta.persistence.EntityManager;
import org.example.trends_backend.TweetService.DTO.FetchFeedDTO;
import org.example.trends_backend.TweetService.DTO.SaveTweetDTO;
import org.example.trends_backend.TweetService.DTO.UpdateTweetDTO;
import org.example.trends_backend.TweetService.Model.*;
import org.example.trends_backend.TweetService.Reository.TagsRepository;
import org.example.trends_backend.TweetService.Reository.TweetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.sql.SQLOutput;
import java.util.*;

@Service
public class TweetService {
    @Autowired
    TweetRepository tweetRepository;
    @Autowired
    TagsRepository tagsRepository;

    Date currentDate = new Date();

    public int makeTweet(SaveTweetDTO tweetDTO) {
        Tweet tweet = new Tweet();
        tweet.setAuthor(tweetDTO.getAuthor());
        tweet.setText(tweetDTO.getText());
        tweet.setCreatedAt(currentDate.toString());
       tweet.setTags(tweetDTO.getTags());

        Set<Tags> uniqueTags = new HashSet<>();

        for (Tags tag : tweetDTO.getTags()) {
            // Check if the tag already exists in the database
            Tags existingTag = tagsRepository.findByName(tag.getName());
            if (existingTag!=null) {
                uniqueTags.add(existingTag); // Use existing tag
            } else {
                uniqueTags.add(tag); // Add new tag if it doesn't exist
            }
        }
        for(Tags tag : uniqueTags) {
            System.out.println("Print" + tag.getName()+tag.getTagid() + tag.getTweet());
        }
        tweet.setTags(uniqueTags); // Associate tags with tweet

        // Save the tweet, this will also save new tags if any
        tweetRepository.save(tweet);
        return tweet.getId(); // Return the ID of the saved tweet
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
   public List<Tweet> getfeed(FetchFeedDTO fetchFeedDTO){
        List<String> author = fetchFeedDTO.getAuthor();
        List<Tags> tags = fetchFeedDTO.getTags();
        List<Tweet> tweets = new ArrayList<>();
        for(Tags tag : tags){
           List<Tweet> tweetList =  tweetRepository.findAllByTagName(tag.getName());
            tweets.addAll(tweetList);
        }
        for(String authorName : author){
            List<Tweet> tweetList = tweetRepository.findByAuthor(authorName);
            tweets.addAll(tweetList);
        }
       Collections.shuffle(tweets);
        return tweets;
   }
}
