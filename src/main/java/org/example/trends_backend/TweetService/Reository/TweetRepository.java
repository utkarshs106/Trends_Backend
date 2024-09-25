package org.example.trends_backend.TweetService.Reository;

import jakarta.transaction.Transactional;
import org.example.trends_backend.TweetService.Model.Tweet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TweetRepository extends JpaRepository<Tweet, Integer> {

    Tweet save(Tweet tweet);
    Tweet findById(int tweetId);
    List<Tweet> findByAuthor(String authorName);
    void deleteById(int tweetId);
    void deleteAll();

    @Modifying
    @Transactional
    @Query("update Tweet t set t.text = :tweet , t.author = :author where t.id = :id")
    void updateTweet(@Param("id") int id, @Param("tweet") String tweet,@Param("author") String author);
}
