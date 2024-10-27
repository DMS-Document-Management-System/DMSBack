package com.si.apirest.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.si.apirest.dto.Rol.RolDTO;
import com.si.apirest.dto.Rol.RolGetDTO;
import com.si.apirest.service.RolService;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/rol")
@RequiredArgsConstructor
@Tag(name = "Rol")
public class RolController {
    
    private final RolService rolService;

    @PostMapping
    public RolGetDTO createRol(@RequestBody RolDTO roleEntity) {
        return rolService.crearRol(roleEntity);
    }
    
    @PutMapping("/{id}")
    public RolGetDTO updateRol(@PathVariable int id, @RequestBody RolDTO rolDTO) {
        return rolService.updateRol(id, rolDTO);
    }

    @GetMapping("/{id}")
    public RolGetDTO getRol(@PathVariable int id) {
        return rolService.getRol(id);
    }

    @GetMapping("/page/{pageNumber}")
    public Page<RolGetDTO> getAllRol(@PathVariable int pageNumber) {
        return rolService.getAllRol(pageNumber);
    }

    @DeleteMapping("/{id}")
    public void deleteRol(@PathVariable int id) {
        rolService.deleteRol(id);
    }

}
