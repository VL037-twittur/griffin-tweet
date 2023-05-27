package vincentlow.twittur.tweet.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import vincentlow.twittur.base.web.model.response.api.ApiResponse;
import vincentlow.twittur.base.web.model.response.api.ApiSingleResponse;
import vincentlow.twittur.tweet.client.model.response.AccountProfileResponse;

@FeignClient(name = "gargoyle-account-profile")
public interface AccountProfileFeignClient {

  @GetMapping("/api/v1/accounts/@{username}")
  ResponseEntity<ApiSingleResponse<AccountProfileResponse>> getAccountByUsername(@PathVariable String username);

  @PostMapping("/api/v1/accounts/@{username}/add-tweet")
  ResponseEntity<ApiResponse> addTweetCount(@PathVariable String username);

  @PostMapping("/api/v1/accounts/@{username}/subtract-tweet")
  ResponseEntity<ApiResponse> subtractTweetCount(@PathVariable String username);
}
