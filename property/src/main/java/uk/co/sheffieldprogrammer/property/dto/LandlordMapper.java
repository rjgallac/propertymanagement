package uk.co.sheffieldprogrammer.property.dto;

import org.springframework.stereotype.Component;
import uk.co.sheffieldprogrammer.property.model.Landlord;

@Component
public class LandlordMapper {
    public LandlordDto toDto(Landlord landlord) {
        return LandlordDto.builder().id(landlord.getId()).name(landlord.getName()).build();
    }
}
