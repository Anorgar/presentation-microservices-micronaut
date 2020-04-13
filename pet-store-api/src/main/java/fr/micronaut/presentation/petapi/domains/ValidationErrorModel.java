package fr.micronaut.presentation.petapi.domains;

import fr.micronaut.presentation.common.exceptions.ValidationError;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class ValidationErrorModel {

  List<ValidationError> validationErrors;

}
