package am.gitc.spingdemo.controller.users;

import am.gitc.spingdemo.aspect.Loggable;
import am.gitc.spingdemo.entity.UserEntity;
import am.gitc.spingdemo.mapper.UserMapper;
import am.gitc.spingdemo.service.Users;
import am.gitc.spingdemo.service.UsersService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
public class UserController {

  private final UsersService usersService;
  private final Users users;
  private final UserMapper userMapper;
  private static Logger logger = LoggerFactory.getLogger(UserController.class);

  private static final String EXCEL_MIME_TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
  private static final String CSV_MIME_TYPE = "text/csv";

  @Autowired
  public UserController(UsersService usersService, Users users,UserMapper userMapper) {
    this.usersService = usersService;
    this.users = users;
    this.userMapper = userMapper;
  }

  @Loggable
  @GetMapping("/api/users")
  public ResponseEntity<List<UserEntity>> getAllUsers() throws ExecutionException {




    return ResponseEntity.ok(usersService.getAll());
  }

  @Loggable
  @GetMapping("/api/users/{userId}")
  public ResponseEntity<UserEntity> getUser(@PathVariable("userId") Integer userId) throws Exception {
    return ResponseEntity.ok(this.usersService.get(userId).orElse(new UserEntity()));
  }

  @GetMapping("/api/users/delete/{userId}")
  public ResponseEntity<?> deleteUser(@PathVariable("userId") Integer userId) throws Exception {
    this.usersService.delete(userId);
    return ResponseEntity.ok().build();
  }
//
//  @GetMapping(value = "/api/users/excel/_download", produces = EXCEL_MIME_TYPE)
//  public ResponseEntity<Resource> downloadUsersAsExcel() {
//    try {
//      List<UserDto> userDtos = this.userMapper.toDtoList(this.usersService.getAll());
//      InputStream in = this.users.createExcel(userDtos);
//      return ResponseEntity.ok()
//          .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + "all_users.xlsx" + "\"")
//          .body(new InputStreamResource(in).an);
//    } catch (Exception e) {
//      logger.error("Error", e);
//      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//    }
//  }
//
//  @GetMapping(value = "/api/users/csv/_download", produces = CSV_MIME_TYPE)
//  public ResponseEntity<Resource> downloadUsersAsCsv() {
//    try {
//      List<UserDto> userDtos = this.userMapper.toDtoList(this.usersService.getAll());
//      InputStream in = this.users.createCsvFile(userDtos);
//      return ResponseEntity.ok()
//          .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + "all_users.csv" + "\"")
//          .body(new InputStreamResource(in));
//    } catch (Exception e) {
//      logger.error("Error", e);
//      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//    }
//  }
//
//  @GetMapping("/api/users/{userId}")
//  public ResponseEntity<UserDto> getUserById(@PathVariable("userId") Integer userId) {
//    Optional<UserEntity> userEntity = this.usersService.get(userId);
//    if (!userEntity.isPresent()) {
//      logger.warn("Users with id = {} not found.", userId);
//      return ResponseEntity.notFound().build();
//    }
//    return ResponseEntity.ok(this.userMapper.toDto(userEntity.get()));
//  }
//
//  @GetMapping("/api/users/_search")
//  public ResponseEntity<UserDto> getUserByEmailAndPassword(@PathParam("email") String email,
//                                                           @PathParam("password") String password) {
//    try {
//      Optional<UserEntity> userEntity = this.usersService.get(email, password);
//      if (!userEntity.isPresent()) {
//        return ResponseEntity.notFound().build();
//      }
//      return ResponseEntity.ok(this.userMapper.toDto(userEntity.get()));
//    } catch (Exception e) {
//      logger.error("ERROR", e);
//    }
//    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//  }
//
//  @DeleteMapping("/api/users/{userId}")
//  public ResponseEntity deleteUserById(@PathVariable("userId") Integer userId) {
//    this.usersService.delete(userId);
//    return ResponseEntity.ok().build();
//  }
//
//  @PostMapping("/api/users")
//  public ResponseEntity<UserDto> addUser(@RequestBody UserDto userDto) {
//    UserEntity userEntity = this.userMapper.toEntity(userDto);
//    userEntity = this.usersService.add(userEntity);
//    return ResponseEntity.ok(this.userMapper.toDto(userEntity));
//  }
//
//  @PutMapping("/api/users/{userId}")
//  public ResponseEntity<UserDto> addUser(@PathVariable("userId") Integer userId,
//                                         @RequestBody UserDto userDto) {
//    UserEntity userEntity = this.userMapper.toEntity(userDto);
//    userEntity.setId(userId);
//    userEntity = this.usersService.add(userEntity);
//    return ResponseEntity.ok(this.userMapper.toDto(userEntity));
//  }
}
