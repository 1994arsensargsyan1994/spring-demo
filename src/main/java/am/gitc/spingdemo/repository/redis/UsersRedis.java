package am.gitc.spingdemo.repository.redis;

import am.gitc.spingdemo.entity.UserEntity;
import am.gitc.spingdemo.util.JsonUtil;
import am.gitc.spingdemo.util.Validator;
import com.fasterxml.jackson.core.type.TypeReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.List;

@Repository
public class UsersRedis {

  private final JedisPool jedisPool;
  private static Logger logger = LoggerFactory.getLogger(UsersRedis.class);

  @Autowired
  public UsersRedis(JedisPool jedisPool) {
    this.jedisPool = jedisPool;
  }

  public void addUser(String key, UserEntity value, int ttlSeconds) {
    try (Jedis redis = jedisPool.getResource()) {
      String jsonUserValue = JsonUtil.serialize(value);
      redis.set(key, jsonUserValue);
      redis.expire(key, ttlSeconds);
    } catch (Exception e) {
      logger.error("Error", e);
    }
  }

  public UserEntity getUser(String key) {
    try (Jedis redis = jedisPool.getResource()) {
      String userInfoJson = redis.get(key);
      if (!Validator.isEmpty(userInfoJson)) {
        return JsonUtil.deserialize(userInfoJson, new TypeReference<UserEntity>() {
        });
      }
    } catch (Exception e) {
      logger.error("Error", e);
    }
    return null;
  }

  public void deleteUser(String key) {
    try (Jedis redis = jedisPool.getResource()) {
      redis.del(key);
    } catch (Exception e) {
      logger.error("Error", e);
    }
  }

  public void addUsers(List<UserEntity> value, int ttlSeconds) {
    try (Jedis redis = jedisPool.getResource()) {
      String jsonUserValue = JsonUtil.serialize(value);
      redis.set("all", jsonUserValue);
      redis.expire("all", ttlSeconds);
    } catch (Exception e) {
      logger.error("Error", e);
    }
  }

  public List<UserEntity> getUsers() {
    try (Jedis redis = jedisPool.getResource()) {
      String usersStr = redis.get("all");
      if (!Validator.isEmpty(usersStr)) {
        return JsonUtil.deserialize(usersStr, new TypeReference<List<UserEntity>>() {
        });
      }
    } catch (Exception e) {
      logger.error("Error", e);
    }
    return null;
  }
}
