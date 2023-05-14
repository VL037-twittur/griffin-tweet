package vincentlow.twittur.tweet.model.constant;

public enum ErrorCode {

  REQUEST_MUST_NOT_BE_NULL("request must not be null"),

  // TWEET
  MESSAGE_MUST_NOT_BE_BLANK("message must not be blank"),
  MESSAGE_MAXIMAL_LENGTH_IS_250("message maximal length is 250"),

  // EMAIL
  EMAIL_RECIPIENT_MUST_NOT_BE_BLANK("email recipient must not be blank"),
  EMAIL_SUBJECT_MUST_NOT_BE_BLANK("email recipient must not be blank"),
  EMAIL_BODY_MUST_NOT_BE_BLANK("email body must not be blank");

  private String message;

  ErrorCode(String message) {

    this.message = message;
  }

  public String getMessage() {

    return message;
  }
}
