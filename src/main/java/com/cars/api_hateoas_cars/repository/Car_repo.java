package com.cars.api_hateoas_cars.repository;

import com.cars.api_hateoas_cars.domain.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Car_repo extends JpaRepository<Car,Long> {

}
