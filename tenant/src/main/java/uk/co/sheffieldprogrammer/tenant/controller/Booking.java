package uk.co.sheffieldprogrammer.tenant.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import uk.co.sheffieldprogrammer.tenant.dto.BookingDto;
import uk.co.sheffieldprogrammer.tenant.service.BookingService;

import java.security.Principal;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("booking")
public class Booking {

    @Autowired
    private BookingService bookingService;

    @PostMapping()
    public ResponseEntity<BookingDto> addBooking(@RequestBody BookingDto bookingDto){
        return ResponseEntity.ok(bookingService.addBooking(bookingDto));
    }


//    @GetMapping()
//    public ResponseEntity<List<BookingDto>> getBookings() {
//        return ResponseEntity.ok(bookingService.getBookings());
//    }

    @GetMapping()
    public ResponseEntity<List<BookingDto>> getBookingsByEmail(@AuthenticationPrincipal(expression = "username") String username) {
        System.out.println(username);
        return ResponseEntity.ok(bookingService.getBookingsByEmail(username));
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
