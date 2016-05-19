package service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.junit.Test;

import entities.Tournament;
import persistence.TournamentService;

public class TestExportService {

	@Test
	public void test() {
		TournamentService tour = new TournamentService();
		List<Tournament> tl = tour.getOpenTournaments();
		
		ExportService service = new ExportService();
		HSSFWorkbook workbook = service.createExcelFile(tl);
		
		
		
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
		
		
		
		
	}

}
