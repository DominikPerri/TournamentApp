package startup;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import entities.Club;
import entities.Team;
import entities.Youth;
import enums.AgeLevel;
import enums.Gender;
import persistence.ClubService;
import persistence.EntityManagerService;
import persistence.TeamService;
import persistence.YouthService;

public class App {

	public static void main(String[] args) {
		
		EntityManagerFactory ems = Persistence.createEntityManagerFactory("toureg");
		
		EntityManagerFactory ems2 = Persistence.createEntityManagerFactory("toureg");
		
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

		EntityManagerService.close();

	}

}
