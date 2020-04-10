package fr.micronaut.presentation.petcore.services;

import fr.micronaut.presentation.petcore.domains.dtos.PetDTO;
import fr.micronaut.presentation.petcore.exceptions.ApiException;
import fr.micronaut.presentation.petcore.helpers.PetMapper;
import fr.micronaut.presentation.petcore.repositories.PetRepository;
import io.vavr.control.Try;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;
import org.apache.commons.collections.IteratorUtils;

@Singleton
public class PetService {

  @Inject
  private PetRepository repository;

  public List<PetDTO> gretrievePets(){
    return Try.of(() -> repository.findAll())
        .map(pets -> IteratorUtils.toList(pets.iterator()))
        .map(PetMapper::mapEntitiesToDTOs)
        .getOrElseThrow(e -> new ApiException("Unable to retrive pets", 500));
  }

}
