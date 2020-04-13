package fr.micronaut.presentation.common.domains;

import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class Type {

  private Integer id;
  @NotBlank(message = "le type doit être renseigné")
  private String type;

}
