package endToendAutomationProject.utility;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Iterator;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class excel1 {
	
	
	FileInputStream fis;
	FileOutputStream fos;
	XSSFWorkbook workbook;
	XSSFSheet sheet;
	XSSFRow Row ;
	XSSFCell cell;
	String path="";
    int rowcount;
	HashMap<String,Integer> colnamemap;
	
	public excel1(String xlpath)
	{
		try {
			path=xlpath;
		fis=new FileInputStream(path);
		workbook=new XSSFWorkbook(fis);
		
		}
		catch(Exception e)
		{e.printStackTrace();
		
		}		
	}
	
	
	
	public void setsheet(String sheetName)
	{
		sheet=workbook.getSheet(sheetName);
		populatecolname();
		rowcount=sheet.getLastRowNum();
	}
	
	public int getrowcount(){
		return rowcount+1;
	}
	
	public void populatecolname()
	{
	
		colnamemap=new HashMap<String,Integer>();
		int colindex=0;
		Row=sheet.getRow(0);
		Iterator<Cell> cells = Row.cellIterator();
		
		while(cells.hasNext())
		{
			Cell cell1 = cells.next(); 
		    String colName=cell1.getStringCellValue();	
		    colnamemap.put(colName, colindex);
		    colindex++;
		}
	}
	
	public int getcolnumber(String colName)
	{
		return colnamemap.get(colName);
	}
	public String getcelldata(int rowNumber, String colName)
	{
		String ret="";
		int colnum=getcolnumber(colName);
		ret=getcelldata(rowNumber,colnum);
		
		return ret;
		
	}
	
	
	public String getcelldata(int rowNumber, int colNumber)
	{
		String ret=" ";
		try
		{
			 Row = sheet.getRow(rowNumber);
			 cell = Row.getCell(colNumber);
			 
			 if(cell.getCellType()==CellType.STRING) {
				 ret=" "+cell.getStringCellValue();
			 }
			 else if(cell.getCellType()==CellType.NUMERIC)
			 {
				 ret=" "+cell.getNumericCellValue();
			 }
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
	 return ret;
	}
	
	public void readsheetdata()
	{
		Iterator<Row> rows = sheet.iterator();
		while(rows.hasNext())
		{
			Row currntrow = rows.next();
			Iterator<Cell> cells = currntrow.cellIterator();
		
			while(cells.hasNext())
			{
				Cell cell = cells.next();
				CellType ctype = cell.getCellType();
			    String value="";	
				if(ctype==CellType.NUMERIC)
				{
					value= " "+cell.getNumericCellValue();
				}
				else if(ctype==CellType.STRING)
				{
					value=""+cell.getStringCellValue();
				}
				
		System.out.print(value+" || ");
			}
			System.out.println();
		}
		
	}
	
	
	
		
}
