package vincentlow.twittur.tweet.service;

import static vincentlow.twittur.tweet.util.ValidatorUtil.validateAccount;
import static vincentlow.twittur.tweet.util.ValidatorUtil.validateArgument;
import static vincentlow.twittur.tweet.util.ValidatorUtil.validateState;
import static vincentlow.twittur.tweet.util.ValidatorUtil.validateTweet;

import java.time.LocalDateTime;
import java.util.Objects;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import io.micrometer.common.util.StringUtils;
import vincentlow.twittur.base.web.model.response.api.ApiSingleResponse;
import vincentlow.twittur.tweet.client.AccountCredentialFeignClient;
import vincentlow.twittur.tweet.client.AccountProfileFeignClient;
import vincentlow.twittur.tweet.client.model.response.AccountCredentialResponse;
import vincentlow.twittur.tweet.model.constant.ErrorCode;
import vincentlow.twittur.tweet.model.constant.ExceptionMessage;
import vincentlow.twittur.tweet.model.entity.Tweet;
import vincentlow.twittur.tweet.repository.TweetRepository;
import vincentlow.twittur.tweet.web.model.request.CreateTweetRequest;
import vincentlow.twittur.tweet.web.model.request.UpdateTweetRequest;

@Service
public class TweetServiceImpl implements TweetService {

  @Autowired
  private AccountCredentialFeignClient accountCredentialFeignClient;

  @Autowired
  private TweetRepository tweetRepository;

  @Autowired
  private AccountProfileFeignClient accountProfileFeignClient;

  @Override
  public Page<Tweet> findAccountTweets(String username, int pageNumber, int pageSize) {

    ResponseEntity<ApiSingleResponse<AccountCredentialResponse>> accountResponse =
        accountCredentialFeignClient.getAccountCredential(username);
    AccountCredentialResponse account = accountResponse.getBody()
        .getData();

    validateAccount(account, ExceptionMessage.ACCOUNT_NOT_FOUND);

    return tweetRepository.findAllByCreatorIdAndMarkForDeleteFalse(account.getId(), PageRequest.of(pageNumber, pageSize));
  }

  @Override
  public Tweet findAccountTweetById(String username, String tweetId) {

    ResponseEntity<ApiSingleResponse<AccountCredentialResponse>> accountResponse =
        accountCredentialFeignClient.getAccountCredential(username);
    AccountCredentialResponse account = accountResponse.getBody()
        .getData();

    validateAccount(account, ExceptionMessage.ACCOUNT_NOT_FOUND);

    return validateTweet(tweetRepository.findByIdAndMarkForDeleteFalse(tweetId), ExceptionMessage.TWEET_NOT_FOUND);
  }

  @Override
  public Tweet createTweet(String username, CreateTweetRequest request) {

    validateState(Objects.nonNull(request), ErrorCode.REQUEST_MUST_NOT_BE_NULL.getMessage());
    validateArgument(StringUtils.isNotBlank(request.getMessage()),
        ErrorCode.MESSAGE_MUST_NOT_BE_BLANK.getMessage());
    validateArgument(request.getMessage()
        .length() <= 250, ErrorCode.MESSAGE_MAXIMAL_LENGTH_IS_250.getMessage());

    ResponseEntity<ApiSingleResponse<AccountCredentialResponse>> accountResponse =
        accountCredentialFeignClient.getAccountCredential(username);
    AccountCredentialResponse creator = accountResponse.getBody()
        .getData();

    validateAccount(creator, ExceptionMessage.ACCOUNT_NOT_FOUND);

    Tweet tweet = new Tweet();
    BeanUtils.copyProperties(request, tweet);

    LocalDateTime now = LocalDateTime.now();
    tweet.setCreatorId(creator.getId());
    tweet.setCreatedBy(creator.getId());
    tweet.setCreatedDate(now);
    tweet.setUpdatedBy(creator.getId());
    tweet.setUpdatedDate(now);

    accountProfileFeignClient.addTweetCount(creator.getUsername());

    return tweetRepository.save(tweet);
  }

  @Override
  public Tweet updateAccountTweet(String username, String tweetId, UpdateTweetRequest request) {

    validateState(Objects.nonNull(request), ErrorCode.REQUEST_MUST_NOT_BE_NULL.getMessage());
    validateArgument(StringUtils.isNotBlank(request.getMessage()),
        ErrorCode.MESSAGE_MUST_NOT_BE_BLANK.getMessage());
    validateArgument(request.getMessage()
        .length() <= 250, ErrorCode.MESSAGE_MAXIMAL_LENGTH_IS_250.getMessage());

    ResponseEntity<ApiSingleResponse<AccountCredentialResponse>> accountResponse =
        accountCredentialFeignClient.getAccountCredential(username);
    AccountCredentialResponse creator = accountResponse.getBody()
        .getData();

    validateAccount(creator, ExceptionMessage.ACCOUNT_NOT_FOUND);

    Tweet tweet = tweetRepository.findByIdAndMarkForDeleteFalse(tweetId);
    validateTweet(tweet, ExceptionMessage.TWEET_NOT_FOUND);

    BeanUtils.copyProperties(request, tweet);
    tweet.setUpdatedBy(creator.getId());

    return tweetRepository.save(tweet);
  }

  @Override
  public void deleteAccountTweet(String username, String tweetId) {

    ResponseEntity<ApiSingleResponse<AccountCredentialResponse>> accountResponse =
        accountCredentialFeignClient.getAccountCredential(username);
    AccountCredentialResponse creator = accountResponse.getBody()
        .getData();

    validateAccount(creator, ExceptionMessage.ACCOUNT_NOT_FOUND);

    Tweet tweet = tweetRepository.findByIdAndMarkForDeleteFalse(tweetId);
    validateTweet(tweet, ExceptionMessage.TWEET_NOT_FOUND);

    tweet.setMarkForDelete(true);
    tweet.setUpdatedBy(creator.getId());
    tweetRepository.save(tweet);
  }
}
