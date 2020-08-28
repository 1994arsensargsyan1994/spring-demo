package am.gitc.spingdemo.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * ControllerAdvice class for controlling the exceptions which happened in other components.
 */
@Slf4j
@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {


  @ExceptionHandler(value = Throwable.class)
  public ResponseEntity<String> handleThrowable(Throwable ex) {
    log.error("ERROR:", ex);
    return new ResponseEntity<String>("sdf", HttpStatus.INTERNAL_SERVER_ERROR);
  }

}
