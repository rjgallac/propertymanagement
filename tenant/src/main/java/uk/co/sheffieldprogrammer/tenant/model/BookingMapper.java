package uk.co.sheffieldprogrammer.tenant.model;

import org.springframework.stereotype.Component;
import uk.co.sheffieldprogrammer.tenant.dto.ApartmentDto;
import uk.co.sheffieldprogrammer.tenant.dto.BookingDto;
import uk.co.sheffieldprogrammer.tenant.dto.TenantDto;

@Component
public class BookingMapper {

    public BookingDto toDto(Booking booking) {
        return BookingDto.builder()
                .id(booking.getId())
//                .apartmentDto(ApartmentDto.builder().id(booking.getTenant().getId()).build())
//                .tenantDto(TenantDto.builder().id(booking.getTenant().getId()).name(booking.getTenant().getName()).build())
                .build();
    }
}
