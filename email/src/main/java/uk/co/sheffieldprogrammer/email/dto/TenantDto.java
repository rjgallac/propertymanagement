package uk.co.sheffieldprogrammer.email.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TenantDto {

    private Long id;

    private String name;

    private String address;

    private String emailAddress;
}
