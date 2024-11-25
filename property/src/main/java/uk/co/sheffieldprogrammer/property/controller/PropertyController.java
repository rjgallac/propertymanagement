package uk.co.sheffieldprogrammer.property.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import uk.co.sheffieldprogrammer.property.dto.PropertyDto;
import uk.co.sheffieldprogrammer.property.service.PropertyService;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("property")
public class PropertyController {

    @Autowired
    private PropertyService propertyService;


    @PostMapping
    public ResponseEntity<PropertyDto> addProperty(@RequestBody PropertyDto propertyDto,
                                                   @AuthenticationPrincipal(expression = "username") String username){
        System.out.println(username);
        propertyDto.setLandlord(username);
        return ResponseEntity.ok(propertyService.addProperty(propertyDto));
    }

    @GetMapping
    public ResponseEntity<List<PropertyDto>> getProperties(@AuthenticationPrincipal(expression = "username") String username){
        return ResponseEntity.ok(propertyService.getProperties());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PropertyDto> getProperty(@PathVariable("id") Long id) {
        return ResponseEntity.ok(propertyService.getProperty(id));
    }

    @GetMapping("date")
    public List<PropertyDto> getByDateMaintenance() {
        return null;
    }

    @DeleteMapping("/{id}")
    public void deleteProperty(@PathVariable("id") Long id) {
        propertyService.delete(id);
    }

}
