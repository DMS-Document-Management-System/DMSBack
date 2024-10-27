package com.si.apirest.controller;

import org.springframework.web.bind.annotation.RestController;

import com.si.apirest.dto.Bitacora.BitacoraDTO;
import com.si.apirest.service.BitacoraService;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/bitacora")
@RequiredArgsConstructor
@Tag(name = "Bitacora")
public class BitacoraController {
    
    private final BitacoraService bitacoraService;

    @PostMapping
    public ResponseEntity<BitacoraDTO> postMethodName(@RequestBody BitacoraDTO bitacoraDTO) {
        BitacoraDTO nuevaBitacoraDTO = bitacoraService.guardarBitacora(bitacoraDTO);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(nuevaBitacoraDTO);
    }
    
    @GetMapping
    public ResponseEntity<Page<BitacoraDTO>> getAllBitacoras(@RequestParam int pageNumber) {
        Page<BitacoraDTO> bitacoras = bitacoraService.obtenerTodasLasBitacoras(pageNumber);
        return ResponseEntity.ok(bitacoras);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BitacoraDTO> getBitacoraById(@PathVariable int id) {
        try {
            BitacoraDTO bitacoraDTO = bitacoraService.obtenerBitacoraPorId(id);
            return ResponseEntity.ok(bitacoraDTO);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

}
