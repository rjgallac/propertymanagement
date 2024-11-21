package uk.co.sheffieldprogrammer.property.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApartmentDto {

    private Long id;

    private PropertyDto propertyDto;

    private double rent;

    private List<BookingDto> bookingDtos;

}
