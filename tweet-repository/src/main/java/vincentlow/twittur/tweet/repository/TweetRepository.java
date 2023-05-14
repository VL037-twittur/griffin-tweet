package vincentlow.twittur.tweet.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import vincentlow.twittur.tweet.model.entity.Tweet;

@Repository
public interface TweetRepository extends MongoRepository<Tweet, String> {

  Page<Tweet> findAllByCreatorIdAndMarkForDeleteFalse(String creatorId, Pageable pageable);

  Tweet findByIdAndMarkForDeleteFalse(String tweetId);
}
