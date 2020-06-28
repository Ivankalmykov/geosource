package ru.geosource.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.geosource.dto.CountyDto;
import ru.geosource.dto.StateDto;
import ru.geosource.service.OpenStreetMapService;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class OpenStreetMapController {

    private final OpenStreetMapService openStreetMapService;

    @Cacheable(value = "searchCache", key = "#state")
    @GetMapping(value = "/search", params = "state")
    public List<StateDto> findByState(@RequestParam String state) {
        return openStreetMapService.findByState(state);
    }

    @Cacheable(value = "cacheForCountySearch", key = "#county")
    @GetMapping(value = "/search", params = "county")
    public List<CountyDto> findByCounty(@RequestParam String county) {
        return openStreetMapService.findByCounty(county);
    }
}
