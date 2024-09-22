package com.si.apirest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Hidden;

@RestController
@RequestMapping("/holamundo")
@Hidden
public class HolaMundo {
    
    @GetMapping
    public String holaMundo() {
        return "Hola mundo";
    }

}
