package fr.micronaut.presentation.petapi.clients;

import fr.micronaut.presentation.common.exceptions.ApiError;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.client.annotation.Client;

@Client(value = "${client.urls.pet}", errorType = ApiError.class)
public interface ExceptionTransmissionClient {

  @Get("/exception/{?apiError*}")
  public HttpResponse getException(ApiError apiError);

}
