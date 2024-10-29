package com.si.apirest.service;

import java.time.LocalDateTime;
import java.util.GregorianCalendar;
import java.util.List;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.si.apirest.dto.Bitacora.BitacoraDTO;
import com.si.apirest.entity.Bitacora;
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
        bitacoraDTO.setFecha(new GregorianCalendar());
        return BitacoraFactory.createBitacoraDTO(
                bitacoraRepository.save(BitacoraFactory.createBitacora(bitacoraDTO)));
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

    public List<BitacoraDTO> buscarPorEjemplo(BitacoraDTO bitacoraDTO, int maxResults) {

        Bitacora bitacora = BitacoraFactory.createBitacora(bitacoraDTO);
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnoreNullValues()
                .withIgnoreCase()
                .withIgnorePaths("id")
                .withIgnorePaths("fecha")
                .withIgnorePaths("user")
                .withMatcher("accion", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
                .withMatcher("ip", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase());

        bitacora.setAccion("hola");
        BitacoraFactory.encryptBitacora(bitacora);

        Example<Bitacora> example = Example.of(bitacora, matcher);

        Pageable pageable = PageRequest.of(0, maxResults);

        return bitacoraRepository.findAll(example, pageable).stream().map(BitacoraFactory::createBitacoraDTO).toList();
    }

    public List<BitacoraDTO> buscarPorCriterios(BitacoraDTO bitacoraDTO, LocalDateTime  fechaInicio, LocalDateTime  fechaFin,
            int maxResults) {
        Pageable pageable = PageRequest.of(0, maxResults, Sort.by(Sort.Direction.DESC, "fecha"));

        Specification<Bitacora> spec = Specification.where(null);

        // Condicionalmente agrega criterios según los valores en bitacoraDTO
        if (bitacoraDTO.getAccion() != null && !bitacoraDTO.getAccion().isEmpty()) {
            spec = spec.and((root, query, criteriaBuilder) -> criteriaBuilder.like(
                    criteriaBuilder.lower(root.get("accion")), "%" + bitacoraDTO.getAccion().toLowerCase() + "%"));
        }

        if (bitacoraDTO.getIp() != null && !bitacoraDTO.getIp().isEmpty()) {
            spec = spec
                    .and((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("ip"), bitacoraDTO.getIp()));
        }

        // Rango de fechas
        if (fechaInicio != null && fechaFin != null) {
            spec = spec.and((root, query, criteriaBuilder) -> criteriaBuilder.between(root.get("fecha"), fechaInicio,
                    fechaFin));
        } else if (fechaInicio != null) {
            spec = spec.and((root, query, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(root.get("fecha"),
                    fechaInicio));
        } else if (fechaFin != null) {
            spec = spec.and(
                    (root, query, criteriaBuilder) -> criteriaBuilder.lessThanOrEqualTo(root.get("fecha"), fechaFin));
        }

        // Ejecuta la consulta con los criterios dinámicos
        Page<Bitacora> bitacoraPage = bitacoraRepository.findAll(spec, pageable);

        // Transforma los resultados en una lista de BitacoraDTO
        return bitacoraPage.stream()
                .map(BitacoraFactory::createBitacoraDTO)
                .toList();
    }

}
