package com.si.apirest.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.si.apirest.dto.Bitacora.BitacoraDTO;
import com.si.apirest.exceptions.BitacoraNotFoundException;
import com.si.apirest.factory.BitacoraFactory;
import com.si.apirest.repository.BitacoraRepository;


@Service
public class BitacoraService {

    private final BitacoraRepository bitacoraRepository;

    public BitacoraService(BitacoraRepository bitacoraRepository) {
        this.bitacoraRepository = bitacoraRepository;
    }

    public BitacoraDTO guardarBitacora(BitacoraDTO bitacoraDTO) {
        return BitacoraFactory.createBitacoraDTO(
                bitacoraRepository.save(BitacoraFactory.createBitacora(bitacoraDTO))
            );
    }

    public BitacoraDTO obtenerBitacoraPorId(int id) {
        return bitacoraRepository.findById(id)
                .map(BitacoraFactory::createBitacoraDTO)
                .orElseThrow(() -> new BitacoraNotFoundException(id));
    }

    public Page<BitacoraDTO> obtenerTodasLasBitacoras(int pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber, 10);
        return bitacoraRepository.findAll(pageable).map(BitacoraFactory::createBitacoraDTO);
    }

}
