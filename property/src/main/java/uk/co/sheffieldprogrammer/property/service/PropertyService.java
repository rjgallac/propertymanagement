package uk.co.sheffieldprogrammer.property.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import uk.co.sheffieldprogrammer.property.dto.PropertyDto;
import uk.co.sheffieldprogrammer.property.dto.PropertyMapper;
import uk.co.sheffieldprogrammer.property.model.Property;
import uk.co.sheffieldprogrammer.property.repository.PropertyRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class PropertyService {

    @Autowired
    private PropertyRepository propertyRepository;


    @Autowired
    private PropertyMapper propertyMapper;

    @CacheEvict(value = "properties", allEntries = true)
    public PropertyDto addProperty(PropertyDto propertyDto) {

        Property property = Property.builder()
                .landlord(propertyDto.getLandlord())
                .address(propertyDto.getAddress())
                .build();
        Property saved = propertyRepository.save(property);
        log.info("add property {}", saved.getId());
        return propertyMapper.toDto(saved);
    }

    @Cacheable("properties")
    public List<PropertyDto> getProperties() {
        log.info("getting properties");
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

    @CacheEvict(value = "properties", allEntries = true)
    public void delete(Long id) {
        log.info("delete property {}", id);
        propertyRepository.deleteById(id);
    }
}
