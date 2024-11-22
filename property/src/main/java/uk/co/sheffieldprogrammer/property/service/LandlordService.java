package uk.co.sheffieldprogrammer.property.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import uk.co.sheffieldprogrammer.property.dto.LandlordDto;
import uk.co.sheffieldprogrammer.property.dto.LandlordMapper;
import uk.co.sheffieldprogrammer.property.model.Landlord;
import uk.co.sheffieldprogrammer.property.repository.LandlordRepository;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class LandlordService {

    @Autowired
    private LandlordRepository landlordRepository;

    @Autowired
    private LandlordMapper landlordMapper;

    @CacheEvict(value = "landlords", allEntries = true)
    public LandlordDto addLandlord(LandlordDto landlordDto) {
        Landlord landlord = Landlord.builder().name(landlordDto.getName()).build();
        Landlord saved = landlordRepository.save(landlord);
        return landlordMapper.toDto(saved);
    }

    @Cacheable("landlords")
    public List<LandlordDto> getLandlords() {
        log.info("getting landlords");
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

    @CacheEvict(value = "landlords", allEntries = true)
    public void deleteLandlord(Long id) {
        log.info("delete landlord {}", id);
        landlordRepository.deleteById(id);
    }
}
