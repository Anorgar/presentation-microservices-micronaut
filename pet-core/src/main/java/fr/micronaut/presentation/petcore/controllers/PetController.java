package fr.micronaut.presentation.petcore.controllers;

import fr.micronaut.presentation.petcore.domains.dtos.PetDTO;
import fr.micronaut.presentation.petcore.services.PetService;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import java.util.List;
import javax.inject.Inject;

@Controller("/pet")
public class PetController {

  @Inject
  private PetService service;

  @Get
  public List<PetDTO> retrievePets(){
    return service.gretrievePets();
  }

}
