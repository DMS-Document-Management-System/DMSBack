package com.si.apirest.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.si.apirest.dto.Categoria.CategoriaDTO;
import com.si.apirest.entity.Categoria;
import com.si.apirest.service.CategoriaService;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/categoria")
@Tag(name =  "Categoria")
public class CategoriaController {

    private final CategoriaService categoriaService;

        @GetMapping
        public ResponseEntity<List<CategoriaDTO>> getAll() {
            return ResponseEntity.ok(categoriaService.getAllCategorias());
        }
    
        @PostMapping
        public ResponseEntity<Categoria> create(@RequestBody CategoriaDTO categoriaDTO) {
            return ResponseEntity.ok(categoriaService.createCategoria(categoriaDTO));
        }
    
        @PutMapping("/{id}")
        public ResponseEntity<CategoriaDTO> update(@PathVariable int id, @RequestBody CategoriaDTO categoriaDTO) {
            return ResponseEntity.ok(categoriaService.updateCategoria(id, categoriaDTO));
        }
    
        @DeleteMapping("/{id}")
        public ResponseEntity<String> delete(@PathVariable int id) {
            return ResponseEntity.ok("Delete request - Test for ID " + id);
        }

        @GetMapping("/paciente/{idPaciente}")
        public ResponseEntity<List<CategoriaDTO>> getCategoriasByPacienteId(@PathVariable int idPaciente) {
            return ResponseEntity.ok(categoriaService.getCategoriaByPaciente(idPaciente));
        }
}
