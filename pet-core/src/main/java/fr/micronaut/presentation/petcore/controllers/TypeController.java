package fr.micronaut.presentation.petcore.controllers;

import fr.micronaut.presentation.petcore.domains.dtos.TypeDTO;
import fr.micronaut.presentation.petcore.services.TypeService;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import java.util.List;
import javax.inject.Inject;

@Controller("/pet/type")
public class TypeController {

  @Inject
  private TypeService service;

  @Get
  public List<TypeDTO> findAll(){
    return service.findAll();
  }

  @Post
  public TypeDTO save(@Body TypeDTO type){
    return service.save(type);
  }

}
