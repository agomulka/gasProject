
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;


public class GeneratorExcelFile {
    private XSSFWorkbook workbook;
    XSSFSheet sheet;
    ListService listService;
    List<GasData> gasList;
    Object[][] gasData;
    GasService gs;
    Object[][] result;

    public GeneratorExcelFile(GasService gs) {
        this.gs = gs;
        this.workbook = new XSSFWorkbook();
        sheet = workbook.createSheet("All");
        this.listService = gs.getListService();
        this.gasList = gs.getListService().getList();
        this.gasData = createSheet(gasList);
    }

    private Object[][] createSheet(List<GasData> gasList) {
        Object[][] fromList = new Object[gasList.size()][3];
        for (int i = 0; i < gasList.size(); i++) {
            fromList[i][0] = gasList.get(i).getDateAsString();
            fromList[i][1] = gasList.get(i).getValue();
            fromList[i][2] = gasList.get(i).getTemperature();
        }
        GasData gd = new GasData(22,"2021-03-01)");
        Object[][] gasData = {
                {"date", "gas value", "temperature"}

//
//                ,fromList,
//                {gd.getDateAsString(),gd.getTemperature(),gd.getValue()}
        };

        result = new Object[gasData.length + fromList.length][fromList[0].length];

        System.arraycopy(gasData, 0, result, 0, gasData.length);
        System.arraycopy(fromList, 0, result, gasData.length, fromList.length);


        return result;
    }

    private Object[][] getResult(){
        return result;
    }
    public void print(){
        for(int i = 0; i < gasData.length; i++){
            for (int j = 0; j < gasData[0].length; j++){
                System.out.println(gasData[i][j]);
            }
        }
    }
    public void generate() {
        int rowCount = 0;
        for (Object[] aDate : gasData) {
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
        cell = sumRow.createCell(0);
        cell = sumRow.createCell(1);
        cell.setCellValue("Average:");
        int howManyData = getResult()[0].length;
        int howManyRows = getResult().length;
        for (int j = 2; j < howManyData + 1; j++) {
            cell = sumRow.createCell(j);
            String ref = (char)('A' + j) + "3:" + (char)('A' + j) + String.valueOf(1+howManyRows);
            cell.setCellFormula("AVERAGE(" + ref + ")");
        }

        try (FileOutputStream outputStream = new FileOutputStream("gasData.xlsx")) {
            workbook.write(outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}


