package uk.co.sheffieldprogrammer.tenant.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uk.co.sheffieldprogrammer.tenant.dto.BookingDto;
import uk.co.sheffieldprogrammer.tenant.service.BookingService;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("booking")
public class Booking {

    @Autowired
    private BookingService bookingService;

    @PostMapping()
    void addBooking(@RequestBody BookingDto bookingDto){
        bookingService.addBooking(bookingDto);
    }


    @GetMapping()
    List<BookingDto> getBookings() {
        return bookingService.getBookings();
    }

    @GetMapping("/apartment/{id}")
    BookingDto getBookingsByApartmentId(@PathVariable("id") Long id) {
        return bookingService.getBookingByApartmentId(id);
    }

    @DeleteMapping("{id}")
    public void deleteBooking(@PathVariable("id") Long id) {
        bookingService.deleteBooking(id);
    }

}
