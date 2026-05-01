package utilities;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtility {
    public FileInputStream fi;
    public FileOutputStream fo;
    public XSSFWorkbook wb;
    public XSSFSheet ws;
    public XSSFRow row;
    public XSSFCell cell;
    public CellStyle style;
    String path;

    public ExcelUtility(String path)
    {
        this.path=path;
    }

    /** CSV is plain text; {@link XSSFWorkbook} only supports .xlsx (OOXML). */
    private boolean isCsv() {
        return path != null && path.toLowerCase().endsWith(".csv");
    }

    private static int csvLineCount(String path) throws IOException {
        int n = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            while (br.readLine() != null) {
                n++;
            }
        }
        return n;
    }

    private static String csvLineAt(String path, int rowIndex) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            int i = 0;
            while ((line = br.readLine()) != null) {
                if (i == rowIndex) {
                    return line;
                }
                i++;
            }
        }
        return null;
    }

    public int getRowCount(String sheetName) throws IOException
    {
        if (isCsv()) {
            int lines = csvLineCount(path);
            return lines == 0 ? 0 : lines - 1;
        }
        fi=new FileInputStream(path);
        wb=new XSSFWorkbook(fi);
        ws=wb.getSheet(sheetName);
        int rowcount=ws.getLastRowNum();

        wb.close();
        fi.close();
        return rowcount;
    }

    public int getCellCount(String sheetName,int rownum) throws IOException
    {
        if (isCsv()) {
            String line = csvLineAt(path, rownum);
            return line == null ? 0 : line.split(",", -1).length;
        }
        fi=new FileInputStream(path);
        wb=new XSSFWorkbook(fi);
        ws=wb.getSheet(sheetName);
        row=ws.getRow(rownum);
        int cellcount=row.getLastCellNum();

        wb.close();
        fi.close();
        return cellcount;
    }

    public String getCellData(String sheetName,int rownum,int colnum) throws IOException
    {
        if (isCsv()) {
            String line = csvLineAt(path, rownum);
            if (line == null) {
                return "";
            }
            String[] cells = line.split(",", -1);
            return colnum >= 0 && colnum < cells.length ? cells[colnum] : "";
        }
        fi=new FileInputStream(path);
        wb=new XSSFWorkbook(fi);
        ws=wb.getSheet(sheetName);
        row=ws.getRow(rownum);
        cell=row.getCell(colnum);

        DataFormatter formatter = new DataFormatter();
        String data;
        try
        {
            data = formatter.formatCellValue(cell);
        }
        catch(Exception e)
        {
            data="";
        }

        wb.close();
        fi.close();
        return data;
    }

    public void setCellData(String sheetName,int rownum,int colnum,String data) throws IOException
    {
        if (isCsv()) {
            throw new UnsupportedOperationException("setCellData is not supported for .csv files; use .xlsx");
        }

        File xlfile=new File(path);
        if(!xlfile.exists())                  // If file not exists then create new file
        {
            wb=new XSSFWorkbook();
            fo=new FileOutputStream(path);
            wb.write(fo);
        }
        fi=new FileInputStream(path);
        wb=new XSSFWorkbook(fi);

        if(wb.getSheetIndex(sheetName)==-1)
            wb.createSheet(sheetName);
        ws=wb.getSheet(sheetName);

        if(ws.getRow(rownum)==null)
            ws.createRow(rownum);



        row=ws.getRow(rownum);
        cell=row.createCell(colnum);
        cell.setCellValue(data);

        fo=new FileOutputStream(path);
        wb.write(fo);

        wb.close();
        fi.close();
        fo.close();

    }

    public void fillRedColor(String sheetName, int rownum,int colnum) throws IOException
    {
        if (isCsv()) {
            throw new UnsupportedOperationException("fillRedColor is not supported for .csv files; use .xlsx");
        }
        fi=new FileInputStream(path);
        wb=new XSSFWorkbook(fi);
        ws=wb.getSheet(sheetName);
        row=ws.getRow(rownum);
        cell=row.getCell(colnum);

        style=wb.createCellStyle();

        style.setFillForegroundColor(IndexedColors.RED.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        cell.setCellStyle(style);
        fo = new FileOutputStream(path);
        wb.write(fo);
        wb.close();
        fi.close();
        fo.close();

    }

}
