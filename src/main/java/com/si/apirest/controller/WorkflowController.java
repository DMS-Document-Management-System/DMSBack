package com.si.apirest.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.si.apirest.dto.Workflow.WorkflowDTO;
import com.si.apirest.entity.Workflow;
import com.si.apirest.service.WorkflowService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/workflow")
@RequiredArgsConstructor
@Tag(name = "Workflow")
public class WorkflowController {
    
    private final WorkflowService workflowService;

    @PostMapping
    @Operation(summary = "Crea un workflow.")
    public WorkflowDTO createWorkflow(@RequestBody WorkflowDTO workflowDTO) {
        return workflowService.createWorkflow(workflowDTO);
    }

    @GetMapping
    @Operation(summary = "Obtiene todos los workflow.")
    public List<Workflow> findAllWorkflows() {
        return workflowService.findWorkflow();
    }

}
