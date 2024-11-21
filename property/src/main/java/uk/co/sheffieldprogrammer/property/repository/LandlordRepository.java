package uk.co.sheffieldprogrammer.property.repository;

import org.springframework.data.repository.CrudRepository;
import uk.co.sheffieldprogrammer.property.model.Landlord;

public interface LandlordRepository extends CrudRepository<Landlord, Long> {
}
