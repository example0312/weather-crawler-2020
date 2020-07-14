package org.ajou.realcoding.weathercrawler.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.ajou.realcoding.weathercrawler.api.WeatherOpenApiClient;
import org.ajou.realcoding.weathercrawler.domain.City;
import org.ajou.realcoding.weathercrawler.domain.CurrentWeather;
import org.ajou.realcoding.weathercrawler.repository.CurrentWeatherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;

@Service
@EnableScheduling
public class CurrentWeatherService {
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private WeatherOpenApiClient weatherOpenApiClient;
    @Autowired
    private CurrentWeatherRepository currentWeatherRepository;

    private final Queue<String> cityNamesQueue = new LinkedList<>();

    @PostConstruct
    public void setUpCityNames() throws IOException {
        List<String> availableCityNames = this.getAvailableCities();
        cityNamesQueue.addAll(availableCityNames);
    }

    public List<String> getAvailableCities() throws IOException {
        List<City> cities = objectMapper.readValue(new File("city.list.json"), new TypeReference<List<City>>() {
        });

        return cities.stream()
                .filter(city -> city.getCountry().equals("KR"))
                .map(city -> city.getName())
                .collect(Collectors.toList());
    }

    public CurrentWeather getCurrentWeather(String cityName) {
        return currentWeatherRepository.findCurrentWeather(cityName);
    }

    @Scheduled(fixedDelay = 2000L)
    public void getCurrentWeatherEveryTwoSeconds() {
        String targetCityName = cityNamesQueue.poll();
        cityNamesQueue.add(targetCityName);

        CurrentWeather currentWeather = weatherOpenApiClient.getCurrentWeather(targetCityName);
        currentWeatherRepository.saveCurrentWeather(currentWeather);
    }
}
