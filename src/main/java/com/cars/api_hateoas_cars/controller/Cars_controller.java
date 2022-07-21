package com.cars.api_hateoas_cars.controller;

import com.cars.api_hateoas_cars.domain.Car;
import com.cars.api_hateoas_cars.service.Car_service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class Cars_controller {
    @Autowired
    private Car_service service;


    @GetMapping("cars/get/car")
    public HttpEntity<Car> get_car( @RequestParam Long id){

        Car car = service.select_by_id(id);

        //TODO isto 'mapeia' a classe em busca de metodos
        //TODO aqui sao metodos de 'end-points'
        //TODO depois ele define um link com rel, navegaveis *isto foi um resumo
        car.add(linkTo(methodOn(Cars_controller.class).get_car(id)).withSelfRel());
       // car.add(linkTo(methodOn(Cars_controller.class).edi_car(id,car)).withRel("editar_car"));
       // car.add(linkTo(methodOn(Cars_controller.class).deleteCar(id)).withRel("delete_car"));
        car.add(linkTo(methodOn(Cars_controller.class).get_all_cars()).withRel("list_all"));
        //car.add(linkTo(methodOn(Cars_controller.class).get_all_cars()).withRel("create"));

        return new ResponseEntity<>(car, HttpStatus.OK);
    }



    @GetMapping("cars/get/all_cars")
    public List<Car> get_all_cars(){

        return service.select_all();
    }


    @PostMapping("cars/save/car")
    public String save_car(@RequestBody Car newCar){//Todo esta forma de gerar objeto esta errado: #01
        service.insert(newCar);

        return "redirect:/cars/get/car/{"+newCar.getId()+"}";
    }

    @PutMapping("cars/edit/car")
    public String edi_car(@RequestParam Long id ,@RequestBody Car newCar){
        //Todo esta forma de gerr objeto esta errado: #01

        boolean is_null =  Objects.isNull(service.select_by_id(id));
        if(!is_null){
            service.update_car(newCar);
            return "redirect:/cars/get/car/{"+newCar.getId()+"}";
        }



        return "redirect:/cars/get/car/";
    }

    @DeleteMapping("cars/delete/car")
    public String deleteCar(@RequestParam Long id){

        service.soft_delete(id);
        return "redirect:/cars/get/car/{"+id+"}";
    }


}
