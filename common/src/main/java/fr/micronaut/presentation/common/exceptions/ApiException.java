package fr.micronaut.presentation.common.exceptions;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
public class ApiException extends RuntimeException {

  private int status;
  private String errorMessage;

  public ApiException(Throwable e, String message, int defaultStatus){
    super(e.getMessage());
    log.error("----------------> {}", message, e);
    if (e instanceof ApiException){
      this.status = ((ApiException) e).getStatus();
      this.errorMessage = ((ApiException) e).getErrorMessage();
    } else {
      this.errorMessage = message;
      this.status = defaultStatus;
    }
  }

  public ApiException(String message, int status) {
    super(message);
    this.errorMessage = message;
    this.status = status;
  }
}
