package uk.co.sheffieldprogrammer.property.repository;

import org.springframework.data.repository.CrudRepository;
import uk.co.sheffieldprogrammer.property.model.Property;

public interface PropertyRepository extends CrudRepository<Property, Long> {
}
