package com.si.apirest.controller;

import java.io.IOException;
import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.si.apirest.service.ExcelExporterService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/excel")
@Tag(name = "ExcelExporter")
public class ExcelExporterController {
    
    private final ExcelExporterService excelExporterService;

    @GetMapping("/exportar-bitacoras-fechas")
    @Operation(summary = "Obtiene un excel de bitácoras según los parametros enviados como ejemplo.",
        description = "El parámetro maxResults es obligatorio, los parametros restantes son opcionales."
    )
    public void exportarBitacorasFechas(
        @RequestParam int maxResults,
        @RequestParam(required = false) String accion,
        @RequestParam(required = false) String ip,
        @RequestParam(required = false) 
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
        LocalDateTime  fechaInicio, 
        @RequestParam(required = false) 
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
        LocalDateTime  fechaFin,
            HttpServletResponse response) throws IOException {

        // Generamos el reporte en bytes
        byte[] excelBytes = excelExporterService.generarReporteBitacorasFechas(maxResults, accion, ip, fechaInicio, fechaFin);

        // Configuramos la respuesta HTTP para enviar el archivo Excel
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=bitacoras.xlsx");
        response.getOutputStream().write(excelBytes);
        response.getOutputStream().flush();
    }

}
