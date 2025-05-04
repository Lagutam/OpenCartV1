package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtilities {
	
	public String path;
	public FileInputStream fi;
	public FileOutputStream fo;
	public XSSFWorkbook workbook;
	public XSSFSheet sheet;
	public XSSFRow row;
	public XSSFCell cell;
	public CellStyle style;
	
	
	public ExcelUtilities(String path) {
		this.path = path;
	}
	
	
	public int getRowCount(String sheet) throws IOException {
		
		fi=new FileInputStream(path);
		workbook= new XSSFWorkbook(fi);
		this.sheet= workbook.getSheet(sheet);
		int rowCount=-1;
		if(this.sheet!= null) {
		rowCount = this.sheet.getLastRowNum();
		}
		workbook.close();
		fi.close();
		return rowCount;
	}
	
	public int getCellCount(String sheet, int rowNum) throws IOException {
		fi= new FileInputStream(path);
		workbook = new XSSFWorkbook(fi);
		this.sheet= workbook.getSheet(sheet);
		row=this.sheet.getRow(rowNum);
		if(row!=null) {
			int cellCount=row.getLastCellNum();
			return cellCount;
		}else return -1;
		
	}
	
	public String getCellData(String sheet, int rowNum, int cellNum) throws IOException {
		fi=new FileInputStream(path);
		workbook= new XSSFWorkbook(fi);
		this.sheet= workbook.getSheet(sheet);
		row=this.sheet.getRow(rowNum);
		cell=row.getCell(cellNum);
		String cellData;
		
		try {
			DataFormatter formatter= new DataFormatter();
			
			cellData= formatter.formatCellValue(cell);
		}catch (Exception e) {
			cellData="";
		}
		workbook.close();
		fi.close();
		return cellData;
	}
	
	public void setCelldata(String sheet, int rowNum, int cellNum, String data) throws IOException {
		File xlfile = new File(path);
		
		if(!xlfile.exists()) {
			workbook= new XSSFWorkbook();
			fo= new FileOutputStream(path);
			workbook.write(fo);
			}
		
		fi = new FileInputStream(path);
		workbook = new XSSFWorkbook(fi);

		if(workbook.getSheetIndex(sheet)==-1) {
			workbook.createSheet(sheet);
		}
		this.sheet=workbook.getSheet(sheet);
		
		if(this.sheet.getRow(rowNum)==null){
			this.sheet.createRow(rowNum);
		}
		row=this.sheet.getRow(rowNum);
		cell=row.createCell(cellNum);
		cell.setCellValue(data);
		
		workbook.close();
		fi.close();
		fo.close();
		
	}
	
	public void fillGreenColour(String sheet, int rowNum, int colNum) throws IOException {
		fi= new FileInputStream(path);
		workbook=new XSSFWorkbook(fi);
		this.sheet= workbook.getSheet(sheet);
		
		row=this.sheet.getRow(rowNum);
		cell=row.getCell(colNum);
		
		style = workbook.createCellStyle();
		
		style.setFillForegroundColor(IndexedColors.GREEN.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		
		cell.setCellStyle(style);
		workbook.write(fo);
		workbook.close();
		fo.close();
		fi.close();
	}
	
	public void fillRedColor(String sheet, int rowNum, int colNum) throws IOException {
		fi= new FileInputStream(path);
		workbook= new XSSFWorkbook(fi);
		this.sheet=workbook.getSheet(sheet);
		
		row=this.sheet.getRow(rowNum);
		cell=row.getCell(colNum);
		
		style= workbook.createCellStyle();
		
		style.setFillForegroundColor(IndexedColors.RED.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		
		cell.setCellStyle(style);
		workbook.write(fo);
		workbook.close();
		fi.close();
		fo.close();
	}
	
	
	

}
