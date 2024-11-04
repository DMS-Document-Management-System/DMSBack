package com.si.apirest.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.si.apirest.dto.Workflow.WorkflowDTO;
import com.si.apirest.entity.Workflow;
import com.si.apirest.repository.WorkflowRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WorkflowService {
    
    private final WorkflowRepository workflowRepository;

    private final ModelMapper modelMapper;

    public WorkflowDTO createWorkflow(WorkflowDTO workflowDTO) {
        Workflow workflow = workflowRepository.save(modelMapper.map(workflowDTO, Workflow.class));
        return modelMapper.map(workflow, WorkflowDTO.class);
    }

    public List<Workflow> findWorkflow() {
        return workflowRepository.findAll();
    }

}
