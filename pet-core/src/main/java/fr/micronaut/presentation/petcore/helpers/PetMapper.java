package fr.micronaut.presentation.petcore.helpers;

import fr.micronaut.presentation.petcore.domains.dtos.PetDTO;
import fr.micronaut.presentation.petcore.domains.entities.Pet;
import java.util.List;
import java.util.stream.Collectors;

public final class PetMapper {

  private PetMapper(){}

  public static List<PetDTO> mapEntitiesToDTOs(List<Pet> pets){
    return pets.stream()
        .map(PetMapper::mapEntityToDTO)
        .collect(Collectors.toList());
  }

  public static PetDTO mapEntityToDTO(Pet pet){
    return PetDTO.builder()
        .id(pet.getId())
        .name(pet.getName())
        .number(pet.getNumber())
        .price(pet.getPrice())
        .type(TypeMapper.mapEntityToDTO(pet.getType()))
        .build();
  }

  public static Pet mapDTOToEntity(PetDTO pet){
    return Pet.builder()
        .id(pet.getId())
        .name(pet.getName())
        .number(pet.getNumber())
        .price(pet.getPrice())
        .type(TypeMapper.mapDTOToEntity(pet.getType()))
        .build();
  }

}
