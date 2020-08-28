package am.gitc.spingdemo.mapper;

import am.gitc.spingdemo.dto.UserDto;
import am.gitc.spingdemo.entity.UserEntity;

@org.mapstruct.Mapper(componentModel = "spring")
public interface UserMapper extends Mapper<UserEntity, UserDto> {
}
