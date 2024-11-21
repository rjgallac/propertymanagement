package uk.co.sheffieldprogrammer.email.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@NoArgsConstructor
public class PropertyDto {

    private Long id;

    private String address;

    private LandlordDto landlordDto;

    private List<ApartmentDto> apartmentDtos;

    private List<MaintenanceDto> maintenanceDtos;
}
