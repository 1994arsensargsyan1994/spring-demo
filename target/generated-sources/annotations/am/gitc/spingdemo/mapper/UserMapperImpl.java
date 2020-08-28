package am.gitc.spingdemo.mapper;

import am.gitc.spingdemo.dto.UserDto;
import am.gitc.spingdemo.entity.UserEntity;
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
public class UserMapperImpl implements UserMapper {

    @Override
    public UserDto toDto(UserEntity entity) {
        if ( entity == null ) {
            return null;
        }

        UserDto userDto = new UserDto();

        userDto.setId( entity.getId() );
        userDto.setName( entity.getName() );
        userDto.setSurname( entity.getSurname() );
        userDto.setEmail( entity.getEmail() );
        userDto.setPassword( entity.getPassword() );

        return userDto;
    }

    @Override
    public UserEntity toEntity(UserDto dto) {
        if ( dto == null ) {
            return null;
        }

        UserEntity userEntity = new UserEntity();

        userEntity.setId( dto.getId() );
        userEntity.setName( dto.getName() );
        userEntity.setSurname( dto.getSurname() );
        userEntity.setEmail( dto.getEmail() );
        userEntity.setPassword( dto.getPassword() );

        return userEntity;
    }

    @Override
    public List<UserDto> toDtoList(List<UserEntity> entity) {
        if ( entity == null ) {
            return null;
        }

        List<UserDto> list = new ArrayList<UserDto>( entity.size() );
        for ( UserEntity userEntity : entity ) {
            list.add( toDto( userEntity ) );
        }

        return list;
    }

    @Override
    public List<UserEntity> toEntityList(List<UserDto> dto) {
        if ( dto == null ) {
            return null;
        }

        List<UserEntity> list = new ArrayList<UserEntity>( dto.size() );
        for ( UserDto userDto : dto ) {
            list.add( toEntity( userDto ) );
        }

        return list;
    }
}
