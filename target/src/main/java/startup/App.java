package startup;

import java.util.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import entities.Club;
import entities.Person;
import entities.Team;
import entities.Tournament;
import entities.Youth;
import enums.AgeLevel;
import enums.Gender;
import persistence.ClubService;
import persistence.EntityManagerService;
import persistence.PersonService;
import persistence.TeamService;
import persistence.TournamentService;
import persistence.YouthService;

public class App {

	public static void main(String[] args) {
		ClubService clubSer = new ClubService();
		Club c = clubSer.persist(new Club("VfB Stuttgart 3"));
		
		YouthService youthService = new YouthService();
		Youth am = new Youth(AgeLevel.A, Gender.MALE);
		am = youthService.persist(am);

		Youth cm = new Youth(AgeLevel.C, Gender.MALE);
		cm = youthService.persist(cm);
		
		TeamService tS = new TeamService();
		
		Team team = new Team(c, cm);
		team = tS.persist(team);

		PersonService ps = new PersonService();
		
		Person person = new Person();
		person.setMailAddress("raghav@gmail.com");
		person.setFirstName("Raghav");
		
		person = ps.persist(person);
		
		Tournament tournament = new Tournament();
		TournamentService service = new TournamentService();
		tournament.setYouth(cm);
		Calendar cal = new GregorianCalendar(2016, 5, 25);
		Date date = (Date) cal.getTime();
		tournament.setDate(date);
		tournament.setOpen(true);
		service.persist(tournament);
		
		List<Tournament> tt = service.getopenTournaments();
		
		System.out.println("*** Printing tournament list"+tt.size());
		for(int i =0;i<tt.size() ; i++) {
			System.out.println(tt.get(i).getDate());
		}
			
		EntityManagerService.close();
		
		

	}

}
