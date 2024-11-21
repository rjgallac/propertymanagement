package uk.co.sheffieldprogrammer.property.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import uk.co.sheffieldprogrammer.property.model.Maintenance;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface MaintenanceRepository extends CrudRepository<Maintenance, Long> {

    public List<Maintenance> findByMaintenanceDate(LocalDateTime maintenanceDate);
}
