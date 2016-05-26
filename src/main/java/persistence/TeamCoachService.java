package persistence;

import entities.Person;
import entities.Team;
import entities.TeamCoach;

public class TeamCoachService extends BaseService {

	public TeamCoachService()  {
		super();
	}
	/**
	 * Creates a registration of a Team by a person to a tournament.
	 * @param person
	 * @param team
	 * @param tournament
	 * @return
	 */

     public TeamCoach persist(Person person, Team team) {
    	TeamCoach coach;
//		Make sure entities are persisted
		TeamService teamService = new TeamService();
		PersonService personService = new PersonService();
		person = personService.persist(person);
		team = teamService.persist(team);
		coach = new TeamCoach(person, team);
		super.persist(coach);
		return coach;
	}

}
