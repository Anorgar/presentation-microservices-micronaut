package fr.micronaut.presentation.petcore.exceptions;

import lombok.Getter;

@Getter
public class ApiException extends RuntimeException{

  private int status;
  private String errorMessage;

  public ApiException(Throwable e, String message, int defaultStatus){
    if (e instanceof ApiException){
      this.status = ((ApiException) e).getStatus();
      this.errorMessage = ((ApiException) e).getErrorMessage();
    } else {
      this.errorMessage = message;
      this.status = defaultStatus;
    }
  }

  public ApiException(String message, int status) {
    this.errorMessage = message;
    this.status = status;
  }
}
