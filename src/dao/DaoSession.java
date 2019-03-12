package dao;

import entity.Personne;

public interface DaoSession {
	
	public void inscription(Personne personne);
	public void seConnecter(String login, String mdp);
	public void changerMdp(String login, String mdp, String mail);
}
