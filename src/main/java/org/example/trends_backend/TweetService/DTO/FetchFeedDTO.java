package org.example.trends_backend.TweetService.DTO;

import lombok.Getter;
import lombok.Setter;
import org.example.trends_backend.TweetService.Model.Tags;

import java.util.List;

@Getter
@Setter
public class FetchFeedDTO {
    private String token;
    private List<Tags> tags;
    public List<String> author;
}
