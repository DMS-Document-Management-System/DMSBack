package com.si.apirest.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import com.si.apirest.entity.Version;
import com.si.apirest.projection.VersionView;

public interface VersionRepository extends JpaRepository<Version, Integer>{
    List<VersionView> findAllByDocumentoId(int documentoId, Sort sort);
}
