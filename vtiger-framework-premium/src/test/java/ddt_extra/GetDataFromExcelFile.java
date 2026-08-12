package ddt_extra;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class GetDataFromExcelFile {
	public static void main(String[] args) throws EncryptedDocumentException, IOException {
//		step 0> create a JRO of the physical file
//		FileInputStream fis = new FileInputStream("./src/test/resources/testScriptData.xlsx");

//		step 1> get the access of workbook
//		Workbook wb = WorkbookFactory.create(fis);

//		step 2> get the access of sheet
//		Sheet sh = wb.getSheet("org");
//		
//		step 3> get the access of row
//		Row row = sh.getRow(7);
//		
//		step 4> get the access of cell
//		Cell cell = row.getCell(0);
//		
//		step 5> get the value
//		String value = cell.getStringCellValue();
//		System.out.println(value);

		FileInputStream fis = new FileInputStream("./src/test/resources/testScriptData.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		String value1 = wb.getSheet("org").getRow(7).getCell(0).getStringCellValue();
		boolean value2 = wb.getSheet("org").getRow(7).getCell(1).getBooleanCellValue();
		double value3 = wb.getSheet("org").getRow(7).getCell(2).getNumericCellValue();
		System.out.println(value1 + " " + value2 + " " + value3);
		
		String value4 = wb.getSheet("org").getRow(8).getCell(0).getStringCellValue();
		String value5 = wb.getSheet("org").getRow(8).getCell(1).getStringCellValue();
		String value6 = wb.getSheet("org").getRow(8).getCell(2).getStringCellValue();
		System.out.println(value4 + " " + value5 + " " + value6);

//		do not forget to close
		wb.close();
		fis.close();

	}
}
