package fr.micronaut.presentation.petapi.Controllers;

import fr.micronaut.presentation.common.exceptions.ApiError;
import fr.micronaut.presentation.petapi.clients.ExceptionTransmissionClient;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import javax.annotation.Nullable;
import javax.inject.Inject;

@Controller("/exception")
public class ExceptionTransmissionController {

  @Inject
  private ExceptionTransmissionClient client;

  @Get("/{?apiError*}")
  public HttpResponse throwException(@Nullable ApiError apiError){
    return client.getException(apiError);
  }

}
