package uk.co.sheffieldprogrammer.tenant.repository;

import org.springframework.data.repository.CrudRepository;
import uk.co.sheffieldprogrammer.tenant.model.Booking;

import java.util.List;

public interface BookingRepository extends CrudRepository<Booking, Long> {
    Booking findByApartmentId(Long apartmentId);

    List<Booking> findAllByEmail(String email);
}
