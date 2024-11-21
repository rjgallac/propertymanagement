package uk.co.sheffieldprogrammer.property.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uk.co.sheffieldprogrammer.property.dto.PropertyDto;
import uk.co.sheffieldprogrammer.property.service.PropertyService;

import java.util.List;

@RestController
@RequestMapping("property")
public class PropertyController {

    @Autowired
    private PropertyService propertyService;


    @PostMapping
    public void addProperty(@RequestBody PropertyDto propertyDto){
        propertyService.addProperty(propertyDto);
    }

    @GetMapping
    public List<PropertyDto> getProperties(){
        return propertyService.getProperties();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PropertyDto> getProperty(@PathVariable("id") Long id) {
        return ResponseEntity.ok(propertyService.getProperty(id));
    }

    @GetMapping("date")
    public List<PropertyDto> getByDateMaintenance() {
        return null;
    }

}
