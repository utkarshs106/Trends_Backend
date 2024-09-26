package org.example.trends_backend.TweetService.Reository;

import org.example.trends_backend.TweetService.Model.Tags;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TagsRepository extends JpaRepository<Tags, Long> {
    @Override
    void deleteAll();
    Tags findByName(String name);

   /* @Modifying
    @Query(value = """
        insert into tweet_tags(id,tagid) values(:tweet,:tag)""",nativeQuery = true)
    void insertIntotweet_tags(@Param("tweet") int tweet, @Param("tag") int tag);
*/
  //  Tags save(Tags tags);
    List<Tags> findAllByName(String name);
}
