package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {
	
	//DataProvider 1

	
	@DataProvider(name="LoginData")
	public String[][] getData() throws IOException{
		String path = ".\\testData\\Opencart_LoginData.xlsx";
		ExcelUtilities xlutil=new ExcelUtilities(path);
		
		int rowCount= xlutil.getRowCount("Sheet1");
		System.out.println(rowCount);
		int colCount= xlutil.getCellCount("Sheet1", 1);
		System.out.println(colCount);
		
		String data[][]= new String	[rowCount][colCount];
		
		for(int i=1;i<=rowCount;i++) {
			for(int j=0;j<colCount;j++) {
				data[i-1][j]= xlutil.getCellData("Sheet1", i, j);
			}
		}
		
		System.out.println(data);
				
		return data;
	}
	
}
