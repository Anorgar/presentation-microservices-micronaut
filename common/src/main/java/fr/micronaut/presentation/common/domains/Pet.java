package fr.micronaut.presentation.common.domains;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class Pet {

  private Integer id;
  private String name;
  private Integer number;
  private Double price;
  private Type type;

}
