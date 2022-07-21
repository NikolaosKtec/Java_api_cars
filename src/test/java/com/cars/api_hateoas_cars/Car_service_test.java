package com.cars.api_hateoas_cars;

import com.cars.api_hateoas_cars.domain.Car;

import com.cars.api_hateoas_cars.service.Car_service;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
public class Car_service_test {

    @Autowired
    Car_service service;

    @Test
    public  void save(){
        Car newCar = new Car();
        newCar.setCar("Omega");
        newCar.setPrice(8000);
        newCar.setYear(1998);

        service.insert(newCar);


        assertThat(service.select_by_id(7l)).isEqualTo(newCar);

    }

    @Test
    public  void safe_delete(){
        Car newCar = new Car();


        service.soft_delete(7l);
        newCar = service.select_by_id(7l);


        assertThat(newCar.isDeleted()).isEqualTo(true);;

    }


    @Test
    public  void select_actives(){
        List <Car> newCarList = service.select_all_active();

        //Car car = ;
for(var i :newCarList ){
    assertThat(i.isDeleted()).isEqualTo(false);
    System.out.println(i.getCar()+"\n");
}


    }

    @Test
    public  void select_inatives(){
        List <Car> newCarList = service.select_all_inative();




        for( var i : newCarList){
            System.out.println(i.getCar()+"\n");
            assertThat(i.isDeleted()).isEqualTo(true);
        }

    }
}