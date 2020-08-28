package am.gitc.spingdemo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class MessageDto {

  private int id;

  @JsonProperty("sender_id")
  private int senderId;

  @JsonProperty("receiver_id")
  private int receiverId;

  private String message;

  @JsonProperty("created_at")
  private Date createdAt;
}
