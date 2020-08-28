package am.gitc.spingdemo.service.impl;

import am.gitc.spingdemo.entity.UserEntity;
import am.gitc.spingdemo.repository.UserRepository;
import am.gitc.spingdemo.repository.redis.UsersRedis;
import am.gitc.spingdemo.service.UsersService;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

@Component
public class UsersServiceImpl implements UsersService {

  private final UserRepository userRepository;

  private final UsersRedis usersRedis;

  private final LoadingCache<Integer, Optional<UserEntity>> usersCache;

  @Autowired
  public UsersServiceImpl(UserRepository userRepository, UsersRedis usersRedis) {
    this.userRepository = userRepository;
    this.usersRedis = usersRedis;
    this.usersCache = CacheBuilder.newBuilder()
        .maximumSize(1000)
        .expireAfterWrite(10, TimeUnit.MINUTES)
        .build(new CacheLoader<Integer, Optional<UserEntity>>() {
          @Override
          public Optional<UserEntity> load(Integer id) throws Exception {
            return UsersServiceImpl.this.userRepository.findById(id);
          }
        });
  }

  @Override
  public UserEntity add(UserEntity user, InputStream imageContent) {

    return this.userRepository.save(user);
  }

  @Override
  public UserEntity add(UserEntity user) {
    return this.userRepository.save(user);
  }

  @Override
  public Optional<UserEntity> get(int id) throws Exception {
    UserEntity userEntity = this.usersRedis.getUser(id + "");
    if (userEntity == null) {
      userEntity = this.userRepository.findById(id).orElse(null);
      if (userEntity != null) {
        this.usersRedis.addUser(id + "", userEntity, 600);
      }
    }
    return Optional.ofNullable(userEntity);
  }

  @Override
  public Optional<UserEntity> get(String email, String password) {
    return this.userRepository.findByEmailAndPassword(email, password);
  }

  @Override
  public boolean userExist(String email) {
    return this.userRepository.existsByEmail(email);
  }

  @Override
  public List<UserEntity> getAll() throws ExecutionException {
    List<UserEntity> userEntities = this.usersRedis.getUsers();
    if (userEntities == null) {
      userEntities = this.userRepository.findAll();
      this.usersRedis.addUsers(userEntities, 600);
    }
    return userEntities;
  }

  private List<UserEntity> getAllFromDb() {
    return this.userRepository.findAll();
  }

  @Override
  public void delete(int id) {
    this.usersRedis.deleteUser(id + "");
    this.userRepository.deleteById(id);
  }
}
