package vincentlow.twittur.tweet.web.model.response.exception;

public class ConflictException extends RuntimeException {

  public ConflictException(String message) {

    super(message);
  }
}
