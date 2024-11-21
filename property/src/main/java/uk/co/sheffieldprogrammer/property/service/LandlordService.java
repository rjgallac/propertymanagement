package uk.co.sheffieldprogrammer.property.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.co.sheffieldprogrammer.property.dto.LandlordDto;
import uk.co.sheffieldprogrammer.property.dto.LandlordMapper;
import uk.co.sheffieldprogrammer.property.model.Landlord;
import uk.co.sheffieldprogrammer.property.repository.LandlordRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class LandlordService {

    @Autowired
    private LandlordRepository landlordRepository;

    @Autowired
    private LandlordMapper landlordMapper;

    public LandlordDto addLandlord(LandlordDto landlordDto) {
        Landlord landlord = Landlord.builder().name(landlordDto.getName()).build();
        Landlord saved = landlordRepository.save(landlord);
        return landlordMapper.toDto(saved);
    }

    public List<LandlordDto> getLandlords() {
        List<LandlordDto> landlordDtos = new ArrayList<>();
        Iterable<Landlord> landlords = landlordRepository.findAll();
        for (Landlord landlord : landlords) {
            LandlordDto landlordDto = LandlordDto
                    .builder()
                    .id(landlord.getId())
                    .name(landlord.getName())

                    .build();
            landlordDtos.add(landlordDto);
        }
        return landlordDtos;

    }


    public void deleteLandlord(Long id) {
        landlordRepository.deleteById(id);
    }
}
