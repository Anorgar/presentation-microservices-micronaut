package fr.micronaut.presentation.petapi.domains;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
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
  @NotBlank(message = "Le nom doit être renseigné")
  private String name;
  @NotNull
  @Positive
  @Max(100)
  private Integer stock;
  @NotNull
  @Positive
  @Max(999)
  private Double price;
  @NotNull
  private Integer typeId;
  private String type;

}
