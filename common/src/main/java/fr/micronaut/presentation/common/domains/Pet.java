package fr.micronaut.presentation.common.domains;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Pet {

  private Integer id;
  private String name;
  private Integer stock;
  private Double price;
  private Type type;

}
