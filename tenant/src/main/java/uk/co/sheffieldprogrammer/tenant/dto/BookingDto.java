package uk.co.sheffieldprogrammer.tenant.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BookingDto {

    private Long id;

    private String email;

    private ApartmentDto apartmentDto;
}
