package ru.geosource.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StateDto {
    private List<String> boundingbox;
    private String lat;
    private String lon;
    @JsonProperty("display_name")
    private String displayName;
}
