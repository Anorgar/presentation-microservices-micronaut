package fr.micronaut.presentation.petapi.services;

import static fr.micronaut.presentation.petapi.helper.PetMapper.mapPetFlatToPet;

import fr.micronaut.presentation.common.domains.Pet;
import fr.micronaut.presentation.petapi.clients.PetClient;
import fr.micronaut.presentation.petapi.domains.PetFlat;
import fr.micronaut.presentation.petapi.domains.PetModel;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class PetService {

  @Inject
  private PetClient client;

  public PetModel createPetModel(){
    return PetModel.builder()
        .pets(client.findAllPets())
        .types(client.findAllTypes())
        .petToCreate(Pet.builder().build())
        .build();
  }

  public PetModel addPet(PetFlat pet){
    client.save(mapPetFlatToPet(pet));
    return createPetModel();
  }


}
