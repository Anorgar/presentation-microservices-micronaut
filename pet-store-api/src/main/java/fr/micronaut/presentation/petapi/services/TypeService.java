package fr.micronaut.presentation.petapi.services;

import fr.micronaut.presentation.common.domains.Type;
import fr.micronaut.presentation.petapi.clients.PetClient;
import fr.micronaut.presentation.petapi.domains.TypeModel;
import javax.inject.Inject;
import javax.inject.Singleton;
import javax.validation.Valid;

@Singleton
public class TypeService {

  @Inject
  private PetClient client;

  public TypeModel createTypeModel(){
    return TypeModel.builder()
        .types(client.findAllTypes())
        .typeToCreate(Type.builder().build())
        .build();
  }

  public TypeModel addType(@Valid Type type){
    client.saveType(type);
    return createTypeModel();
  }

}
