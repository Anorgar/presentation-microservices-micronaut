package fr.micronaut.presentation.common.exceptions;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ValidationError implements Serializable {

  private static final long serialVersionUID = 6159937425497090431L;
  private String field;
  private String message;

}
