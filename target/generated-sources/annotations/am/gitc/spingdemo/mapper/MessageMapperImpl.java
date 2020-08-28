package am.gitc.spingdemo.mapper;

import am.gitc.spingdemo.dto.MessageDto;
import am.gitc.spingdemo.entity.MessageEntity;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-08-10T17:21:46+0400",
    comments = "version: 1.2.0.Final, compiler: javac, environment: Java 1.8.0_265 (Private Build)"
)
@Component
public class MessageMapperImpl implements MessageMapper {

    @Override
    public MessageDto toDto(MessageEntity entity) {
        if ( entity == null ) {
            return null;
        }

        MessageDto messageDto = new MessageDto();

        messageDto.setId( entity.getId() );
        messageDto.setSenderId( entity.getSenderId() );
        messageDto.setReceiverId( entity.getReceiverId() );
        messageDto.setMessage( entity.getMessage() );
        messageDto.setCreatedAt( entity.getCreatedAt() );

        return messageDto;
    }

    @Override
    public MessageEntity toEntity(MessageDto dto) {
        if ( dto == null ) {
            return null;
        }

        MessageEntity messageEntity = new MessageEntity();

        messageEntity.setId( dto.getId() );
        messageEntity.setSenderId( dto.getSenderId() );
        messageEntity.setReceiverId( dto.getReceiverId() );
        messageEntity.setMessage( dto.getMessage() );
        messageEntity.setCreatedAt( dto.getCreatedAt() );

        return messageEntity;
    }

    @Override
    public List<MessageDto> toDtoList(List<MessageEntity> entity) {
        if ( entity == null ) {
            return null;
        }

        List<MessageDto> list = new ArrayList<MessageDto>( entity.size() );
        for ( MessageEntity messageEntity : entity ) {
            list.add( toDto( messageEntity ) );
        }

        return list;
    }

    @Override
    public List<MessageEntity> toEntityList(List<MessageDto> dto) {
        if ( dto == null ) {
            return null;
        }

        List<MessageEntity> list = new ArrayList<MessageEntity>( dto.size() );
        for ( MessageDto messageDto : dto ) {
            list.add( toEntity( messageDto ) );
        }

        return list;
    }
}
