package com.example.db.GH_gestion.Exel_Conection;
import com.example.db.Functions_Chatbot.Function_Extra.USERCHAT_ADD_FUNCTION.userchatbot_add;
import com.example.db.Ite_chat_user.Entity.User_chatbot;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xwpf.usermodel.BreakType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

//planteamiento quitar getstring o hacerlo publico
@Component
public class Load_Exel {

    @Autowired
    Load_by_atributo loadByAtributo;
    @Autowired
    Load_by_Row loadByRow;
    @Autowired
    userchatbot_add userchatbotadd;


//funcion basica todo perfecto
    public Sheet Cargar_Archivo(String path_ExelFile) throws IOException {
        FileInputStream inputStream = new FileInputStream(path_ExelFile);
        Workbook workbook = new XSSFWorkbook(inputStream);
        Sheet sheet = workbook.getSheetAt(0);

        return  sheet;

    }


    private String getCellStringValue(Cell cell) {
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

}
