

import org.junit.Test;

import entities.Club;
import entities.Team;
import entities.Youth;
import enums.AgeLevel;
import enums.Gender;
import persistence.ClubService;
import persistence.EntityManagerService;
import persistence.TeamService;
import persistence.YouthService;

public class DataGeneration {

	private Youth df;
	private Youth ef;
	private Youth bm;
	private Youth em;

	private Club vfb;
	private Club liv;

	@Test
	public void test() {
		DataGeneration dg = new DataGeneration();
		dg.createYouth();
		dg.createClubs();
		dg.createTeams();

		EntityManagerService.close();
	}

	private void createTeams() {

		TeamService tS = new TeamService();

		Team team = new Team(vfb, bm);
		tS.persist(team);

		team = new Team(vfb, df);
		tS.persist(team);

		team = new Team(vfb, em);
		tS.persist(team);

		team = new Team(vfb, ef);
		tS.persist(team);

		team = new Team(liv, bm);
		tS.persist(team);

		team = new Team(liv, em);
		tS.persist(team);

		team = new Team(vfb, bm);
		tS.persist(team);

	}

	private void createClubs() {
		ClubService service = new ClubService();

		vfb = new Club("VfB Stuttgart");
		vfb = service.persist(vfb);

		Club club = new Club("Bayern Munich");
		service.persist(club);

		liv = new Club("Liverpool FC");
		liv = service.persist(liv);

		club = new Club("Aresenal London");
		service.persist(club);

		club = new Club("Stuttgarter Kickers");
		service.persist(club);

		club = new Club("SSV Ulm");
		service.persist(club);

		club = new Club("Borussia Dortmund");
		service.persist(club);

		club = new Club("VfL Wolfsburg");
		service.persist(club);
	}

	private void createYouth() {
		YouthService youthService = new YouthService();
		Youth am = new Youth(AgeLevel.A, Gender.MALE);
		youthService.persist(am);

		bm = new Youth(AgeLevel.B, Gender.MALE);
		bm = youthService.persist(bm);

		Youth cm = new Youth(AgeLevel.C, Gender.MALE);
		youthService.persist(cm);

		Youth dm = new Youth(AgeLevel.D, Gender.MALE);
		youthService.persist(dm);

		em = new Youth(AgeLevel.E, Gender.MALE);
		em = youthService.persist(em);

		Youth fm = new Youth(AgeLevel.F, Gender.MALE);
		youthService.persist(fm);

		Youth gm = new Youth(AgeLevel.G, Gender.MALE);
		youthService.persist(gm);

		Youth af = new Youth(AgeLevel.A, Gender.FEMALE);
		youthService.persist(af);

		Youth bf = new Youth(AgeLevel.B, Gender.FEMALE);
		youthService.persist(bf);

		Youth cf = new Youth(AgeLevel.C, Gender.FEMALE);
		youthService.persist(cf);

		df = new Youth(AgeLevel.D, Gender.FEMALE);
		df = youthService.persist(df);

		ef = new Youth(AgeLevel.E, Gender.FEMALE);
		ef = youthService.persist(ef);

	}

}
