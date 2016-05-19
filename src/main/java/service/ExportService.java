package service;

import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.Row;

import entities.Address;
import entities.Club;
import entities.Person;
import entities.Team;
import entities.Tournament;
import entities.TournamentRegistration;
import entities.Youth;

public class ExportService {

	private HSSFWorkbook workbook;

	public ExportService() {
	}

	/**
	 * Converts the given tournaments to a readable csv-file
	 * 
	 * @param tournaments
	 * @return
	 */
	public HSSFWorkbook createExcelFile(List<Tournament> tournaments) {

		// Create a new workbook
		workbook = new HSSFWorkbook();
		// Create a overview sheet
		createOverviewSheet(tournaments);

		for (Tournament t : tournaments) {
			createTournamentSheet(t);
		}

		return workbook;
	}

	private void createTournamentSheet(Tournament tournament) {

		Date date = tournament.getDate();
		Youth youth = tournament.getYouth();
		List<TournamentRegistration> registrations = tournament.getRegistered();

		String sheetname = date.toString() + " - " + youth.toString();

		HSSFSheet sheet = workbook.createSheet(sheetname);
		Row row;
		Cell cell;

		row = sheet.createRow(0);

		cell = row.createCell(0);
		cell.setCellValue(youth.toString());

		cell = row.createCell(1);
		cell = setDateFormat(cell);
		cell.setCellValue(date);

		cell = row.createCell(2);
		cell.setCellValue("Current Registrations");
		cell = setBoldCellStyle(cell);

		cell = row.createCell(3);
		cell.setCellValue(registrations.size());
		cell = setBoldCellStyle(cell);

		row = sheet.createRow(2);
		cell = row.createCell(0);
		cell = setBoldCellStyle(cell);
		cell.setCellValue("Registration informations");

		row = sheet.createRow(3);
		cell = row.createCell(0);
		cell = setBoldCellStyle(cell);
		cell.setCellValue("Registration date");

		cell = row.createCell(1);
		cell = setBoldCellStyle(cell);
		cell.setCellValue("Club");

		cell = row.createCell(2);
		cell = setBoldCellStyle(cell);
		cell.setCellValue("First name");

		cell = row.createCell(3);
		cell = setBoldCellStyle(cell);
		cell.setCellValue("Last name");

		cell = row.createCell(4);
		cell = setBoldCellStyle(cell);
		cell.setCellValue("Mail address");

		cell = row.createCell(5);
		cell = setBoldCellStyle(cell);
		cell.setCellValue("Phone number");

		cell = row.createCell(6);
		cell = setBoldCellStyle(cell);
		cell.setCellValue("Street");

		cell = row.createCell(7);
		cell = setBoldCellStyle(cell);
		cell.setCellValue("zip code");

		cell = row.createCell(8);
		cell = setBoldCellStyle(cell);
		cell.setCellValue("City");

		int rownumber = 4;

		for (TournamentRegistration reg : registrations) {
			Person person = reg.getPerson();
			Date registrationDate = reg.getRegistrationDate();
			Team team = reg.getTeam();
			Club club = team.getClub();
			Address personAddress = person.getAddress();

			row = sheet.createRow(rownumber);

			cell = row.createCell(0);
			cell = setDateFormat(cell);
			cell.setCellValue(registrationDate);

			cell = row.createCell(1);
			cell.setCellValue(club.getName());

			cell = row.createCell(2);
			cell.setCellValue(person.getFirstName());

			cell = row.createCell(3);
			cell.setCellValue(person.getLastName());

			cell = row.createCell(4);
			cell.setCellValue(person.getMailAddress());

			cell = row.createCell(5);
			cell.setCellValue(person.getPhoneNumber());

			cell = row.createCell(6);
			String streetAndNumber = personAddress.getStreetName() + " " + personAddress.getHouseNumber();
			cell.setCellValue(streetAndNumber);

			cell = row.createCell(7);
			cell.setCellValue(personAddress.getZipCode());

			cell = row.createCell(8);
			cell.setCellValue(personAddress.getCityName());

			rownumber++;
		}
		
		autosizeSheet(sheet);

	}

	private void createOverviewSheet(List<Tournament> tournaments) {
		HSSFSheet sheet = workbook.createSheet("Overview");
		Row row;
		Cell cell;

		row = sheet.createRow(0);
		cell = row.createCell(0);
		cell.setCellValue("Tournaments");

		row = sheet.createRow(2);

		cell = row.createCell(0);
		cell.setCellValue("Date");
		cell = setBoldCellStyle(cell);

		cell = row.createCell(1);
		cell.setCellValue("Youth");
		cell = setBoldCellStyle(cell);

		cell = row.createCell(2);
		cell.setCellValue("Current Registrations");
		cell = setBoldCellStyle(cell);

		int rownumber = 3;

		for (Tournament t : tournaments) {
			Date date = t.getDate();
			Youth youth = t.getYouth();
			int registeredTeams = t.getRegistered().size();

			row = sheet.createRow(rownumber);
			cell = row.createCell(0);

			cell = setDateFormat(cell);
			cell.setCellValue(date);

			cell = row.createCell(1);
			cell.setCellValue(youth.toString());

			cell = row.createCell(2);
			cell.setCellValue(registeredTeams);

			rownumber++;
		}

		autosizeSheet(sheet);

	}

	private Cell setBoldCellStyle(Cell cell) {
		HSSFCellStyle style = workbook.createCellStyle();
		HSSFFont font = workbook.createFont();
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		style.setFont(font);
		cell.setCellStyle(style);
		return cell;
	}

	private Cell setDateFormat(Cell cell) {
		CellStyle cellStyle = workbook.createCellStyle();
		CreationHelper createHelper = workbook.getCreationHelper();
		DataFormat dataFormat = createHelper.createDataFormat();
		short format = dataFormat.getFormat("m/d/yy");
		cellStyle.setDataFormat(format);
		cell.setCellStyle(cellStyle);
		return cell;
	}

	private void autosizeSheet(HSSFSheet sheet) {
		for (int i = 0; i < 50; i++) {
			sheet.autoSizeColumn(i);
		}
	}

}
