package com.si.apirest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.si.apirest.entity.Etiqueta;
import com.si.apirest.projection.EtiquetaView;
import java.util.List;

public interface EtiquetaRepository extends JpaRepository<Etiqueta, Integer> {
    List<EtiquetaView> findByDocumentosId(int documentoId);

    List<Etiqueta> findAllByDocumentosId(int documentoId);

    Iterable<EtiquetaView> findAllProjectedBy();

    void deleteAllByDocumentosId(int documentoId);
}
