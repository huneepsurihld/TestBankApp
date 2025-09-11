package Util;

import java.io.FileInputStream;
import java.lang.reflect.Method;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

import Base.BaseTest;

public class DataReader extends BaseTest

{
	
	
	   @DataProvider(name="dp")
		public static Object[][] dataProvider(Method method) throws Exception
		{
			String sheetName=method.getName();
		   
		   	FileInputStream file= new FileInputStream("D:\\Document\\Default Location\\PageObjectBank\\src\\test\\resources\\Data\\DataInfo.xlsx");
			XSSFWorkbook book=new XSSFWorkbook(file);
			XSSFSheet sheet= book.getSheet(sheetName);
			
			int row=sheet.getPhysicalNumberOfRows();
			int col=sheet.getRow(0).getLastCellNum();
			
			Object[][] Data= new Object[row-1][col];
			
			for (int i=0;i<row-1;i++)
			{
				for(int j=0;j<col;j++)
				{
					XSSFCell cell=sheet.getRow(i+1).getCell(j);
					
					switch(cell.getCellType())
					{
						case STRING:
							Data[i][j]=cell.getStringCellValue();
							break;
						case NUMERIC:
							Data[i][j]= String.valueOf((long) cell.getNumericCellValue());
							break;
							
						default:
							Data[i][j]=" ";
							
					}
												
					
				}
			}
			
			return Data;
		}
	}

	
	


