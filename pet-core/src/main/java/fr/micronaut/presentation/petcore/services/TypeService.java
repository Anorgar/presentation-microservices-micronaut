package fr.micronaut.presentation.petcore.services;

import fr.micronaut.presentation.petcore.domains.dtos.TypeDTO;
import fr.micronaut.presentation.petcore.exceptions.ApiException;
import fr.micronaut.presentation.petcore.helpers.TypeMapper;
import fr.micronaut.presentation.petcore.repositories.TypeRepository;
import io.vavr.control.Try;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;
import org.apache.commons.collections.IteratorUtils;

@Singleton
public class TypeService {

  @Inject
  private TypeRepository repository;

  public List<TypeDTO> findAll(){
    return Try.of(() -> repository.findAll())
        .map(types -> IteratorUtils.toList(types.iterator()))
        .map(TypeMapper::mapEntitiesToDTOs)
        .getOrElseThrow(e -> new ApiException(e, "Unable to find pet types", 500));
  }

  public TypeDTO save(TypeDTO type){
    return Try.of(() -> repository.save(TypeMapper.mapDTOToEntity(type)))
        .map(TypeMapper::mapEntityToDTO)
        .getOrElseThrow(e -> new ApiException(e, "Unable to save pet", 500));
  }

}
