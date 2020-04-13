package fr.micronaut.presentation.petcore.controllers;

import fr.micronaut.presentation.common.domains.Type;
import fr.micronaut.presentation.petcore.services.TypeService;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import java.util.List;
import javax.inject.Inject;

@Controller("/pet/type")
public class TypeController {

  @Inject
  private TypeService service;

  @Operation(summary = "List of every pet types available in the data base")
  @ApiResponse(responseCode = "200", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Type.class))))
  @Get
  public List<Type> findAll(){
    return service.findAll();
  }

  @Operation(summary = "Save a pet type")
  @ApiResponse(responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Type.class)))
  @Post
  public Type save(@Body Type type){
    return service.save(type);
  }

}
