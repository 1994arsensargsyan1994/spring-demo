package am.gitc.spingdemo.service;


import am.gitc.spingdemo.dto.MessageDto;
import am.gitc.spingdemo.entity.MessageEntity;
import am.gitc.spingdemo.exceptions.DatabaseException;

import java.util.List;

public interface MessagesService {

  MessageEntity add(MessageEntity message) throws DatabaseException;

  List<MessageEntity> getAllMessages(int senderId, int receiverId) throws DatabaseException;
}
