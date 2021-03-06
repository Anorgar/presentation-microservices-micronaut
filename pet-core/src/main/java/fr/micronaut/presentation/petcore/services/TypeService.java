package fr.micronaut.presentation.petcore.services;

import fr.micronaut.presentation.common.domains.Type;
import fr.micronaut.presentation.common.exceptions.ApiException;
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

  public List<Type> findAll(){
    return Try.of(() -> repository.findAll())
        .map(types -> IteratorUtils.toList(types.iterator()))
        .map(TypeMapper::mapEntitiesToDTOs)
        .getOrElseThrow(e -> new ApiException(e, "Unable to find pet types", 500));
  }

  public Type save(Type type){
    return Try.of(() -> repository.save(TypeMapper.mapDTOToEntity(type)))
        .map(TypeMapper::mapEntityToDTO)
        .getOrElseThrow(e -> new ApiException(e, "Unable to save pet", 500));
  }

}
