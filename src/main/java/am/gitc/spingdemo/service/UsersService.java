package am.gitc.spingdemo.service;

import am.gitc.spingdemo.entity.UserEntity;

import java.io.InputStream;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

public interface UsersService {

  UserEntity add(UserEntity user, InputStream imageContent);

  UserEntity add(UserEntity user);

  Optional<UserEntity> get(int id) throws Exception;

  Optional<UserEntity> get(String email, String password);

  boolean userExist(String email);

  List<UserEntity> getAll() throws ExecutionException;

  void delete(int id);
}
