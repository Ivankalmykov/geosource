package ru.geosource.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.geosource.dto.CountyDto;
import ru.geosource.dto.StateDto;
import ru.geosource.service.OpenStreetMapService;

import java.util.List;

@RestController
public class OpenStreetMapController {
    @Autowired
    private OpenStreetMapService openStreetMapService;
    private static Logger log = Logger.getLogger(OpenStreetMapController.class);

    @Cacheable(value = "cashForStateSearch", key = "#state")
    @GetMapping("/state/{state}")
    public List<StateDto> findByState(@PathVariable(value = "state") String state) {
        List<StateDto> listState = null;
        try {
            listState = openStreetMapService.findByState(state);
        } catch (Exception e) {
            log.error("Ошибка получения объекта по области", e);
        }
        return listState;
    }

    @Cacheable(value = "cashForCountySearch", key = "#district")
    @GetMapping("/{district}")
    public List<CountyDto> findByCounty(@PathVariable(value = "district") String district) {
        List<CountyDto> listCounty = null;
        try {
            listCounty = openStreetMapService.findByCounty(district);
        } catch (Exception e) {
            log.error("Ошибка получения объекта по федеральному округу", e);
        }
        return listCounty;
    }
}
