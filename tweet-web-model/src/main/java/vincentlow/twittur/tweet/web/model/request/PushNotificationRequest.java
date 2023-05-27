package vincentlow.twittur.tweet.web.model.request;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import vincentlow.twittur.tweet.model.constant.NotificationType;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PushNotificationRequest implements Serializable {

  private static final long serialVersionUID = 1169632168157740210L;

  private String senderId;

  private String title;

  private String message;

  private String imageUrl;

  private NotificationType type;

  private String redirectUrl;
}
