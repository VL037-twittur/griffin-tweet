package vincentlow.twittur.tweet.model.entity;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.Data;

@Data
@Document(collection = "tweet")
public class Tweet extends BaseEntity {

  private static final long serialVersionUID = -2430156128463750199L;

  @Field(name = "message")
  private String message;

  @Field(name = "creator_id")
  private String creatorId;
}
