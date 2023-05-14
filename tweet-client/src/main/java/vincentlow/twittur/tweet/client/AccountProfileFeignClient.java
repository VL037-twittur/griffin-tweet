package vincentlow.twittur.tweet.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import vincentlow.twittur.base.web.model.response.api.ApiResponse;

@FeignClient(name = "gargoyle-account-profile")
public interface AccountProfileFeignClient {

  @PostMapping("/api/v1/accounts/@{username}/add-tweet")
  ResponseEntity<ApiResponse> addTweetCount(@PathVariable String username);
}
