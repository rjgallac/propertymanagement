package uk.co.sheffieldprogrammer.tenant.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uk.co.sheffieldprogrammer.tenant.dto.TenantDto;
import uk.co.sheffieldprogrammer.tenant.service.TenantService;

import java.util.List;

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
    public void addTenant(@RequestBody TenantDto tenantDto) {
        tenantService.addTenant(tenantDto);

    }
}
