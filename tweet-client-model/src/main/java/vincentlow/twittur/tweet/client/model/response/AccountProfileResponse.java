package vincentlow.twittur.tweet.client.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountProfileResponse {

  private String id;

  private String username;
}
