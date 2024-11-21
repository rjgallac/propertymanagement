package uk.co.sheffieldprogrammer.tenant.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import uk.co.sheffieldprogrammer.tenant.dto.TenantDto;
import uk.co.sheffieldprogrammer.tenant.model.Tenant;
import uk.co.sheffieldprogrammer.tenant.repository.TenantRepository;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class TenantService {

    @Autowired
    private TenantRepository tenantRepository;

    public void addTenant(TenantDto tenantDto) {
        tenantRepository.save(Tenant.builder()
                .address(tenantDto.getAddress())
                .name(tenantDto.getName())
                .build());
    }

    @Cacheable("tenants")
    public List<TenantDto> getTenants() {
        log.info("Getting tenants");
        List<TenantDto> tenantDtos = new ArrayList<>();
        Iterable<Tenant> all = tenantRepository.findAll();
        for (Tenant tenant : all) {
            TenantDto tenantDto = TenantDto.builder()
                    .id(tenant.getId())
                    .name(tenant.getName())
                    .address(tenant.getAddress())
                    .emailAddress(tenant.getEmailAddress())
                    .build();
            tenantDtos.add(tenantDto);
        }
        return tenantDtos;

    }
}
