package am.gitc.spingdemo.service.impl;


import am.gitc.spingdemo.entity.MessageEntity;
import am.gitc.spingdemo.repository.MessageRepository;
import am.gitc.spingdemo.service.MessagesService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessagesServiceImpl implements MessagesService {

  private final MessageRepository messagesRepository;

  public MessagesServiceImpl(MessageRepository messagesRepository) {
    this.messagesRepository = messagesRepository;
  }

  @Override
  public MessageEntity add(MessageEntity message) {
    return this.messagesRepository.save(message);
  }

  @Override
  public List<MessageEntity> getAllMessages(int senderId, int receiverId) {
    return this.messagesRepository.findAllBySenderIdAndReceiverIdOrReceiverIdAndSenderId(senderId, receiverId, senderId, receiverId);
  }
}
