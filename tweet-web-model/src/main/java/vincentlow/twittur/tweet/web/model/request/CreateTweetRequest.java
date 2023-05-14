package vincentlow.twittur.tweet.web.model.request;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor // needed when req field = 1
@AllArgsConstructor // used in mapper.readValue()
public class CreateTweetRequest implements Serializable {

  private static final long serialVersionUID = 8405947830930746613L;

  private String message;
}
