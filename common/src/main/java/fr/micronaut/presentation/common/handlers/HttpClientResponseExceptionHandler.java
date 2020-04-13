package fr.micronaut.presentation.common.handlers;

import fr.micronaut.presentation.common.exceptions.ApiError;
import fr.micronaut.presentation.common.exceptions.ApiException;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MutableHttpResponse;
import io.micronaut.http.annotation.Produces;
import io.micronaut.http.client.FullNettyClientHttpResponse;
import io.micronaut.http.client.exceptions.HttpClientResponseException;
import io.micronaut.http.server.exceptions.ExceptionHandler;
import javax.inject.Singleton;

import java.util.Optional;

/**
 * Catch every {@link HttpClientResponseException} and convert them into a {@link HttpResponse<ApiError>}.
 * If the exception body is instance of {@link ApiError}, rethrow the same {@link ApiError}.
 */
@Produces
@Singleton
public class HttpClientResponseExceptionHandler implements ExceptionHandler<HttpClientResponseException, HttpResponse> {

  @Override
  public HttpResponse<ApiError> handle(HttpRequest request, HttpClientResponseException exception) {
    FullNettyClientHttpResponse<?> clientResponse = (FullNettyClientHttpResponse<?>) exception.getResponse();
    MutableHttpResponse<ApiError> response = HttpResponse.status(clientResponse.getStatus());
    Optional<ApiError> body = clientResponse.getBody(ApiError.class);
    if (body.isPresent()) {
      response.body(body.get());
    } else {
      response.body(ApiError.builder()
              .message(clientResponse.getStatus().getReason()).build());
    }
    return response;
  }
}