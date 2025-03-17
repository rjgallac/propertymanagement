package uk.co.sheffieldprogrammer.property.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import uk.co.sheffieldprogrammer.property.dto.ApartmentDto;
import uk.co.sheffieldprogrammer.property.service.ApartmentService;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("apartment")
public class ApartmentController {

    @Autowired
    private ApartmentService apartmentService;

    @PreAuthorize("hasRole('LANDLORD')")
    @PostMapping
    public ResponseEntity<ApartmentDto> addApartment(@RequestBody ApartmentDto apartmentDto) {

        return ResponseEntity.ok(apartmentService.addApartment(apartmentDto));
    }


    @PreAuthorize("hasRole('USER')")
    @GetMapping
    public List<ApartmentDto> getApartments() throws InterruptedException {
        Thread.sleep(5000L);

        return apartmentService.getApartments();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApartmentDto> getApartmentById(@PathVariable("id") Long id) throws InterruptedException {
        return ResponseEntity.ok(apartmentService.getApartmentById(id));
    }

    @DeleteMapping("{id}")
    public void deleteApartment(@PathVariable("id") Long id) {
        apartmentService.deleteApartment(id);
    }

}
