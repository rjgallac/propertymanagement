package uk.co.sheffieldprogrammer.tenant.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import uk.co.sheffieldprogrammer.tenant.dto.TenantDto;
import uk.co.sheffieldprogrammer.tenant.model.Tenant;
import uk.co.sheffieldprogrammer.tenant.model.TenantMapper;
import uk.co.sheffieldprogrammer.tenant.repository.TenantRepository;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class TenantService {

    @Autowired
    private TenantRepository tenantRepository;

    @Autowired
    private TenantMapper tenantMapper;

    @CacheEvict(value = "tenants", allEntries = true)
    public TenantDto addTenant(TenantDto tenantDto) {
        Tenant saved = tenantRepository.save(Tenant.builder()
                .address(tenantDto.getAddress())
                .emailAddress(tenantDto.getEmailAddress())
                .name(tenantDto.getName())
                .build());
        log.info("Adding tenant {}", saved.getId());
        return tenantMapper.toDto(saved);
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

    @CacheEvict(value = "tenants", allEntries = true)
    public void deleteTenant(Long id) {
        log.info("Deleting tenant {}", id);
        tenantRepository.deleteById(id);
    }
}
