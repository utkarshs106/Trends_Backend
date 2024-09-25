package org.example.trends_backend.TweetService.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
public class Tags {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int Tagid;
    private String name;
    @ManyToMany(mappedBy = "tags") // Inverse side of the relationship
    private Set<Tweet> tweet = new HashSet<>();
}
