package am.gitc.spingdemo.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@Entity(name = "messages")
public class MessageEntity implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private int id;

  @Column(name = "sender_id")
  private int senderId;

  @Column(name = "receiver_id")
  private int receiverId;

  private String message;

  @Column(name = "created_at")
  private Date createdAt;

}
