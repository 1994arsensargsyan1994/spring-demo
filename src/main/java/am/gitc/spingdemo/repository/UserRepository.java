package am.gitc.spingdemo.repository;

import am.gitc.spingdemo.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {

  Optional<UserEntity> findByEmailAndPassword(String email, String password);

  boolean existsByEmail(String email);

}
