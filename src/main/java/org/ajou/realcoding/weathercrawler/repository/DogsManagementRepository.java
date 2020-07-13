package org.ajou.realcoding.weathercrawler.repository;

import org.ajou.realcoding.weathercrawler.domain.Dog;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class DogsManagementRepository {
    List<Dog> dogList = new ArrayList<>();

    public void insertDog(Dog dog) {
        dogList.add(dog);
    }

    public Dog findDog(String name) {
        for (Dog dog : dogList) {
            if (dog.getName().equals(name)) {
                return dog;
            }
        }

        throw new RuntimeException();
    }

    public void updateDogsType(String name, String type) {
        Dog dog = this.findDog(name);
        dog.setType(type);
    }

    public void updateDog(Dog dog) {
        Dog toBeUpdatedDog = this.findDog(dog.getName());
        toBeUpdatedDog.setType(dog.getType());
        toBeUpdatedDog.setAge(dog.getAge());
    }

    public void removeDog(String name) {
        Dog dog = this.findDog(name);
        dogList.remove(dog);
    }
}
