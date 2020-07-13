package org.ajou.realcoding.weathercrawler.service;

import org.ajou.realcoding.weathercrawler.domain.Dog;
import org.ajou.realcoding.weathercrawler.repository.DogsManagementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DogsManagementService {

    @Autowired
    private DogsManagementRepository dogsManagementRepository;

    public void createDog(Dog dog) {
        dogsManagementRepository.insertDog(dog);
    }

    public Dog findDog(String name) {
        return dogsManagementRepository.findDog(name);
    }

    public void updateDogsType(String name, String type) {
        dogsManagementRepository.updateDogsType(name, type);
    }

    public void updateDog(Dog dog) {
        dogsManagementRepository.updateDog(dog);
    }

    public void removeDog(String name) {
        dogsManagementRepository.removeDog(name);
    }
}
