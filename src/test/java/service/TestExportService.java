package service;

import java.util.List;

import org.junit.Test;

import entities.Tournament;
import persistence.TournamentService;

public class TestExportService {

	@Test
	public void test() {
		TournamentService tour = new TournamentService();
		List<Tournament> tl = tour.getOpenTournaments();
		
		ExportService service = new ExportService();
		service.createExcelFile(tl);
		
	}

}
