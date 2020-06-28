package ru.geosource;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import ru.geosource.dto.CountyDto;
import ru.geosource.dto.StateDto;

import java.util.Arrays;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class GeosourceApplicationTests {
	@Autowired
	TestRestTemplate restTemplate;


	@Test
	public void findByState(){
		StateDto stateDto = new StateDto();
		stateDto.setBoundingbox(Arrays.asList("51.7730366","54.6771616","47.9304408","52.568276"));
		stateDto.setLat("53.2128813");
		stateDto.setLon("50.8914633");
		stateDto.setDisplayName("Самарская область, Приволжский федеральный округ, Россия");
		StateDto[] byState = new StateDto[1];
		byState[0] = stateDto;

		ResponseEntity<StateDto[]> forEntity = restTemplate.getForEntity("/search?state=Самарская область", StateDto[].class);

		assertThat(forEntity.getStatusCode(), is(HttpStatus.OK));
		assertThat(forEntity.getBody(), is(byState));
	}

	@Test
	public  void findByCounty(){
		CountyDto countyDto = new CountyDto();
		countyDto.setBoundingbox(Arrays.asList("49.8012132","61.663234","41.7764767","61.6897852"));
		countyDto.setLat("55.7359267");
		countyDto.setLon("50.77496358049139");
		countyDto.setDisplayName("Приволжский федеральный округ, Россия");
		CountyDto[] byCounty = new CountyDto[1];
		byCounty[0] = countyDto;

		ResponseEntity<CountyDto[]> forEntity = restTemplate.getForEntity("/search?county=ПФО", CountyDto[].class);

		assertThat(forEntity.getStatusCode(), is(HttpStatus.OK));
		assertThat(forEntity.getBody(), is(byCounty));
	}
}
