package fr.micronaut.presentation.petcore.repositories;

import fr.micronaut.presentation.petcore.domains.TypeEntity;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;

@Repository
public interface TypeRepository extends CrudRepository<TypeEntity, Integer> {

}
