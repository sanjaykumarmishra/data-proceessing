package com.dataloader.dataprocessing.helper;

import com.dataloader.dataprocessing.entity.PatientDetails;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Slf4j
public class Helper {


    //check that file is of excel type or not
    public static boolean checkExcelFormat(MultipartFile file) {

        String contentType = file.getContentType();



        if (contentType.equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")) {
            return true;
        } else {
            return false;
        }

    }


    //convert excel to list of PatientDetails

    public static List<PatientDetails> convertExcelToListOfPatientDetails(InputStream is) {
        List<PatientDetails> list = new ArrayList<>();

        try {


            XSSFWorkbook workbook = new XSSFWorkbook(is);
            System.out.println(workbook.getAllNames());
            XSSFSheet sheet = workbook.getSheet("data");
            int rowNumber = 0;
            Iterator<Row> iterator = sheet.iterator();
            DataFormatter formatter = new DataFormatter();
            while (iterator.hasNext()) {
                Row row = iterator.next();

                if (rowNumber == 0) {
                    rowNumber++;
                    continue;
                }

                Iterator<Cell> cells = row.iterator();

                int cid = 0;

                PatientDetails p = new PatientDetails();

                while (cells.hasNext()) {
                    Cell cell = cells.next();

                    switch (cid) {
                        case 0:
                            log.warn(String.valueOf(cell.getNumericCellValue()));
                            p.setPatientId((int) cell.getNumericCellValue());
                            break;
                        case 1:
                            log.warn(cell.getStringCellValue());
                            p.setPatientName(cell.getStringCellValue());
                            break;
                        case 2:
                            log.warn(cell.getStringCellValue());
                            p.setPatientAddress(cell.getStringCellValue());
                            break;
                        case 3:
                            log.warn(cell.getStringCellValue());
                            p.setPatientDateofBirth(cell.getStringCellValue());
                            break;
                        case 4:
                            log.warn(cell.getStringCellValue());
                            p.setPatientEmail(cell.getStringCellValue());
                            break;
                        case 5:
                            String number = formatter.formatCellValue(cell);
                            log.warn(number);
                            p.setPatientContactNumber(number);
                            break;
                        case 6:
                            log.warn(cell.getStringCellValue());
                            p.setPatientDrugId(cell.getStringCellValue());
                            break;
                        case 7:
                            log.warn(cell.getStringCellValue());
                            p.setPatientDrugName(cell.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                    cid++;

                }

                list.add(p);


            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;

    }


}