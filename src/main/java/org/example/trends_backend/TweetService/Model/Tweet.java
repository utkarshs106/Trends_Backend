package org.example.trends_backend.TweetService.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Entity

public class Tweet {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;
    String text;
    String author;
    String createdAt;
    int retweetCount;
    int likes;
    int dislikes;

}
