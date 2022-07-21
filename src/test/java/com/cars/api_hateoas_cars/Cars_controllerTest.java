package com.cars.api_hateoas_cars;

import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.cars.api_hateoas_cars.controller.Cars_controller;
import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JsonContent;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;


@AutoConfigureMockMvc
@SpringBootTest()
public class Cars_controllerTest {


    @Autowired
    private MockMvc mockMvc;

    @Test
    public void get_carBy_id() throws Exception {
        this.mockMvc.perform(get("/cars/get/car?id=1"))
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }
    @Test
    public void get_carBy_error() throws Exception {
        this.mockMvc.perform(get("/cars/get/car"))
                .andDo(print())
                .andExpect(status().is4xxClientError());

    }

    @Test
    public void get_allCars() throws Exception {
        this.mockMvc.perform(get("/cars/get/all_cars"))
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());


    }

}
    //



