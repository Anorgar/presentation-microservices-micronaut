package fr.micronaut.presentation.petcore.helpers;

import fr.micronaut.presentation.common.domains.Type;
import fr.micronaut.presentation.petcore.domains.TypeEntity;
import java.util.List;
import java.util.stream.Collectors;

public final class TypeMapper {

  private TypeMapper() {}

  public static List<Type> mapEntitiesToDTOs(List<TypeEntity> types) {
    return types.stream()
        .map(TypeMapper::mapEntityToDTO)
        .collect(Collectors.toList());
  }

  public static Type mapEntityToDTO(TypeEntity type) {
    return Type.builder()
        .id(type.getId())
        .type(type.getType())
        .build();
  }

  public static TypeEntity mapDTOToEntity(Type type) {
    return TypeEntity.builder()
        .id(type.getId())
        .type(type.getType())
        .build();
  }

}
