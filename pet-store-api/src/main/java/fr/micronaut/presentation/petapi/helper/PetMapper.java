package fr.micronaut.presentation.petapi.helper;

import fr.micronaut.presentation.common.domains.Pet;
import fr.micronaut.presentation.common.domains.Type;
import fr.micronaut.presentation.petapi.domains.PetFlat;

public final class PetMapper {

  private PetMapper(){}

  public static Pet mapPetFlatToPet(PetFlat pet){
    return Pet.builder()
        .name(pet.getName())
        .number(pet.getNumber())
        .price(pet.getPrice())
        .type(Type.builder().id(pet.getTypeId()).type(pet.getType()).build())
        .build();
  }
}
