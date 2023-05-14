package vincentlow.twittur.tweet.util;

import java.util.Objects;

import vincentlow.twittur.tweet.client.model.response.AccountCredentialResponse;
import vincentlow.twittur.tweet.model.constant.ExceptionMessage;
import vincentlow.twittur.tweet.model.entity.Tweet;
import vincentlow.twittur.tweet.web.model.response.exception.BadRequestException;
import vincentlow.twittur.tweet.web.model.response.exception.NotFoundException;

public class ValidatorUtil {

  public static void validateArgument(boolean expression, String errorMessage) {

    if (!expression) {
      throw new BadRequestException(errorMessage);
    }
  }

  public static void validateState(boolean expression, String errorMessage) {

    if (!expression) {
      throw new BadRequestException(errorMessage);
    }
  }

  public static void validatePageableRequest(int pageNumber, int pageSize) {

    if (pageNumber < 0) {
      throw new BadRequestException(ExceptionMessage.PAGE_NUMBER_MUST_BE_AT_LEAST_0);
    } else if (pageSize < 1 || pageSize > 100) {
      throw new BadRequestException(ExceptionMessage.PAGE_SIZE_MUST_BE_BETWEEN_1_AND_100);
    }
  }

  public static AccountCredentialResponse validateAccount(AccountCredentialResponse account, String errorMessage) {

    if (Objects.isNull(account)) {
      throw new NotFoundException(errorMessage);
    }
    return account;
  }

  public static Tweet validateTweet(Tweet tweet, String errorMessage) {

    if (Objects.isNull(tweet)) {
      throw new NotFoundException(errorMessage);
    }
    return tweet;
  }
}
