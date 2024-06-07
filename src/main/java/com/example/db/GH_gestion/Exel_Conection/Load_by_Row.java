package com.example.db.GH_gestion.Exel_Conection;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//pendiente combinacion de todas las funciones para una sola salida
@Component
public class Load_by_Row {
    @Autowired
    Load_by_atributo loadByAtributo;



    //funcion basica en funcionamiento correcto
    public Map<Integer,String> getHeaderIndices( List<String> headers,Sheet sheet) throws IOException, IOException {

        List<Integer> indices = new ArrayList<>();

        // Leer la primera fila como encabezados
        Row headerRow = sheet.getRow(0);

        // Verificar cada encabezado en la lista proporcionada
        for (String header : headers) {
            boolean found = false;
            for (Cell cell : headerRow) {
                if (cell.getStringCellValue().equalsIgnoreCase(header)) {
                    indices.add(cell.getColumnIndex());
                    found = true;
                    break;
                }
            }
            if (!found) {
                indices.add(-1); // Indicador de que el encabezado no fue encontrado
            }
        }
        Map<Integer, String> mapa=createMapFromLists(indices,headers);
        System.out.println(mapa);
        return mapa;



    }

    //funcion para obtener un MAP de valor con su indice
    private Map<Integer, String> createMapFromLists(List<Integer> keys, List<String> values) {
        // Verificar que ambas listas tengan el mismo tamaño
        if (keys == null || values == null || keys.size() != values.size()) {
            throw new IllegalArgumentException("Las listas deben tener el mismo tamaño y no deben ser null");
        }

        Map<Integer, String> map = new HashMap<>();

        // Llenar el Map con los elementos de las listas
        for (int i = 0; i < keys.size(); i++) {
            map.put(keys.get(i), values.get(i));
        }

        return map;
    }

    //cambia el indice por el valor añadido
    public Map<String, String> getValuesFromRow(Row row, Map<Integer, String> indexMap) {

        List<String> values = new ArrayList<>();

        // Iterar sobre los índices en el map
        for (Map.Entry<Integer, String> entry : indexMap.entrySet()) {
            Integer index = entry.getKey();
            // Obtener el valor de la celda en el índice especificado
            Cell cellValue = row.getCell(index);
            String cellValue2 = loadByAtributo.getCellStringValue(cellValue);
            values.add(cellValue2);
        }

        //creacion de un doble mapa
        Map<String, String> nuevoMapa = new HashMap<>();
        int i = 0;
        for (Map.Entry<Integer, String> entry : indexMap.entrySet()) {
            String valorMapa = entry.getValue();
            Integer claveMapa = entry.getKey();
            if (i < values.size()) {
                String valorLista = values.get(i);
                nuevoMapa.put(valorMapa, valorLista);
                i++;
            } else {
                break; // Si la lista es más corta que el mapa, salir del bucle
            }
        }

        return nuevoMapa;
    }
    }




