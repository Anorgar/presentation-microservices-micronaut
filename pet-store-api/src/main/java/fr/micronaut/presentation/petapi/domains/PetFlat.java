package fr.micronaut.presentation.petapi.domains;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class PetFlat {

  private Integer id;
  private String name;
  private Integer number;
  private Double price;
  private Integer typeId;
  private String type;

}
