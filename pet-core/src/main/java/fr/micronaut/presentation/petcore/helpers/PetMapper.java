package fr.micronaut.presentation.petcore.helpers;

import fr.micronaut.presentation.common.domains.Pet;
import fr.micronaut.presentation.petcore.domains.PetEntity;
import java.util.List;
import java.util.stream.Collectors;

public final class PetMapper {

  private PetMapper(){}

  public static List<Pet> mapEntitiesToDTOs(List<PetEntity> pets){
    return pets.stream()
        .map(PetMapper::mapEntityToDTO)
        .collect(Collectors.toList());
  }

  public static Pet mapEntityToDTO(PetEntity pet){
    return Pet.builder()
        .id(pet.getId())
        .name(pet.getName())
        .stock(pet.getStock())
        .price(pet.getPrice())
        .type(TypeMapper.mapEntityToDTO(pet.getType()))
        .build();
  }

  public static PetEntity mapDTOToEntity(Pet pet){
    return PetEntity.builder()
        .id(pet.getId())
        .name(pet.getName())
        .stock(pet.getStock())
        .price(pet.getPrice())
        .type(TypeMapper.mapDTOToEntity(pet.getType()))
        .build();
  }

}
