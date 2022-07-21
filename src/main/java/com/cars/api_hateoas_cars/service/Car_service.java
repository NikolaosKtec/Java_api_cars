package com.cars.api_hateoas_cars.service;

import com.cars.api_hateoas_cars.domain.Car;
import com.cars.api_hateoas_cars.repository.Car_repo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class Car_service {

    @Autowired
    private Car_repo repo;

    public void insert(Car c){
        repo.save(c);
    }

    public List<Car> select_all(){
         List<Car>cars = repo.findAll();
        return cars;
    }

    public List<Car> select_all_active(){
        List<Car>cars = repo.findAll();
        List<Car>cars_act = new ArrayList<>();
                cars.stream()
                     .filter(filtred ->filtred.isDeleted() == false )
                    .forEach(filtred -> cars_act.add(filtred));

        return cars_act;
    }

    public List<Car> select_all_inative(){
        List<Car>cars = repo.findAll();
        List<Car>cars_inat = new ArrayList<>();
            cars.stream()
                .filter(filtred ->filtred.isDeleted())
                .forEach(filtred -> cars_inat.add(filtred));

        return cars_inat;
    }

    public Car select_by_id(Long id){
         Optional<Car> carOptional = repo.findById(id);
        return carOptional.orElse(null);
    }

    public void update_car(Car c){
        repo.saveAndFlush(c);
    }
    
//todo dps arrumar
    public void soft_delete(Long id){

        Car car = select_by_id(id);
        car.setDeleted(true);
        update_car(car);}


}
