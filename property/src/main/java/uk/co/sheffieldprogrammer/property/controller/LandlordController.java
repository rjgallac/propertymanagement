package uk.co.sheffieldprogrammer.property.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uk.co.sheffieldprogrammer.property.dto.LandlordDto;
import uk.co.sheffieldprogrammer.property.service.LandlordService;

import java.util.List;

@RestController
@RequestMapping("/landlord")
public class LandlordController {

    @Autowired
    private LandlordService landlordService;

    @PostMapping
    public void addLandlord(@RequestBody LandlordDto landlordDto){
        landlordService.addLandlord(landlordDto);
    }

    @GetMapping
    public List<LandlordDto> getLandlords() {
        return landlordService.getLandlords();
    }

}
