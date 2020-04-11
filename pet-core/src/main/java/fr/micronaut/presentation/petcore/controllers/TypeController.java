package fr.micronaut.presentation.petcore.controllers;

import fr.micronaut.presentation.common.domains.Type;
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
  public List<Type> findAll(){
    return service.findAll();
  }

  @Post
  public Type save(@Body Type type){
    return service.save(type);
  }

}
