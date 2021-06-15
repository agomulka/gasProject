package com.gomulka.ola;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;


public class GeneratorExcelFile {
    private XSSFWorkbook workbook;
    private List<XSSFSheet> yearSheet;
    private XSSFSheet sheet;
    private ListService listService;
    private List<GasData> gasList;
    private GasService gs;
    private Object[][] result;
    private Set<String> yearSet;
    private List<Object[][]> yearList;
    private Object[][] titles = {{"date", "gas value", "temperature"}};

    public GeneratorExcelFile(GasService gs) {
        this.gs = gs;
        this.listService = gs.getListService();
        this.gasList = gs.getListService().getList();
        this.yearSet = listService.getYearSet();

        this.workbook = new XSSFWorkbook();
        sheet = workbook.createSheet("All");
        this.yearSheet = createYearSheets();
        this.result = createSheet(gasList);
        this.yearList = createYearList();

    }

    private List<XSSFSheet> createYearSheets() {
        List<XSSFSheet> yearSheets = new ArrayList<>();
        for (String year : yearSet) {
            yearSheets.add(workbook.createSheet("year" + year));
        }
        return yearSheets;
    }

    private List<Object[][]> createYearList() {
        List<Object[][]> yearList = new ArrayList<>();
        for (String year : yearSet) {
            yearList.add(createSheet(listService.getYearList(Integer.parseInt(year))));
        }
        return yearList;
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

    // for debugging
    public void print() {
        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result[0].length; j++) {
                System.out.println(result[i][j]);
            }
        }
    }

    public void generate() {
        generate(result, sheet);
        for (int i = 0; i < yearList.size(); i++) {
            generate(yearList.get(i), yearSheet.get(i));
        }
    }

    public void generate(Object[][] result, XSSFSheet sheet) {

        int rowCount = 0;
        Row row;
        for (Object[] aDate : result) {
            row = sheet.createRow(++rowCount);

            int columnCount = 0;

            for (Object field : aDate) {
                sheet.autoSizeColumn(columnCount);
                Cell cell = row.createCell(++columnCount);
                if (field instanceof String) {
                    cell.setCellValue((String) field);
                } else if (field instanceof Integer) {
                    cell.setCellValue((Integer) field);
                }
            }
        }

        // difference between first and last
        int howManyData = result[0].length;
        int howManyRows = result.length;
        row = sheet.createRow(++rowCount);
        Cell cell;
        row.setHeightInPoints(35);
        cell = row.createCell(1);
        cell.setCellValue("Used:");
        for (int j = 2; j < howManyData; j++) {
            cell = row.createCell(j);
            String ref = (char) ('A' + j) + "3-" + (char) ('A' + j) + String.valueOf(1 + howManyRows);
            cell.setCellFormula("ABS(" + ref + ")");
        }

        try (FileOutputStream outputStream = new FileOutputStream("gasData.xlsx")) {
            workbook.write(outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}


