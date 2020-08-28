package am.gitc.spingdemo.repository;

import am.gitc.spingdemo.entity.MessageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<MessageEntity, Integer> {
  List<MessageEntity> findAllBySenderIdAndReceiverIdOrReceiverIdAndSenderId(int firstSenderId, int firstReceiverId, int secondReceiverId, int secondSenderId);
}
