package vincentlow.twittur.tweet.web.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import vincentlow.twittur.base.web.model.response.BaseResponse;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TweetResponse extends BaseResponse {

  private static final long serialVersionUID = 5815082635966746259L;

  private String message;
}
