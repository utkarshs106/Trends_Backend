package org.example.trends_backend.TweetService.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SaveTweetDTO {
   private String text;
   private String author;
   private String token;
}
