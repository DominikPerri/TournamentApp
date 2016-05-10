package persistence;

import entities.Person;
import entities.Team;
import entities.Tournament;
import entities.TournamentRegistration;

public class TournamentRegistrationService extends BaseService {
	
	public TournamentRegistrationService() {
		super();
	}
	
	/**
	 * Creates a registration of a Team by a person to a tournament.
	 * @param person
	 * @param team
	 * @param tournament
	 * @return
	 */
	public TournamentRegistration persist(Person person, Team team, Tournament tournament) {
		TournamentRegistration registration;
//		Make sure entities are persisted
		TeamService teamService = new TeamService();
		PersonService personService = new PersonService();
		TournamentService tournamentService = new TournamentService();
		person = personService.persist(person);
		team = teamService.persist(team);
		tournament=tournamentService.persist(tournament);
		
		registration = new TournamentRegistration(person, team, tournament);
		super.persist(registration);
		return registration;
	}

}
