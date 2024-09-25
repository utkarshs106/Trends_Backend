package org.example.trends_backend.TweetService.Reository;

import org.example.trends_backend.TweetService.Model.Tags;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagsRepository extends JpaRepository<Tags, Long> {
    @Override
    void deleteAll();
}
