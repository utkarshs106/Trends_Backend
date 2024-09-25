package org.example.trends_backend.TweetService.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateTweetDTO {
   private int tweetId;
   private String tweetText;
   private String authorName;
   private String token;
}
