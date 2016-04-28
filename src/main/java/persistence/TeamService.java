package persistence;

import java.util.List;

import javax.persistence.Query;

import entities.Team;


public class TeamService extends BaseService{
	
	public TeamService(){
		super();
	}

	/**
	 * Persist a new team in the database. If the team already exists then
	 * return this entity from the database
	 * 
	 * @param team
	 * @return persisted team
	 */
	public Team persist(Team team) {
		super.setup();
		super.openConnection();
		Query query = em.createNamedQuery(Team.findTeam);
		query.setParameter("club", team.getClub().getId());
		query.setParameter("youth", team.getYouth().getId());
		
		@SuppressWarnings("unchecked")
		List<Team> teamList = query.getResultList();
		
		if (teamList.size()==0){
			super.persist(team);
		} else {
			team = teamList.get(0);
		}
		closeEntityManager();
		return team;
	}

}
