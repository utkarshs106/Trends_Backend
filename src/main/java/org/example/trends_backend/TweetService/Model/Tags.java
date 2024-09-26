package org.example.trends_backend.TweetService.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Entity
public class Tags {
    @Id
    private int Tagid;
    @Column(unique = true)
    private String name;
    @ManyToMany(mappedBy = "tags",fetch = FetchType.EAGER)
    private Set<Tweet> tweet = new HashSet<>();
}
