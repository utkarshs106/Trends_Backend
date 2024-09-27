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

    @Query(value = """
            SELECT 
                t1.id,
                t1.author,
                t1.created_at,
                t1.dislikes,
                t1.likes,
                t1.retweet_count,
                t1.text
            FROM Tweet t1
            JOIN tweet_tags twta ON t1.id = twta.id
            JOIN Tags ta ON twta.tagid = ta.Tagid
            WHERE ta.name = :TagName
            """, nativeQuery = true)
    List<Tweet> findAllByTagName(@Param("TagName") String TagName);

    @Modifying
    @Transactional
    @Query("update Tweet t set t.text = :tweet , t.author = :author where t.id = :id")
    void updateTweet(@Param("id") int id, @Param("tweet") String tweet,@Param("author") String author);


}
