package vincentlow.twittur.tweet.service;

import vincentlow.twittur.tweet.web.model.request.PushNotificationRequest;

public interface KafkaPublisherService {

  void pushNotification(PushNotificationRequest request);
}
