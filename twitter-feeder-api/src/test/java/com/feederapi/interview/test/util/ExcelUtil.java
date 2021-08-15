package com.feederapi.interview.test.util;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import com.feederapi.interview.utility.Constants;

/**
 * Excel utility class which will have all common methods for reading/writing
 * excel
 * 
 * @author PUNIT GARG
 *
 */
public class ExcelUtil {

	public static Workbook workbook;
	public static Sheet worksheet;
	static ClassLoader classLoader = ExcelUtil.class.getClassLoader();
	

	/**
	 * Read the data and return as 2D object array to data provider methods
	 * 
	 * @author PUNIT GARG
	 * @param sheetName
	 * @return
	 * @throws IOException
	 * @throws URISyntaxException 
	 */
	public static Object[][] dataSupplier(String sheetName) throws IOException, URISyntaxException {
		URL resource = classLoader.getResource(Constants.INPUT_EXCEL_FILE_NAME);
		
		workbook = WorkbookFactory.create(new File(resource.toURI()));
		worksheet = workbook.getSheet(sheetName);// get sheet from workbook
		workbook.close();
		
		DataFormatter formatter = new DataFormatter();
		
		int lastRowNum = worksheet.getLastRowNum();
		int lastCellNum = worksheet.getRow(0).getLastCellNum();

		Object data[][] = new Object[lastRowNum][1]; // pass count data in array

		for (int i = 0; i < lastRowNum; i++) // Loop work for Rows
		{
			Map<Object, Object> dataMap = new HashMap<>();
			for (int j = 0; j < lastCellNum; j++) // Loop work for colNum
			{
				Cell cell = worksheet.getRow(i + 1).getCell(j);
				String value = (cell == null) ? "" : formatter.formatCellValue(cell);
				dataMap.put(worksheet.getRow(0).getCell(j).toString(), value);
			}
			data[i][0] = dataMap;
		}

		return data;
	}

}
