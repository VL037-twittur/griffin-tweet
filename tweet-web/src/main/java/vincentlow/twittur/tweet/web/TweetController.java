package vincentlow.twittur.tweet.web;

import static vincentlow.twittur.tweet.util.ObjectMappingHelper.toResponse;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import vincentlow.twittur.base.web.controller.BaseController;
import vincentlow.twittur.base.web.model.response.PageMetaData;
import vincentlow.twittur.base.web.model.response.api.ApiListResponse;
import vincentlow.twittur.base.web.model.response.api.ApiResponse;
import vincentlow.twittur.base.web.model.response.api.ApiSingleResponse;
import vincentlow.twittur.tweet.model.constant.ApiPath;
import vincentlow.twittur.tweet.model.entity.Tweet;
import vincentlow.twittur.tweet.service.TweetService;
import vincentlow.twittur.tweet.util.ValidatorUtil;
import vincentlow.twittur.tweet.web.model.request.CreateTweetRequest;
import vincentlow.twittur.tweet.web.model.request.UpdateTweetRequest;
import vincentlow.twittur.tweet.web.model.response.TweetResponse;

@Slf4j
@RestController
@RequestMapping(value = ApiPath.TWEET, produces = MediaType.APPLICATION_JSON_VALUE)
public class TweetController extends BaseController {

  @Autowired
  private TweetService tweetService;

  @GetMapping
  public ResponseEntity<ApiListResponse<TweetResponse>> getAccountTweets(@PathVariable String username,
      @RequestParam(defaultValue = "0") int pageNumber,
      @RequestParam(defaultValue = "5") int pageSize) {

    try {
      ValidatorUtil.validatePageableRequest(pageNumber, pageSize);

      Page<Tweet> tweets = tweetService.findAccountTweets(username, pageNumber, pageSize);
      List<TweetResponse> response = tweets.stream()
          .map(tweet -> toResponse(tweet, TweetResponse.class))
          .collect(Collectors.toList());
      PageMetaData pageMetaData = getPageMetaData(tweets, pageNumber, pageSize);

      return toSuccessResponseEntity(toApiListResponse(response, pageMetaData));
    } catch (RuntimeException e) {
      log.error(
          "#TweetController#getAccountTweets ERROR! with username: {}, pageNumber: {}, pageSize: {}, and error: {}",
          username, pageNumber, pageSize, e.getMessage(), e);
      throw new RuntimeException(e.getMessage(), e);
    }
  }

  @GetMapping("/{tweetId}")
  public ResponseEntity<ApiSingleResponse<TweetResponse>> getAccountTweetById(@PathVariable String username,
      @PathVariable String tweetId) {

    try {
      Tweet tweet = tweetService.findAccountTweetById(username, tweetId);
      TweetResponse response = toResponse(tweet, TweetResponse.class);

      return toSuccessResponseEntity(toApiSingleResponse(response));
    } catch (RuntimeException e) {
      log.error("#TweetController#getAccountTweetById ERROR! with username: {}, tweetId: {}, and error: {}", username,
          tweetId, e.getMessage(), e);
      throw new RuntimeException(e.getMessage(), e);
    }
  }

  @PostMapping
  public ResponseEntity<ApiSingleResponse<TweetResponse>> postTweet(@PathVariable String username,
      @RequestBody CreateTweetRequest request) {

    try {
      Tweet tweet = tweetService.createTweet(username, request);
      TweetResponse response = toResponse(tweet, TweetResponse.class);

      return toSuccessResponseEntity(toApiSingleResponse(response));
    } catch (RuntimeException e) {
      log.error("#TweetController#postTweet ERROR! with username: {}, request: {}, and error: {}", username, request,
          e.getMessage(), e);
      throw new RuntimeException(e.getMessage(), e);
    }
  }

  @PutMapping("/{tweetId}")
  public ResponseEntity<ApiSingleResponse<TweetResponse>> updateAccountTweet(@PathVariable String username,
      @PathVariable String tweetId,
      @RequestBody UpdateTweetRequest request) {

    try {
      Tweet tweet = tweetService.updateAccountTweet(username, tweetId, request);
      TweetResponse response = toResponse(tweet, TweetResponse.class);

      return toSuccessResponseEntity(toApiSingleResponse(response));
    } catch (RuntimeException e) {
      log.error("#TweetController#updateAccountTweet ERROR! with username: {}, tweetId: {}, request: {}, and error: {}",
          username, tweetId, request, e.getMessage(), e);
      throw new RuntimeException(e.getMessage(), e);
    }
  }

  @DeleteMapping("/{tweetId}")
  public ResponseEntity<ApiResponse> deleteAccountTweet(@PathVariable String username, @PathVariable String tweetId) {

    try {
      tweetService.deleteAccountTweet(username, tweetId);
      return toSuccessResponseEntity(successResponse);
    } catch (RuntimeException e) {
      log.error("#TweetController#deleteAccountTweet ERROR! with username: {}, tweetId: {}, and error: {}", username,
          tweetId, e.getMessage(), e);
      throw new RuntimeException(e.getMessage(), e);
    }
  }
}
