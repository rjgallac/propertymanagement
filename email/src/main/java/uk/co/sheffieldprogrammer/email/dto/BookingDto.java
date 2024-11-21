package uk.co.sheffieldprogrammer.email.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;


@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BookingDto {

    private Long id;

    private TenantDto tenantDto;

    private ApartmentDto apartmentDto;
}
