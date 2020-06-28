package ru.geosource.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.geosource.dto.CountyDto;
import ru.geosource.dto.StateDto;
import ru.geosource.exception.CountyNotFoundException;
import ru.geosource.exception.StateNotFoundException;

import java.util.Arrays;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class OpenStreetMapService {

    private final RestTemplate restTemplate;
    @Value("${application.findByState}")
    private String urlForState;
    @Value("${application.findByCounty}")
    private String urlForCounty;

    public List<StateDto> findByState(String state) {
        ResponseEntity<StateDto[]> stateEntity = restTemplate.getForEntity(urlForState, StateDto[].class, state);
        if (ArrayUtils.isNotEmpty(stateEntity.getBody())) {
            return Arrays.asList(stateEntity.getBody());
        } else {
            throw new StateNotFoundException(state + " не найден");
        }
    }

    public List<CountyDto> findByCounty(String county) {
        ResponseEntity<CountyDto[]> countyEntity = restTemplate.getForEntity(urlForCounty, CountyDto[].class, county);
        if (ArrayUtils.isNotEmpty(countyEntity.getBody())) {
            return Arrays.asList(countyEntity.getBody());
        } else {
            throw new CountyNotFoundException(county + " не найден");
        }
    }
}
