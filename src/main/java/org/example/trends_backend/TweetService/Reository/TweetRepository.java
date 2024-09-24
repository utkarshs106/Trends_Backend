package org.example.trends_backend.TweetService.Reository;

import org.example.trends_backend.TweetService.Model.Tweet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TweetRepository extends JpaRepository<Tweet, Long> {

    Tweet save(Tweet tweet);
    Tweet findById(int tweetId);
    List<Tweet> findByAuthor(String authorName);
    void deleteById(int tweetId);
}
