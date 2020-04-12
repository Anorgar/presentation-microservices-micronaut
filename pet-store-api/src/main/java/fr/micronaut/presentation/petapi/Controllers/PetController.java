package fr.micronaut.presentation.petapi.Controllers;

import fr.micronaut.presentation.common.domains.Pet;
import fr.micronaut.presentation.petapi.domains.PetFlat;
import fr.micronaut.presentation.petapi.domains.PetModel;
import fr.micronaut.presentation.petapi.services.PetService;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.views.View;
import javax.inject.Inject;

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

}
