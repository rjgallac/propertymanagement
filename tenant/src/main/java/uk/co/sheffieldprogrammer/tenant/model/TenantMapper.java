package uk.co.sheffieldprogrammer.tenant.model;

import org.springframework.stereotype.Component;
import uk.co.sheffieldprogrammer.tenant.dto.TenantDto;

@Component
public class TenantMapper {
    public TenantDto toDto(Tenant tenant) {
        return TenantDto.builder()
                .id(tenant.getId())
                .name(tenant.getName())
                .address(tenant.getAddress())
                .emailAddress(tenant.getEmailAddress())
                .build();
    }
}
