package uk.co.sheffieldprogrammer.tenant.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uk.co.sheffieldprogrammer.tenant.dto.TenantDto;
import uk.co.sheffieldprogrammer.tenant.service.TenantService;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("tenant")
public class Tenant {


    @Autowired
    private TenantService tenantService;

    @GetMapping
    public List<TenantDto> getTenants() {
        return tenantService.getTenants();
    }


    @PostMapping
    public ResponseEntity<TenantDto> addTenant(@RequestBody TenantDto tenantDto) {
        return ResponseEntity.ok(tenantService.addTenant(tenantDto));
    }

    @DeleteMapping("/{id}")
    public void deleteTenant(@PathVariable("id") Long id){
        tenantService.deleteTenant(id);
    }
}
