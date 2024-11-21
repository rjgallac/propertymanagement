package uk.co.sheffieldprogrammer.tenant.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import uk.co.sheffieldprogrammer.tenant.dto.ApartmentDto;
import uk.co.sheffieldprogrammer.tenant.dto.BookingDto;
import uk.co.sheffieldprogrammer.tenant.dto.TenantDto;
import uk.co.sheffieldprogrammer.tenant.model.Booking;
import uk.co.sheffieldprogrammer.tenant.model.Tenant;
import uk.co.sheffieldprogrammer.tenant.repository.BookingRepository;
import uk.co.sheffieldprogrammer.tenant.repository.TenantRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private TenantRepository tenantRepository;

    @Autowired
    private PropertyService propertyService;

    public List<BookingDto> getBookings() {
        List<BookingDto> bookingsDto = new ArrayList<>();
        Iterable<Booking> bookings = bookingRepository.findAll();
        for (Booking booking : bookings) {

            ApartmentDto apartmentDto = propertyService.getApartment(booking.getApartmentId());
            BookingDto.builder()
                    .tenantDto(TenantDto.builder().name(booking.getTenant().getName()).build())
                    .apartmentDto(apartmentDto)
                    .build();
        }
        return bookingsDto;
    }

    @Cacheable("booking")
    public BookingDto getBookingByApartmentId(Long id) {
        log.info("getting bookings for apartment {}", id);
        Booking booking = bookingRepository.findByApartmentId(id);

        BookingDto bookingDto = BookingDto.builder()
                .id(booking.getId())
                .tenantDto(TenantDto.builder()
                        .id(booking.getTenant().getId())
                        .name(booking.getTenant().getName())
                        .address(booking.getTenant().getAddress())
                        .build())
                .build();

        return bookingDto;
    }


    public void addBooking(BookingDto bookingDto){
        Tenant tenant = tenantRepository.findById(bookingDto.getTenantDto().getId()).get();
        Booking booking = Booking.builder()
                .tenant(tenant)
                .apartmentId(bookingDto.getApartmentDto().getId())
                .build();
        bookingRepository.save(booking);
    }

}
