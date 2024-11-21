package uk.co.sheffieldprogrammer.property.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import uk.co.sheffieldprogrammer.property.dto.ApartmentDto;
import uk.co.sheffieldprogrammer.property.dto.ApartmentMapper;
import uk.co.sheffieldprogrammer.property.dto.BookingDto;
import uk.co.sheffieldprogrammer.property.dto.MaintenanceDto;
import uk.co.sheffieldprogrammer.property.model.Apartment;
import uk.co.sheffieldprogrammer.property.model.Maintenance;
import uk.co.sheffieldprogrammer.property.repository.ApartmentRepository;
import uk.co.sheffieldprogrammer.property.repository.MaintenanceRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class MaintenanceService {

    @Autowired
    private ApartmentRepository apartmentRepository;

    @Autowired
    private MaintenanceRepository maintenanceRepository;

    @Autowired
    private ApartmentMapper apartmentMapper;

    RestTemplate restTemplate = new RestTemplate();

    public void addMaintenance(MaintenanceDto maintenanceDto) {
        Optional<Apartment> apartment = apartmentRepository.findById(maintenanceDto.getApartmentDto().getId());
        apartment.ifPresent(value -> maintenanceRepository.save(Maintenance.builder().maintenanceDate(maintenanceDto.getMaintenanceDate()).apartment(value).build()));
    }

    public List<MaintenanceDto> getByDate(LocalDateTime maintenanceDate) {
        log.info("getting bookings for date {}", maintenanceDate);
        List<MaintenanceDto> maintenanceDtos = new ArrayList<>();
        List<Maintenance> maintenances = maintenanceRepository.findByMaintenanceDate(maintenanceDate);
        for (Maintenance maintenance : maintenances) {
            MaintenanceDto maintenanceDto = MaintenanceDto
                    .builder()
                    .id(maintenance.getId())
                    .maintenanceDate(maintenance.getMaintenanceDate())
                    .apartmentDto(apartmentMapper.toDto(maintenance.getApartment()))
                    .build();
            ResponseEntity<BookingDto> bookingDtoResponseEntity = restTemplate.getForEntity("http://localhost:8081/booking/apartment/" + maintenance.getApartment().getId(), BookingDto.class);
            maintenanceDto.setBookingDto(bookingDtoResponseEntity.getBody());
            maintenanceDtos.add(maintenanceDto);
        }
        return maintenanceDtos;
    }
}
