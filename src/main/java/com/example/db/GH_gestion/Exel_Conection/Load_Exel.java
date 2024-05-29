package com.example.db.GH_gestion.Exel_Conection;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xwpf.usermodel.BreakType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

@Component
@RestController
public class Load_Exel {

    private Sheet Cargar_Archivo(String path_ExelFile) throws IOException {
        FileInputStream inputStream = new FileInputStream(path_ExelFile);
        Workbook workbook = new XSSFWorkbook(inputStream);
        Sheet sheet = workbook.getSheetAt(0);
        if(sheet!=null){
            System.out.println("cargar archivo exitoso ");
        }
        return  sheet;

    }

    private Row buscar_por_atributo(String atributo,Sheet sheet){
        for (int rowIndex = 1; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
            Row row = sheet.getRow(rowIndex);
            if (row != null) {

                Cell cedulaCell = row.getCell(1); // Suponiendo que la columna "CEDULA" está en el índice 1
                System.out.println(cedulaCell);
                if (cedulaCell != null && cedulaCell.getCellType() == CellType.STRING) {
                    String cellValue = cedulaCell.getStringCellValue();
                    System.out.println(cellValue);
                    if (cellValue.equalsIgnoreCase(atributo)) {
                        System.out.println("encontre esa cedula");
                        return row;
                    }
                }
            }
        }
        return null; // No se encontró la fila coincidente

    }


    @RequestMapping("/test/exel")
    public String testeo_completo( ) throws IOException { // Valor de la cédula a buscar

        Sheet hoja=Cargar_Archivo("C:\\Users\\timetocode\\Documents\\programacion\\chatbot-programa\\GH-gestion\\MAESTRO.xlsx");

        Row filaCoincidente = buscar_por_atributo("104006012",hoja);

        if (filaCoincidente != null) {
            // Recorre las celdas de la fila

            for (int cellIndex = 0; cellIndex < filaCoincidente.getLastCellNum(); cellIndex++) {
                Cell cell = filaCoincidente.getCell(cellIndex);
                if (cell != null) {
                    // Obtiene el valor de la celda
                    switch (cell.getCellType()) {
                        case STRING:
                            String cellValue = cell.getStringCellValue();
                            System.out.println("Valor de celda: " + cellValue);
                            break;
                        case NUMERIC:
                            double numericValue = cell.getNumericCellValue();
                            System.out.println("Valor de celda: " + numericValue);
                            break;
                        // Añade casos para otros tipos de datos (fecha, booleano, etc.)
                    }
                }
            }
        } else {
            System.out.println("No se encontró la fila con la cédula: " );
        }
 return  "termine mi tarea aqui";
    }

}
