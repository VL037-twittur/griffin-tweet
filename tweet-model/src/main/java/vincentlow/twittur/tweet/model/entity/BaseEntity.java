package vincentlow.twittur.tweet.model.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseEntity implements Serializable {

  private static final long serialVersionUID = -6153245778238063521L;

  @Id
  private String id;

  @Field("created_by")
  private String createdBy;

  @Field("created_date")
  private LocalDateTime createdDate;

  @Field("updated_by")
  private String updatedBy;

  @Field("updated_date")
  private LocalDateTime updatedDate;

  @Field("mark_for_delete")
  private boolean markForDelete;
}
