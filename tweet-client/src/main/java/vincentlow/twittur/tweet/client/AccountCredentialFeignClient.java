package vincentlow.twittur.tweet.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import vincentlow.twittur.base.web.model.response.api.ApiSingleResponse;
import vincentlow.twittur.tweet.client.model.response.AccountCredentialResponse;

@FeignClient(name = "cyclops-account-credential")
public interface AccountCredentialFeignClient {

  @GetMapping("api/v1/acc-cred/@{username}")
  ResponseEntity<ApiSingleResponse<AccountCredentialResponse>> getAccountCredential(@PathVariable String username);
}
