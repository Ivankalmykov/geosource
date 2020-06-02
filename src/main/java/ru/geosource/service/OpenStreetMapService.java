package ru.geosource.service;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
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

@Slf4j
@Service
public class OpenStreetMapService {
    @Autowired
    private RestTemplate restTemplate;
    @Value("${application.findByState}")
    private String urlForState;
    @Value("${application.findByCounty}")
    private String urlForCounty;


    public List<StateDto> findByState(@NonNull String state) {
        ResponseEntity<StateDto[]> stateEntity = restTemplate.getForEntity(urlForState, StateDto[].class, state);
        if (Arrays.asList(Objects.requireNonNull(stateEntity.getBody())).isEmpty()){
            log.error("Объект c названием " + state + " не найден");
        }
        return Arrays.asList(Objects.requireNonNull(stateEntity.getBody()));
    }

    public List<CountyDto> findByCounty(@NonNull String county) {
        ResponseEntity<CountyDto[]> countyEntity = restTemplate.getForEntity(urlForCounty, CountyDto[].class, county);
        if (Arrays.asList(Objects.requireNonNull(countyEntity.getBody())).isEmpty()){
            log.error("Объект с названием " + county + " не найден");
        }
        return Arrays.asList(Objects.requireNonNull(countyEntity.getBody()));
    }
}
