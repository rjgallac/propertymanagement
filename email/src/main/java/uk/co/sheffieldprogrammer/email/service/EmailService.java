package uk.co.sheffieldprogrammer.email.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import uk.co.sheffieldprogrammer.email.dto.MaintenanceDto;

@Service
@Slf4j
public class EmailService {
    private RestTemplate restTemplate = new RestTemplate();

    public void email(){
        ResponseEntity<MaintenanceDto[]> forEntity = restTemplate.getForEntity("http://localhost:8080/maintenance/date/2020-12-31T15:53:16", MaintenanceDto[].class);
        MaintenanceDto[] maintenanceDtos = forEntity.getBody();
        for (MaintenanceDto maintenanceDto : maintenanceDtos) {
            log.info("sending message to {}", maintenanceDto.getBookingDto().getTenantDto().getAddress());
        }


    }
}
