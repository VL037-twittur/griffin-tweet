package vincentlow.twittur.tweet.model.constant;

public interface ApiPath {

  String BASE_PATH = "/api/v1";

  String ACCOUNT = BASE_PATH + "/accounts";

  String TWEET = ACCOUNT + "/@{username}/tweets";
}
