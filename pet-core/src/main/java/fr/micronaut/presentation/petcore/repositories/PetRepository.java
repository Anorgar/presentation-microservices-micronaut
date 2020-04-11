package fr.micronaut.presentation.petcore.repositories;

import fr.micronaut.presentation.petcore.domains.PetEntity;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;
import java.util.Optional;

@Repository
public interface PetRepository extends CrudRepository<PetEntity, Integer> {

  Optional<PetEntity> findByName(String name);

}
