package uk.co.sheffieldprogrammer.tenant.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import uk.co.sheffieldprogrammer.tenant.dto.ApartmentDto;
import uk.co.sheffieldprogrammer.tenant.dto.BookingDto;
import uk.co.sheffieldprogrammer.tenant.dto.TenantDto;
import uk.co.sheffieldprogrammer.tenant.model.Booking;
import uk.co.sheffieldprogrammer.tenant.model.BookingMapper;
import uk.co.sheffieldprogrammer.tenant.repository.BookingRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;


    @Autowired
    private PropertyService propertyService;

    @Autowired
    private BookingMapper bookingMapper;

    @Cacheable("bookings")
    public List<BookingDto> getBookings() {
        log.info("getting bookings");
        List<BookingDto> bookingsDto = new ArrayList<>();
        Iterable<Booking> bookings = bookingRepository.findAll();
        for (Booking booking : bookings) {

            ApartmentDto apartmentDto = propertyService.getApartment(booking.getApartmentId());
            BookingDto bookingDto = BookingDto.builder()
                    .id(booking.getId())
//                    .tenantDto(TenantDto.builder().name(booking.getTenant().getName()).build())
                    .apartmentDto(apartmentDto)
                    .build();
            bookingsDto.add(bookingDto);
        }
        return bookingsDto;
    }

    @Cacheable("bookings")
    public List<BookingDto> getBookingsByEmail(String email) {
        log.info("getting bookings");
        List<BookingDto> bookingsDto = new ArrayList<>();
        Iterable<Booking> bookings = bookingRepository.findAllByEmail(email);
        for (Booking booking : bookings) {

            ApartmentDto apartmentDto = propertyService.getApartment(booking.getApartmentId());
            BookingDto bookingDto = BookingDto.builder()
                    .id(booking.getId())
//                    .tenantDto(TenantDto.builder().name(booking.getTenant().getName()).build())
                    .apartmentDto(apartmentDto)
                    .build();
            bookingsDto.add(bookingDto);
        }
        return bookingsDto;
    }

    @Cacheable("booking")
    public BookingDto getBookingByApartmentId(Long id) {
        log.info("getting bookings for apartment {}", id);
        Booking booking = bookingRepository.findByApartmentId(id);

        BookingDto bookingDto = BookingDto.builder()
                .id(booking.getId())

                .build();

        return bookingDto;
    }

    @CacheEvict(value = "bookings", allEntries = true)
    public BookingDto addBooking(BookingDto bookingDto){
        Booking booking = Booking.builder()
                .email(bookingDto.getEmail())
                .apartmentId(bookingDto.getApartmentDto().getId())
                .build();
        ApartmentDto apartmentDto = propertyService.getApartment(booking.getApartmentId());
        Booking saved = bookingRepository.save(booking);
        log.info("add booking {}", saved.getId());
        BookingDto dto = bookingMapper.toDto(saved);
        dto.setApartmentDto(apartmentDto);
        return dto;
    }

    @CacheEvict(value = "bookings", allEntries = true)
    public void deleteBooking(Long id) {
        log.info("delete booking {}",  id);
        bookingRepository.deleteById(id);
    }
}
