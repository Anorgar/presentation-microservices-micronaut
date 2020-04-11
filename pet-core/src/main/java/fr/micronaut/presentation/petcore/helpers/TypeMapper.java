package fr.micronaut.presentation.petcore.helpers;

import fr.micronaut.presentation.petcore.domains.dtos.TypeDTO;
import fr.micronaut.presentation.petcore.domains.entities.Type;
import java.util.List;
import java.util.stream.Collectors;

public final class TypeMapper {

  private TypeMapper() {}

  public static List<TypeDTO> mapEntitiesToDTOs(List<Type> types) {
    return types.stream()
        .map(TypeMapper::mapEntityToDTO)
        .collect(Collectors.toList());
  }

  public static TypeDTO mapEntityToDTO(Type type) {
    return TypeDTO.builder()
        .id(type.getId())
        .type(type.getType())
        .build();
  }

  public static Type mapDTOToEntity(TypeDTO type) {
    return Type.builder()
        .id(type.getId())
        .type(type.getType())
        .build();
  }

}
