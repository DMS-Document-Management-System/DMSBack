package com.si.apirest.dto.Etapa;

import com.si.apirest.dto.Workflow.WorkflowDTO;

import lombok.Data;

@Data
public class EtapaDTO {
    private int id;

    private String nombre;

    private int orden;

    private String accionRequerida;

    private WorkflowDTO workflow;

}
