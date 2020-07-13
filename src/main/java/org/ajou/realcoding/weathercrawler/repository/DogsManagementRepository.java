package org.ajou.realcoding.weathercrawler.repository;

import lombok.extern.slf4j.Slf4j;
import org.ajou.realcoding.weathercrawler.domain.Dog;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@Slf4j
public class DogsManagementRepository {
    List<Dog> dogList = new ArrayList<>();

    public void insertDog(Dog dog) {
        dogList.add(dog);
        log.info("Dog has been inserted successfully. {}", dog);
    }

    public Dog findDog(String name) {
        for (Dog dog : dogList) {
            if (dog.getName().equals(name)) {
                log.info("Dog found successfully. {}", dog);
                return dog;
            }
        }

        log.error("Can't find Dog!! name:{}", name);
        throw new RuntimeException();
    }

    public void updateDogsType(String name, String type) {
        Dog dog = this.findDog(name);
        dog.setType(type);
        log.info("Dog's type has been updated successfully. name:{} type:{}", name, type);
    }

    public void updateDog(Dog dog) {
        Dog toBeUpdatedDog = this.findDog(dog.getName());
        toBeUpdatedDog.setType(dog.getType());
        toBeUpdatedDog.setAge(dog.getAge());

        log.info("Dog has been updated successfully. dog:{}", dog);
    }

    public void removeDog(String name) {
        Dog dog = this.findDog(name);
        dogList.remove(dog);

        log.info("Dog has been removed successfully. name:{}", name);
    }
}
