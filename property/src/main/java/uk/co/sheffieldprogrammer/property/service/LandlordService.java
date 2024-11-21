package uk.co.sheffieldprogrammer.property.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.co.sheffieldprogrammer.property.dto.LandlordDto;
import uk.co.sheffieldprogrammer.property.model.Landlord;
import uk.co.sheffieldprogrammer.property.repository.LandlordRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class LandlordService {

    @Autowired
    private LandlordRepository landlordRepository;

    public void addLandlord(LandlordDto landlordDto) {
        Landlord landlord = Landlord.builder().name(landlordDto.getName()).build();

        landlordRepository.save(landlord);

    }

    public List<LandlordDto> getLandlords() {
        List<LandlordDto> landlordDtos = new ArrayList<>();
        Iterable<Landlord> landlords = landlordRepository.findAll();
        for (Landlord landlord : landlords) {
            LandlordDto landlordDto = LandlordDto
                    .builder()
                    .name(landlord.getName())

                    .build();
            landlordDtos.add(landlordDto);
        }
        return landlordDtos;

    }


}
