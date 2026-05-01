package utilities;

import org.testng.annotations.DataProvider;

import java.io.IOException;

public class DataProviders {

    // DataProvider 1
    @DataProvider(name="LoginData")
    public String[][] getData() throws IOException {

        String path = "./testData//DataTestOpenCart.csv"; // taking xl file from testData

        ExcelUtility xlutil = new ExcelUtility(path); // creating an object for XLUtility

        int totalRows = xlutil.getRowCount("Sheet1");
        int totalCols = xlutil.getCellCount("Sheet1", 1);

        String loginData[][] = new String[totalRows][totalCols]; // created for two dimensional array

        for(int i = 1; i <= totalRows; i++) { // read the data from xl storing in 2D array
            for(int j = 0; j < totalCols; j++) { // i = rows, j = cols
                loginData[i-1][j] = xlutil.getCellData("Sheet1", i, j);
            }
        }

        return loginData; // returning two dimensional array
    }
}
