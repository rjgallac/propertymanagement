package uk.co.sheffieldprogrammer.tenant.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TenantDto {

    private Long id;

    private String name;

    private String address;

    private String emailAddress;
}
