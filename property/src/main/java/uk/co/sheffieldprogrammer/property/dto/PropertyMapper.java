package uk.co.sheffieldprogrammer.property.dto;

import org.springframework.stereotype.Component;
import uk.co.sheffieldprogrammer.property.model.Apartment;
import uk.co.sheffieldprogrammer.property.model.Property;

import java.util.ArrayList;
import java.util.List;

@Component
public class PropertyMapper {

    public PropertyDto toDto(Property property) {
        List<ApartmentDto> apartmentDtos = new ArrayList<>();
        if(property.getApartments() != null) {
            for (Apartment apartment : property.getApartments()) {
                apartmentDtos.add(ApartmentDto.builder().id(apartment.getId()).rent(apartment.getRent()).build());
            }
        }

        return PropertyDto.builder()
                .id(property.getId())
                .landlordDto(LandlordDto.builder()
                        .id(property.getLandlord().getId())
                        .name(property.getLandlord().getName())
                        .build())
                .address(property.getAddress())
                .apartmentDtos(apartmentDtos)
                .build();
    }
}
