package startup;

import java.util.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import entities.Address;
import entities.Club;
import entities.Person;
import entities.Team;
import entities.Tournament;
import entities.TournamentRegistration;
import entities.Youth;
import enums.AgeLevel;
import enums.Gender;
import persistence.ClubService;
import persistence.EntityManagerFactoryService;
import persistence.PersonService;
import persistence.TeamService;
import persistence.TournamentRegistrationService;
import persistence.TournamentService;
import persistence.YouthService;

public class App {

	public static void main(String[] args) {
		
		
		ClubService clubSer = new ClubService();
		Club clubA = new Club("Testclub A");
		clubA = clubSer.persist(clubA);	
		System.out.println(clubA.getId());
		
		Club a = new Club("Testclub B");
		clubSer.persist(a);
	
		YouthService youthService = new YouthService();
		Youth am = new Youth(AgeLevel.A, Gender.MALE);
		am = youthService.persist(am);

		Youth cm = new Youth(AgeLevel.C, Gender.MALE);
		cm = youthService.persist(cm);
		
		TeamService tS = new TeamService();
		
		Team team = new Team(clubA, cm);
		team = tS.persist(team);

		PersonService ps = new PersonService();
		
		Address ad = new Address();
		ad.setCityName("Stuttgart");
		ad.setHouseNumber(10);
		ad.setStreetName("Musterstreet");
		ad.setZipCode(70582);
		
		Person person = new Person();
		person.setMailAddress("raghav@gmail.com");
		person.setFirstName("Raghav");
		person.setAddress(ad);
		
		person = ps.persist(person);
		
		Tournament tournament = new Tournament();
		TournamentService service = new TournamentService();
		tournament.setYouth(cm);
		Calendar cal = new GregorianCalendar(2016, 5, 25);
		Date date = (Date) cal.getTime();
		tournament.setDate(date);
		tournament.setOpen(true);
		service.persist(tournament);
		
		List<Tournament> tt = service.getOpenTournaments();
		
		System.out.println("*** Printing tournament list"+tt.size());
		for(int i =0;i<tt.size() ; i++) {
			System.out.println(tt.get(i).getDate());
		}
		
//		Register someone to a tournament
		TournamentRegistrationService tournamentRegService = new TournamentRegistrationService();
		tournamentRegService.persist(person, team, tournament);
		
//		Careful: After Registration is done, then the Person has to be fetched from the DB again
		Person p = ps.findPersonByMail("raghav@gmail.com");
		List<TournamentRegistration> t2 = p.getRegistered();
		System.out.println("Tournament registration (only first element):");
		System.out.println("Person: " + t2.get(0).getPerson().getMailAddress());
		System.out.println("Club/Team: " + t2.get(0).getTeam().getClub().getName());
		System.out.println("Tournament: " + t2.get(0).getTournament().getId());
		System.out.println("Date: " + t2.get(0).getRegistrationDate());

		EntityManagerFactoryService.close();

	}

}
