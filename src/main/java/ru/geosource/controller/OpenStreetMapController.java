package ru.geosource.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
public class OpenStreetMapController {
    private final OpenStreetMapService openStreetMapService;

    @Autowired
    public OpenStreetMapController(OpenStreetMapService openStreetMapService) {
        this.openStreetMapService = openStreetMapService;
    }

    @Cacheable(value = "searchCache", key = "#state")
    @GetMapping(value = "/search", params = "state")
    public List<StateDto> findByState(@RequestParam(required = false) String state) {
        List <StateDto> listState = null;
        try {
            listState = openStreetMapService.findByState(state);
        } catch (Exception e) {
            log.error("Ошибка получения объекта по области", e);
        }
        return listState;
    }

    @Cacheable(value = "cacheForCountySearch", key = "#county")
    @GetMapping(value = "/search", params = "county")
    public List<CountyDto> findByCounty(@RequestParam(required = false) String county) {
        List<CountyDto> listCounty = null;
        try {
            listCounty = openStreetMapService.findByCounty(county);
        } catch (Exception e) {
            log.error("Ошибка получения объекта по федеральному округу", e);
        }
        return listCounty;
    }
}
