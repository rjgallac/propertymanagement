package uk.co.sheffieldprogrammer.property.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    @PostMapping
    public ResponseEntity<ApartmentDto> addApartment(@RequestBody ApartmentDto apartmentDto) {

        return ResponseEntity.ok(apartmentService.addApartment(apartmentDto));
    }

    @GetMapping
    public List<ApartmentDto> getApartments() {

        return apartmentService.getApartments();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApartmentDto> getApartmentById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(apartmentService.getApartmentById(id));
    }

    @DeleteMapping("{id}")
    public void deleteApartment(@PathVariable("id") Long id) {
        apartmentService.deleteApartment(id);
    }

}
