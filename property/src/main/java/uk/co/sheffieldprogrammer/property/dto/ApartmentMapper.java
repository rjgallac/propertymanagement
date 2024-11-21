package uk.co.sheffieldprogrammer.property.dto;

import org.springframework.stereotype.Component;
import uk.co.sheffieldprogrammer.property.model.Apartment;

@Component
public class ApartmentMapper {



    public ApartmentDto toDto(Apartment apartment) {
        return ApartmentDto.builder()
                .id(apartment.getId())
                .rent(apartment.getRent())
                .propertyDto(PropertyDto.builder()
                        .id(apartment.getProperty().getId())
                        .address(apartment.getProperty().getAddress())
                        .landlordDto(LandlordDto.builder()
                                .id(apartment.getProperty().getLandlord().getId())
                                .name(apartment.getProperty().getLandlord().getName())
                                .build())
                        .build())
                .build();
    }

}
