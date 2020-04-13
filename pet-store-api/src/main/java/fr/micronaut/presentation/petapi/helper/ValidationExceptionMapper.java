package fr.micronaut.presentation.petapi.helper;

import fr.micronaut.presentation.common.exceptions.ValidationError;
import fr.micronaut.presentation.petapi.domains.ValidationErrorModel;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.MutableHttpResponse;
import io.micronaut.views.ModelAndView;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

public final class ValidationExceptionMapper {

  private ValidationExceptionMapper(){}

  public static HttpResponse<ModelAndView> handleValidationError(ConstraintViolationException e){
    Set<ConstraintViolation<?>> constraintViolations = e.getConstraintViolations();

    Function<String, String> splitter = s -> Arrays.stream(s.split("\\."))
        .reduce((first, second) -> second)
        .orElse("");

    final List<ValidationError> errorDetails = constraintViolations
        .stream()
        .map(v -> new ValidationError(
            splitter.apply(v.getPropertyPath().toString()), v.getMessage())
        ).collect(Collectors.toList());

    MutableHttpResponse<ModelAndView> response = HttpResponse.status(HttpStatus.BAD_REQUEST);
    response.body(new ModelAndView<>("validation",
        ValidationErrorModel.builder().validationErrors(errorDetails).build()));
    return response;
  }

}
