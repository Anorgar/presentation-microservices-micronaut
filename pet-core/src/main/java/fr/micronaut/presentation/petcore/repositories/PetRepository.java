package fr.micronaut.presentation.petcore.repositories;

import fr.micronaut.presentation.petcore.domains.entities.Pet;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;

@Repository
public interface PetRepository extends CrudRepository<Pet, Integer> {

}
