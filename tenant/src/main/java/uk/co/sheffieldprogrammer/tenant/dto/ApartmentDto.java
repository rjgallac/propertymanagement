package uk.co.sheffieldprogrammer.tenant.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ApartmentDto {

    private Long id;

    private PropertyDto propertyDto;
}
