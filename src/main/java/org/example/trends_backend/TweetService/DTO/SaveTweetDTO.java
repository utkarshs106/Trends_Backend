package org.example.trends_backend.TweetService.DTO;

import lombok.Getter;
import lombok.Setter;
import org.example.trends_backend.TweetService.Model.Tags;

import java.util.Set;

@Getter
@Setter
public class SaveTweetDTO {

   private String text;
   private String author;
   private String token;
   private Set<Tags> tags;

}

