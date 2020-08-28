package am.gitc.spingdemo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class UserDto {

  private Integer id;

  private String name;

  private String surname;

  private String email;

  private String password;

  @JsonProperty("image_url")
  private String imageUrl;
}
