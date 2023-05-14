package vincentlow.twittur.tweet.service;

import org.springframework.data.domain.Page;

import vincentlow.twittur.tweet.model.entity.Tweet;
import vincentlow.twittur.tweet.web.model.request.CreateTweetRequest;
import vincentlow.twittur.tweet.web.model.request.UpdateTweetRequest;

public interface TweetService {

  Page<Tweet> findAccountTweets(String username, int pageNumber, int pageSize);

  Tweet findAccountTweetById(String username, String tweetId);

  Tweet createTweet(String username, CreateTweetRequest request);

  Tweet updateAccountTweet(String username, String tweetId, UpdateTweetRequest request);

  void deleteAccountTweet(String username, String tweetId);
}
