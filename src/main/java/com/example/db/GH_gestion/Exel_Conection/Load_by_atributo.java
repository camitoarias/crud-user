package com.example.db.GH_gestion.Exel_Conection;


import com.example.db.Ite_chat_user.Entity.User_chatbot;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


//funcion aparentemente bien
@Component
public class Load_by_atributo {





    public Row buscar_por_atributo_center(String atributo, String encabezado, Sheet sheet) {
        // Encuentra el índice de la columna con el encabezado especificado
        Row headerRow = sheet.getRow(0); // Asumiendo que la primera fila contiene los encabezados
        int columnaIndex = -1;
        for (Cell cell : headerRow) {
            if (cell.getStringCellValue().equalsIgnoreCase(encabezado)) {
                columnaIndex = cell.getColumnIndex();
                break;
            }
        }

        if (columnaIndex == -1) {
            System.out.println("No se encontró el encabezado: " + encabezado);
            return null; // Retorna null si no encuentra el encabezado
        }

        // Busca la fila con el atributo en la columna encontrada
        for (int rowIndex = 1; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
            Row row = sheet.getRow(rowIndex);
            if (row != null) {
                Cell cell = row.getCell(columnaIndex);
                if (cell != null) {
                    String cellValue = getCellStringValue(cell);
                    if (cellValue != null && cellValue.equalsIgnoreCase(atributo)) {
                        System.out.println("Encontré el atributo en la columna " + columnaIndex);
                        System.out.println(cellValue);

                        return row;

                    }
                }
            }
        }
        return null; // Retorna null si no encuentra la fila con el atributo
    }

    public String getCellStringValue(Cell cell) {
        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                // Convertimos el valor numérico a un string sin notación científica
                return BigDecimal.valueOf(cell.getNumericCellValue()).toPlainString();
            case BOOLEAN:
                return Boolean.toString(cell.getBooleanCellValue());
            case FORMULA:
                // Aquí podrías agregar más lógica para evaluar fórmulas si es necesario
                return cell.getCellFormula();
            default:
                return null;
        }
    }

    public String salida_datos(Row filaCoincidente){
        if (filaCoincidente != null) {
            // Recorre las celdas de la fila

            for (int cellIndex = 0; cellIndex < filaCoincidente.getLastCellNum(); cellIndex++) {
                Cell cell = filaCoincidente.getCell(cellIndex);
                if (cell != null) {
                  String valor=getCellStringValue(cell);
                    System.out.println(valor);
                }
            }
        } else {
            System.out.println("No se encontró la fila con la cédula: " );
        }return "datos";
    }







}
