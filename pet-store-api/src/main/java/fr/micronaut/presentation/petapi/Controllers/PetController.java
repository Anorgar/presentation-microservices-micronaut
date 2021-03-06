package fr.micronaut.presentation.petapi.Controllers;

import fr.micronaut.presentation.common.domains.Pet;
import fr.micronaut.presentation.petapi.domains.PetFlat;
import fr.micronaut.presentation.petapi.domains.PetModel;
import fr.micronaut.presentation.petapi.helper.ValidationExceptionMapper;
import fr.micronaut.presentation.petapi.services.PetService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Error;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.annotation.Post;
import io.micronaut.views.ModelAndView;
import io.micronaut.views.View;
import javax.inject.Inject;
import javax.validation.ConstraintViolationException;

@Controller("/pet")
public class PetController {

  @Inject
  private PetService service;

  @View("pet")
  @Get
  public PetModel createPetModel(){
    return service.createPetModel();
  }

  @View("pet")
  @Post(consumes = MediaType.APPLICATION_FORM_URLENCODED)
  public PetModel createPetModel(@Body PetFlat petToCreate){
    return service.addPet(petToCreate);
  }

  @Get("/{name}")
  public Pet findByName(@PathVariable("name") String name){
    return service.findByName(name);
  }

  @Error(exception = ConstraintViolationException.class)
  public HttpResponse<ModelAndView> handleValidationError(ConstraintViolationException e){
    return ValidationExceptionMapper.handleValidationError(e);
  }

}
