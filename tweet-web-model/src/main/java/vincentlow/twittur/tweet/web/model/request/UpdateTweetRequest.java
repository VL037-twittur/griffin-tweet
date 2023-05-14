package vincentlow.twittur.tweet.web.model.request;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor // needed when req field = 1
@AllArgsConstructor
public class UpdateTweetRequest implements Serializable {

  private static final long serialVersionUID = -8016577099657351825L;

  private String message;
}
