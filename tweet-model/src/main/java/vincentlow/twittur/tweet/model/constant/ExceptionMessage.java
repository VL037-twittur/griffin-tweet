package vincentlow.twittur.tweet.model.constant;

public interface ExceptionMessage {

  String PAGE_NUMBER_MUST_BE_AT_LEAST_0 = "page number must be at least 0";

  String PAGE_SIZE_MUST_BE_BETWEEN_1_AND_100 = "page size must be between 1 and 100";

  String SERVICE_TEMPORARILY_UNAVAILABLE = "service temporarily unavailable";

  String ACCOUNT_NOT_FOUND = "account not found";

  String TWEET_NOT_FOUND = "tweet not found";
}
