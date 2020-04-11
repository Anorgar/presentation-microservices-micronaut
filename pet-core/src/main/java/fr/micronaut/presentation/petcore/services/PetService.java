package fr.micronaut.presentation.petcore.services;

import fr.micronaut.presentation.common.domains.Pet;
import fr.micronaut.presentation.common.exceptions.ApiException;
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

  public List<Pet> retrievePets(){
    return Try.of(() -> repository.findAll())
        .map(pets -> IteratorUtils.toList(pets.iterator()))
        .map(PetMapper::mapEntitiesToDTOs)
        .getOrElseThrow(e -> new ApiException(e, "Unable to retrieve pets", 500));
  }

  public Pet findByName(String name){
    return Try.of(() -> repository.findByName(name)
        .map(PetMapper::mapEntityToDTO)
        .orElseThrow(() -> new ApiException("Pet " + name + " not found", 404)))
        .getOrElseThrow(e -> new ApiException(e, "Unable to retrieve pet " + name, 500));
  }

  public Pet save(Pet pet){
    return Try.of(() -> repository.save(PetMapper.mapDTOToEntity(pet)))
        .map(PetMapper::mapEntityToDTO)
        .getOrElseThrow(e -> new ApiException(e, "Unable to save pet", 500));
  }

}
