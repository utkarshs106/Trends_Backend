package org.example.trends_backend.TweetService.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Entity

public class Tweet {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String text;
    private String author;
    private String createdAt;
    private int retweetCount;
    private int likes;
    private int dislikes;
    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(
            name = "Tweet_Tags", // Junction table name
            joinColumns = @JoinColumn(name = "id"), // Foreign key in the junction table to this entity
            inverseJoinColumns = @JoinColumn(name = "Tagid") // Foreign key to the other entity
    )
    private Set<Tags> tags = new HashSet<>();
}
