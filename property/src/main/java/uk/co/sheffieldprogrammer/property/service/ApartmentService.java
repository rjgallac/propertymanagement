package uk.co.sheffieldprogrammer.property.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.co.sheffieldprogrammer.property.dto.ApartmentDto;
import uk.co.sheffieldprogrammer.property.dto.ApartmentMapper;
import uk.co.sheffieldprogrammer.property.exception.ResourceNotFoundException;
import uk.co.sheffieldprogrammer.property.model.Apartment;
import uk.co.sheffieldprogrammer.property.model.Property;
import uk.co.sheffieldprogrammer.property.repository.ApartmentRepository;
import uk.co.sheffieldprogrammer.property.repository.PropertyRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ApartmentService {

    @Autowired
    private ApartmentRepository apartmentRepository;

    @Autowired
    private PropertyRepository propertyRepository;

    @Autowired
    private ApartmentMapper apartmentMapper;

    public void addApartment(ApartmentDto apartmentDto) {
        Optional<Property> property = propertyRepository.findById(apartmentDto.getPropertyDto().getId());
        Apartment apartment = Apartment.builder()
                .property(property.get())
                .rent(apartmentDto.getRent())
                .build();

        apartmentRepository.save(apartment);
    }

    public List<ApartmentDto> getApartments() {
        List<ApartmentDto> apartmentDtos = new ArrayList<>();
        Iterable<Apartment> apartments = apartmentRepository.findAll();
        for (Apartment apartment : apartments) {
            ApartmentDto apartmentDto = apartmentMapper.toDto(apartment);
            apartmentDtos.add(apartmentDto);
        }
        return apartmentDtos;

    }

    public ApartmentDto getApartmentById(Long id) {
        Optional<Apartment> apartment = apartmentRepository.findById(id);
        if(apartment.isPresent()) {
            return apartmentMapper.toDto(apartment.get());
        } else {
            log.info("not found apartment with id {}" , id);
            throw new ResourceNotFoundException("not found");
        }

    }
}
