package fr.micronaut.presentation.petcore.controllers;

import fr.micronaut.presentation.common.domains.Pet;
import fr.micronaut.presentation.common.exceptions.ApiError;
import fr.micronaut.presentation.petcore.services.PetService;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.annotation.Post;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import java.util.List;
import javax.inject.Inject;

@Controller("/pets")
public class PetController {

  @Inject
  private PetService service;

  @Operation(summary = "List of every pets available in the data base")
  @ApiResponse(responseCode = "200", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Pet.class))))
  @Get
  public List<Pet> retrievePets(){
    return service.retrievePets();
  }

  @Operation(summary = "Retrieve a pet by name")
  @ApiResponse(responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Pet.class)))
  @ApiResponse(responseCode = "404", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class)))
  @Get("/{name}")
  public Pet findPetByName(@PathVariable("name") String name){
    return service.findByName(name);
  }

  @Operation(summary = "Save a pet")
  @ApiResponse(responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Pet.class)))
  @Post
  public Pet savePet(Pet pet){
    return service.save(pet);
  }

}
