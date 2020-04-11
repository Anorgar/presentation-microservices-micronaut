package fr.micronaut.presentation.petapi.Controllers;

import fr.micronaut.presentation.common.domains.Pet;
import fr.micronaut.presentation.petapi.clients.PetClient;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.views.View;
import java.util.List;
import javax.inject.Inject;

@Controller("/pet")
public class PerController {

  @Inject
  private PetClient client;

  @View("pet")
  @Get
  public List<Pet> findAll(){
    return client.findAll();
  }

}
