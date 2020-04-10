package fr.micronaut.presentation.petcore.repositories;

import fr.micronaut.presentation.petcore.domains.entities.Type;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;

@Repository
public interface TypeRepository extends CrudRepository<Type, Integer> {

}
