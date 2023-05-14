package vincentlow.twittur.tweet.web.model.response.exception;

public class ServiceUnavailableException extends RuntimeException {

  public ServiceUnavailableException(String message) {

    super(message);
  }
}
