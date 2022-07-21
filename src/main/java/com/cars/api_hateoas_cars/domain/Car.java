package com.cars.api_hateoas_cars.domain;


import lombok.*;

import org.springframework.hateoas.RepresentationModel;


import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Car extends RepresentationModel<Car> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String car;

    private Integer year;

    private double price;

    @Column(columnDefinition = "boolean DEFAULT false")
    private boolean deleted;


}
