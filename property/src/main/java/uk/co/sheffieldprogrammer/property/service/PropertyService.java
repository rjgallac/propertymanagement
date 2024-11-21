package uk.co.sheffieldprogrammer.property.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.co.sheffieldprogrammer.property.dto.PropertyDto;
import uk.co.sheffieldprogrammer.property.dto.PropertyMapper;
import uk.co.sheffieldprogrammer.property.model.Landlord;
import uk.co.sheffieldprogrammer.property.model.Property;
import uk.co.sheffieldprogrammer.property.repository.LandlordRepository;
import uk.co.sheffieldprogrammer.property.repository.PropertyRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class PropertyService {

    @Autowired
    private PropertyRepository propertyRepository;

    @Autowired
    private LandlordRepository landlordRepository;

    @Autowired
    private PropertyMapper propertyMapper;

    public PropertyDto addProperty(PropertyDto propertyDto) {
        Landlord landlord = landlordRepository.findById(propertyDto.getLandlordDto().getId()).get();

        Property property = Property.builder()
                .landlord(landlord)
                .address(propertyDto.getAddress())
                .build();
        Property saved = propertyRepository.save(property);
        return propertyMapper.toDto(saved);
    }

    public List<PropertyDto> getProperties() {
        List<PropertyDto> propertyDtos = new ArrayList<>();

        Iterable<Property> all = propertyRepository.findAll();
        for (Property property : all) {
            propertyDtos.add(propertyMapper.toDto(property));
        }
        return propertyDtos;
    }

    public PropertyDto getProperty(Long id) {
        return propertyMapper.toDto(propertyRepository.findById(id).get());
    }

    public void delete(Long id) {
        propertyRepository.deleteById(id);
    }
}
