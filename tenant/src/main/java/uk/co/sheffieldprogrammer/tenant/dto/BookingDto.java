package uk.co.sheffieldprogrammer.tenant.dto;

import lombok.Builder;
import lombok.Data;
import uk.co.sheffieldprogrammer.tenant.model.Tenant;

@Data
@Builder
public class BookingDto {

    private Long id;

    private TenantDto tenantDto;

    private ApartmentDto apartmentDto;
}
