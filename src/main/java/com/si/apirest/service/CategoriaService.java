package com.si.apirest.service;

import org.springframework.stereotype.Service;

import com.si.apirest.dto.CategoriaDTO;
import com.si.apirest.entity.Categoria;
import com.si.apirest.exceptions.NotFoundException;
import com.si.apirest.repository.CategoriaRepository;

import lombok.RequiredArgsConstructor;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoriaService {
    private final CategoriaRepository categoriaRepository;
    
    public void test(){
        System.out.println("Hola mundo");
    }

    public Categoria createCategoria(CategoriaDTO categoriaDTO){
        Categoria categoria = new Categoria();
        categoria.setNombre(categoriaDTO.getNombre());
        return categoriaRepository.save(categoria);
    }
    
    public CategoriaDTO updateCategoria(int id, CategoriaDTO categoriaDTO){
        Categoria categoria = categoriaRepository.findById(id).orElseThrow(
            ()-> new NotFoundException("Categoria not found.")
        );
        categoria.setNombre(categoriaDTO.getNombre());
        categoriaRepository.save(categoria);
        return categoriaDTO;
    }

    public void deleteCategoria(int id){
        Categoria categoria = categoriaRepository.findById(id).orElseThrow(
            ()-> new NotFoundException("Categoria not found.")
        );
        categoriaRepository.delete(categoria);
    }

    public List<CategoriaDTO> getAllCategorias(){
        List<Categoria> categorias = categoriaRepository.findAll();
        return categorias.stream().map(this::mapToCategoriaDTO).toList();
    }


    public List<CategoriaDTO> getCategoriaByPaciente(int idPaciente){
        List<Categoria> categorias = categoriaRepository.findCategoriasByPacienteId(idPaciente);
        return categorias.stream().map(this::mapToCategoriaDTO).toList();
    }
    

    private CategoriaDTO mapToCategoriaDTO(Categoria categoria) {
        CategoriaDTO categoriaDTO = new CategoriaDTO();
        categoriaDTO.setId(categoria.getId());
        categoriaDTO.setNombre(categoria.getNombre());
        return categoriaDTO;
    }

    

}
