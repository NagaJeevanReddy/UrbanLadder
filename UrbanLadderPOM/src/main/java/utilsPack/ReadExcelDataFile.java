package utilsPack;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadExcelDataFile {

	public String path;
	public FileInputStream fis = null;
	public FileOutputStream fileOut = null;
	public XSSFWorkbook workbook = null;
	public XSSFSheet sheet = null;
	public XSSFRow row = null;
	public XSSFCell cell = null;

	// Used to read data from excel
	public ReadExcelDataFile(String path) {

		this.path = path;

	}

	// It is used to write data in excel
	public boolean setCellData(String sheetName, int colNum, int rowNum, String data) {
		try {
			fis = new FileInputStream(path);
			workbook = new XSSFWorkbook(fis);

			int index = workbook.getSheetIndex(sheetName);

			sheet = workbook.getSheetAt(index);

			row = sheet.getRow(rowNum);
			
			cell = row.getCell(colNum);
			
			cell.setCellValue(data);

			fileOut = new FileOutputStream(path);
			workbook.write(fileOut);

			fileOut.close();

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	//It is used to read data from excel
	public String getCellData(String sheetName, int colNum, int rowNum) {
		String data=null;
		try {
			fis = new FileInputStream(path);
			workbook = new XSSFWorkbook(fis);

			int index = workbook.getSheetIndex(sheetName);

			sheet = workbook.getSheetAt(index);

			row = sheet.getRow(rowNum);

			cell = row.getCell(colNum);
			
			data=cell.getStringCellValue();

			

		} catch (Exception e) {
			e.printStackTrace();
			
		}
		return data;
		
	}


}