package fr.micronaut.presentation.petapi.Controllers;

import fr.micronaut.presentation.common.domains.Type;
import fr.micronaut.presentation.petapi.domains.TypeModel;
import fr.micronaut.presentation.petapi.helper.ValidationExceptionMapper;
import fr.micronaut.presentation.petapi.services.TypeService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Error;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.views.ModelAndView;
import io.micronaut.views.View;
import javax.inject.Inject;
import javax.validation.ConstraintViolationException;

@Controller("/type")
public class PetTypeController {

  @Inject
  private TypeService service;

  @View("type")
  @Get
  public TypeModel createTypeModel(){
    return service.createTypeModel();
  }

  @View("type")
  @Post(consumes = MediaType.APPLICATION_FORM_URLENCODED)
  public TypeModel createTypeModel(@Body Type type){
    return service.addType(type);
  }

  @Error(exception = ConstraintViolationException.class)
  public HttpResponse<ModelAndView> handleValidationError(ConstraintViolationException e){
    return ValidationExceptionMapper.handleValidationError(e);
  }
}
