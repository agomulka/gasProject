
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;


public class GeneratorExcelFile {
    private XSSFWorkbook workbook;
    XSSFSheet sheet, year20Sheet, year21Sheet;
    ListService listService;
    List<GasData> gasList;
    GasService gs;
    Object[][] result, year21List, year20List;
    private Object[][] titles = {{"date", "gas value", "temperature"}};

    public GeneratorExcelFile(GasService gs) {
        this.gs = gs;
        this.listService = gs.getListService();
        this.gasList = gs.getListService().getList();

        this.workbook = new XSSFWorkbook();
        sheet = workbook.createSheet("All");
        year20Sheet = workbook.createSheet("year2020");
        year21Sheet = workbook.createSheet("year2021");
        this.result = createSheet(gasList);
        this.year21List = createSheet(listService.getYearList(2021));
        this.year20List = createSheet(listService.getYearList(2020));
    }


    private Object[][] createSheet(List<GasData> gasList) {
        Object[][] fromList = getFromList(gasList);
        Object[][] result = new Object[titles.length + fromList.length][fromList[0].length];

        System.arraycopy(titles, 0, result, 0, titles.length);
        System.arraycopy(fromList, 0, result, titles.length, fromList.length);

        return result;
    }


    private Object[][] getFromList(List<GasData> gasList) {
        Object[][] fromList = new Object[gasList.size()][3];
        for (int i = 0; i < gasList.size(); i++) {
            fromList[i][0] = gasList.get(i).getDateAsString();
            fromList[i][1] = gasList.get(i).getValue();
            fromList[i][2] = gasList.get(i).getTemperature();
        }
        return fromList;
    }

    private Object[][] getResult() {
        return result;
    }

    public void print() {
        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result[0].length; j++) {
                System.out.println(result[i][j]);
            }
        }
    }

    public void generate() {
        generate(result, sheet);
        generate(year20List, year20Sheet);
        generate(year21List, year21Sheet);
    }

    public void generate(Object[][] result, XSSFSheet sheet) {
        int rowCount = 0;
        for (Object[] aDate : result) {
            Row row = sheet.createRow(++rowCount);

            int columnCount = 0;

            for (Object field : aDate) {
                Cell cell = row.createCell(++columnCount);
                if (field instanceof String) {
                    cell.setCellValue((String) field);
                } else if (field instanceof Integer) {
                    cell.setCellValue((Integer) field);
                }
            }
        }

        Row sumRow = sheet.createRow(++rowCount);
        sumRow.setHeightInPoints(35);
        Cell cell;
        cell = sumRow.createCell(1);
        cell.setCellValue("Average:");
        int howManyData = result[0].length;
        int howManyRows = result.length;
        for (int j = 2; j < howManyData + 1; j++) {
            cell = sumRow.createCell(j);
            String ref = (char) ('A' + j) + "3:" + (char) ('A' + j) + String.valueOf(1 + howManyRows);
            cell.setCellFormula("AVERAGE(" + ref + ")");
        }

        try (FileOutputStream outputStream = new FileOutputStream("gasData.xlsx")) {
            workbook.write(outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}


