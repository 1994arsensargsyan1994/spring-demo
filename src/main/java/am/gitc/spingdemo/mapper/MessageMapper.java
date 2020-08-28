package am.gitc.spingdemo.mapper;

import am.gitc.spingdemo.dto.MessageDto;
import am.gitc.spingdemo.entity.MessageEntity;

@org.mapstruct.Mapper(componentModel = "spring")
public interface MessageMapper extends Mapper<MessageEntity, MessageDto> {
}
