package com.feederapi.interview.test.util;

import java.io.File;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
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

	/**
	 * Read the data and return as 2D object array to data provider methods
	 * 
	 * @author PUNIT GARG
	 * @param fileName
	 * @param sheetName
	 * @return
	 * @throws IOException
	 */
	public static Object[][] ReadVariant(String sheetName) throws IOException {
		Workbook workbook = WorkbookFactory
				.create(new File(Constants.INPUT_EXCEL_FILE_PATH));
		DataFormatter formatter = new DataFormatter();
		worksheet = workbook.getSheet(sheetName);// get sheet from workbook
		Row headerRow = worksheet.getRow(0); // get Row which start from 0

		int rowCount = worksheet.getPhysicalNumberOfRows();// count number of Rows
		int colCount = headerRow.getLastCellNum(); // get last ColNum

		Object data[][] = new Object[rowCount - 1][colCount]; // pass count data in array
		int x = 0;

		for (int i = 0; i < rowCount - 1; i++) // Loop work for Rows
		{
			x = i;
			Row row = worksheet.getRow(++x);
			if (row != null) {
				for (int j = 0; j < colCount; j++) // Loop work for colNum
				{
					Cell cell = row.getCell(j);
					if (cell == null)
						data[i][j] = ""; // if it get Null value it pass no data
					else {
						String value = formatter.formatCellValue(cell);
						data[i][j] = value; // This formatter get all values as string i.e integer, float all type
											// data value
					}
				}
			}
		}

		workbook.close();
		return data;
	}

}
