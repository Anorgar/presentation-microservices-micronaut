package fr.micronaut.presentation.petcore.controllers;

import fr.micronaut.presentation.common.domains.Pet;
import fr.micronaut.presentation.petcore.services.PetService;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.annotation.Post;
import java.util.List;
import javax.inject.Inject;

@Controller("/pets")
public class PetController {

  @Inject
  private PetService service;

  @Get
  public List<Pet> retrievePets(){
    return service.retrievePets();
  }

  @Get("/{name}")
  public Pet findPetByName(@PathVariable("name") String name){
    return service.findByName(name);
  }

  @Post
  public Pet savePet(@Body Pet pet){
    return service.save(pet);
  }

}
