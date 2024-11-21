package uk.co.sheffieldprogrammer.property.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uk.co.sheffieldprogrammer.property.dto.MaintenanceDto;
import uk.co.sheffieldprogrammer.property.model.Maintenance;
import uk.co.sheffieldprogrammer.property.service.MaintenanceService;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("maintenance")
public class MaintenanceController {

    @Autowired
    private MaintenanceService maintenanceService;

    @PostMapping
    public void addMaintenance(@RequestBody MaintenanceDto maintenanceDto){
        maintenanceService.addMaintenance(maintenanceDto);
    }

    @GetMapping("/date/{date}")
    public List<MaintenanceDto> getByDate(@PathVariable("date") LocalDateTime maintenanceDate){
        return maintenanceService.getByDate(maintenanceDate);
    }
}
