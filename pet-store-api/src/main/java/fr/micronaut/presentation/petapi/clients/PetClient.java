package fr.micronaut.presentation.petapi.clients;

import fr.micronaut.presentation.common.domains.Pet;
import fr.micronaut.presentation.common.domains.Type;
import fr.micronaut.presentation.common.exceptions.ApiError;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.client.annotation.Client;
import java.util.List;
import java.util.Optional;

@Client(value = "${client.urls.pet}", errorType = ApiError.class)
public interface PetClient {

  @Get("/pets")
  List<Pet> findAllPets();

  @Get("/pets/{name}")
  Optional<Pet> findByName(@PathVariable("name") String name);

  @Get("/pet/type")
  List<Type> findAllTypes();

  @Post("/pets")
  Pet save(@Body Pet pet);

  @Post("/pet/type")
  Type saveType(@Body Type type);

}
