package fr.micronaut.presentation.petcore.controllers;

import fr.micronaut.presentation.common.exceptions.ApiError;
import fr.micronaut.presentation.common.exceptions.ApiException;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import javax.annotation.Nullable;
import org.apache.commons.lang3.StringUtils;

@Controller("/exception")
public class ExceptionTransmissionController {

  @Operation(summary = "Throw exception for test purpose")
  @ApiResponse(responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class)))
  @Get("/{?apiError*}")
  public HttpResponse throwException(@Nullable ApiError apiError){
    String message;
    if (apiError == null || StringUtils.isBlank(apiError.getMessage())){
      message = "Empty error";
    } else {
      message = apiError.getMessage();
    }

    int status;
    if (apiError == null || apiError.getStatus() == 0){
      status = 500;
    } else {
      status = apiError.getStatus();
    }

    throw new ApiException(message, status);
  }

}
