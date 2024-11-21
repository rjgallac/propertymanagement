package uk.co.sheffieldprogrammer.tenant.repository;

import org.springframework.data.repository.CrudRepository;
import uk.co.sheffieldprogrammer.tenant.model.Tenant;

public interface TenantRepository extends CrudRepository<Tenant, Long> {
}
