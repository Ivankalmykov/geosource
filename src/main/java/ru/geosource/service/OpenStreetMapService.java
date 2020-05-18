package ru.geosource.service;

import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.geosource.dto.CountyDto;
import ru.geosource.dto.StateDto;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
public class OpenStreetMapService {
    @Autowired
    private RestTemplate restTemplate;
    @Value("${application.findByState}")
    private String urlForState;
    @Value("${application.findByDistrict}")
    private String urlForCounty;


    public List<StateDto> findByState(@NonNull String state) {
        ResponseEntity<StateDto[]> stateEntity = restTemplate.getForEntity(urlForState, StateDto[].class, state);
        return Arrays.asList(Objects.requireNonNull(stateEntity.getBody()));
    }

    public List<CountyDto> findByCounty(@NonNull String district) {
        ResponseEntity<CountyDto[]> countyEntity = restTemplate.getForEntity(urlForCounty, CountyDto[].class, district);
        return Arrays.asList(Objects.requireNonNull(countyEntity.getBody()));
    }
}
