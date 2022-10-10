import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadExcelFile {


    static Map<String, String> readExcelFile() {
        Map<String, String> wordsSubstitutePair = new HashMap<>();
        try {
            File file = new File(AppConstants.RESOURCE_FILE_DIR + "Word Substitutions.xlsx");
            FileInputStream fis = new FileInputStream(file);
            XSSFWorkbook wb = new XSSFWorkbook(fis);
            XSSFSheet sheet = wb.getSheetAt(0);
            Iterator<Row> itr = sheet.iterator();
            int rowNumber = 0;
            while (itr.hasNext()) {
                if (rowNumber == 0) {
                    rowNumber++;
                    continue;
                }
                Row row = itr.next();
                Iterator<Cell> cellIterator = row.cellIterator();
                String key = "", value = "";
                int c = 0;
                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    if (c == 0) {
                        key = cell.getStringCellValue();
                    } else {
                        value = cell.getStringCellValue();
                    }
                    c++;

                }
                wordsSubstitutePair.put(key, value);

            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return wordsSubstitutePair;

    }
}
