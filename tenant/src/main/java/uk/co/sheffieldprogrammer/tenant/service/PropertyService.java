package uk.co.sheffieldprogrammer.tenant.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import uk.co.sheffieldprogrammer.tenant.dto.ApartmentDto;
import uk.co.sheffieldprogrammer.tenant.dto.PropertyDto;

@Service
public class PropertyService {

    private RestTemplate restTemplate = new RestTemplate();

    ApartmentDto getApartment(Long id) {
         return restTemplate.getForEntity("http://localhost:8080/apartment/" + id, ApartmentDto.class).getBody();
    }
}
