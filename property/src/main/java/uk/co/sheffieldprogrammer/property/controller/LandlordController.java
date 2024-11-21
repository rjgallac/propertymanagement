package uk.co.sheffieldprogrammer.property.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uk.co.sheffieldprogrammer.property.dto.LandlordDto;
import uk.co.sheffieldprogrammer.property.service.LandlordService;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/landlord")
public class LandlordController {

    @Autowired
    private LandlordService landlordService;

    @PostMapping
    public LandlordDto addLandlord(@RequestBody LandlordDto landlordDto){
        return landlordService.addLandlord(landlordDto);
    }

    @GetMapping
    public List<LandlordDto> getLandlords() {

        return landlordService.getLandlords();
    }

    @DeleteMapping("/{id}")
    public void deleteLandlord(@PathVariable("id") Long id) {
        landlordService.deleteLandlord(id);
    }

}
