package fr.micronaut.presentation.petapi.domains;

import fr.micronaut.presentation.common.domains.Type;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class TypeModel {

  private List<Type> types;
  private Type typeToCreate;

}
