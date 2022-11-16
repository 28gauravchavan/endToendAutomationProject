package endToendAutomationProject.utility;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.*;

public class XLutility {

	public static FileInputStream fis;
	public static FileOutputStream fos;
	public static XSSFWorkbook workbook;
	public static XSSFSheet sheet;
	public static XSSFRow row;
	public static XSSFCell cell;
	
	
	public static int getRowCount(String path, String sheetName) throws IOException
	{
		
		fis=new FileInputStream(path);
		workbook=new XSSFWorkbook(fis);
		sheet=workbook.getSheet(sheetName);
		int rowcount=sheet.getLastRowNum();
		fis.close();
		workbook.close();
		return rowcount;
		
	}
	
	public static int getcellCount(String path, String sheetName, int rownumber) throws IOException
	{
		
		fis=new FileInputStream(path);
		workbook=new XSSFWorkbook(fis);
		sheet=workbook.getSheet(sheetName);
		row=sheet.getRow(rownumber);
		int cellcount=row.getLastCellNum();
		fis.close();
		workbook.close();
		return cellcount;
		
	}
	
	public static String getcelldata(String path, String sheetName, int rownumber,int colnumber) throws IOException
	{
		fis=new FileInputStream(path);
		workbook=new XSSFWorkbook(fis);
		sheet=workbook.getSheet(sheetName);
		row=sheet.getRow(rownumber);
		cell=row.getCell(colnumber);
		
		String data;
		
		try {
			DataFormatter dataformater=new DataFormatter();
			String celldata=dataformater.formatCellValue(cell);
			return celldata;
		}catch(Exception e)
		{
			data=" ";
		}
		workbook.close();
		fis.close();
		return data;
	}
	
	
	public static void setcelldata(String path, String sheetName, int rownumber,int colnumber,String data) throws IOException
	{
		fis=new FileInputStream(path);
		workbook=new XSSFWorkbook(fis);
		sheet=workbook.getSheet(sheetName);
		row=sheet.getRow(rownumber);
		cell=row.getCell(colnumber);
		row.createCell(colnumber);
		cell.setCellValue(data);
		fos=new FileOutputStream(path);
		workbook.write(fos);
		workbook.close();
		fis.close();
		fos.close();
	}
	
	

	
}
