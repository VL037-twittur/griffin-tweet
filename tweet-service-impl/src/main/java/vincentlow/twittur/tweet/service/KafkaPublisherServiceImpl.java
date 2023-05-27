package vincentlow.twittur.tweet.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import vincentlow.twittur.tweet.model.constant.KafkaConstant;
import vincentlow.twittur.tweet.web.model.request.PushNotificationRequest;

@Service
public class KafkaPublisherServiceImpl implements KafkaPublisherService {

  @Autowired
  private KafkaTemplate<String, Object> kafkaTemplate;

  @Override
  public void pushNotification(PushNotificationRequest request) {

    kafkaTemplate.send(KafkaConstant.PUSH_TWEET_NOTIFICATION, request);
  }
}
