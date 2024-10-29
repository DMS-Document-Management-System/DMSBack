package com.si.apirest.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import com.si.apirest.dto.Bitacora.BitacoraDTO;
import com.si.apirest.dto.Person.PersonDTO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ExcelExporterService {

    private final BitacoraService bitacoraService;

    public byte[] generarReporteBitacoras(int maxResults, 
        String username,
        String accion,
        Integer idUser
    ) throws IOException {
        BitacoraDTO bitacoraDTO = new BitacoraDTO();
        PersonDTO personDTO = new PersonDTO();

        if (username != null)
            personDTO.setUsuario(username);

        if (idUser != null)            
            personDTO.setId(idUser);

        if(accion != null)
            bitacoraDTO.setAccion(accion);

        bitacoraDTO.setUser(personDTO);

        List<BitacoraDTO> bitacoras = bitacoraService.buscarPorEjemplo(bitacoraDTO, maxResults);

        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Bitacoras");

        // Encabezado del Excel
        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("ID");
        headerRow.createCell(1).setCellValue("Descripción");
        headerRow.createCell(2).setCellValue("Fecha");
        headerRow.createCell(3).setCellValue("Usuario");
        headerRow.createCell(4).setCellValue("Ip");

        // Llenamos las filas con los datos
        int rowNum = 1;
        for (BitacoraDTO bitacora : bitacoras) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(bitacora.getId());
            row.createCell(1).setCellValue(bitacora.getAccion());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            row.createCell(2).setCellValue(sdf.format(bitacora.getFecha().getTime()));
            row.createCell(3).setCellValue(bitacora.getUser().getUsuario());
            row.createCell(4).setCellValue(bitacora.getIp());

        }

        // Ajustamos las columnas automáticamente
        for (int i = 0; i < 4; i++) {
            sheet.autoSizeColumn(i);
        }

        // Escribimos el archivo Excel en un ByteArrayOutputStream
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        workbook.write(out);
        workbook.close();

        // Devolvemos el array de bytes del Excel
        return out.toByteArray();
    }

    public byte[] generarReporteBitacorasFechas(int maxResults, 
        String accion,
        String ip,
        LocalDateTime  fechaInicio, 
        LocalDateTime  fechaFin
    ) throws IOException {
        BitacoraDTO bitacoraDTO = new BitacoraDTO();
        
        if(accion != null)
            bitacoraDTO.setAccion(accion);
        if(ip != null)
            bitacoraDTO.setIp(ip);

        List<BitacoraDTO> bitacoras = bitacoraService.buscarPorCriterios(bitacoraDTO, fechaInicio, fechaFin, maxResults);

        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Bitacoras");

        // Encabezado del Excel
        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("ID");
        headerRow.createCell(1).setCellValue("Descripción");
        headerRow.createCell(2).setCellValue("Fecha");
        headerRow.createCell(3).setCellValue("Usuario");
        headerRow.createCell(4).setCellValue("Ip");

        // Llenamos las filas con los datos
        int rowNum = 1;
        for (BitacoraDTO bitacora : bitacoras) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(bitacora.getId());
            row.createCell(1).setCellValue(bitacora.getAccion());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            row.createCell(2).setCellValue(sdf.format(bitacora.getFecha().getTime()));
            row.createCell(3).setCellValue(bitacora.getUser().getUsuario());
            row.createCell(4).setCellValue(bitacora.getIp());

        }

        // Ajustamos las columnas automáticamente
        for (int i = 0; i < 4; i++) {
            sheet.autoSizeColumn(i);
        }

        // Escribimos el archivo Excel en un ByteArrayOutputStream
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        workbook.write(out);
        workbook.close();

        // Devolvemos el array de bytes del Excel
        return out.toByteArray();
    }

}
