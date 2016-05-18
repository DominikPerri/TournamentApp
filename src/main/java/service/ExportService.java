package service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;


import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import entities.Tournament;

public class ExportService {

	public ExportService() {
	}

	/**
	 * Converts the given tournaments to a readable csv-file
	 * @param tournaments
	 * @return
	 */
	public File createExcelFile(List<Tournament> tournaments) {
		

//		Below is the complete code that writes a new excel with dummy data:
		@SuppressWarnings("resource")
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet("Tournaments");
		
//		Create Header
		Row row = sheet.createRow(0);
		Cell cell = row.createCell(0);
		cell.setCellValue("Tournaments");
		
		int rownumber = 2;
		
		for (Tournament t : tournaments) {
			row = sheet.createRow(rownumber);
			cell = row.createCell(0);
			cell.setCellValue(t.getYouth().toString());
			rownumber++;
		}
		 
		try {
		    FileOutputStream out =  new FileOutputStream(new File(System.getProperty("user.home"), "new.xls"));
		    workbook.write(out);
		    out.close();
		    System.out.println("Excel written successfully..");
		     
		} catch (FileNotFoundException e) {
		    e.printStackTrace();
		} catch (IOException e) {
		    e.printStackTrace();
		}
		
		
		
		
		
		
		return null;
	}

}
