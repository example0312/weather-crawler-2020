package org.ajou.realcoding.weathercrawler.controller;

import org.ajou.realcoding.weathercrawler.domain.Dog;
import org.ajou.realcoding.weathercrawler.service.DogsManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class DogsManagementController {

    @Autowired
    private DogsManagementService dogsManagementService;

    @PostMapping("/dogs")
    public void createDog(@RequestBody Dog requestBody) {
        dogsManagementService.createDog(requestBody);
    }

    // http://localhost:8080/dogs?name=Ian
    @GetMapping("/dogs")
    public Dog findDog(@RequestParam String name) {
        return dogsManagementService.findDog(name);
    }

    @PatchMapping("/dogs/{name}")
    public void updateDogsType(@PathVariable String name, @RequestBody String type) {
        dogsManagementService.updateDogsType(name, type);
    }

    @PutMapping("/dogs/{name}")
    public void updateDog(@RequestBody Dog dog) {
        dogsManagementService.updateDog(dog);
    }

    @DeleteMapping("/dogs")
    public void delete(@RequestParam String name) {
        dogsManagementService.removeDog(name);
    }
}
