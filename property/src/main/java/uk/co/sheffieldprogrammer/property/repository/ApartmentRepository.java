package uk.co.sheffieldprogrammer.property.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import uk.co.sheffieldprogrammer.property.model.Apartment;

@Repository
public interface ApartmentRepository extends CrudRepository<Apartment, Long> {
}
