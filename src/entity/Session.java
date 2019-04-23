package entity;

public class Session extends Personne {
	String login;
	String mdp;
	
	public Session(String login, String mdp) {
		super();
		this.login = login;
		this.mdp = mdp;
	}
	
	public String getLogin() {
		return login;
	}
	
	public void setLogin(String login) {
		this.login = login;
	}
	
	public String getMdp() {
		return mdp;
	}
	
	public void setMdp(String mdp) {
		this.mdp = mdp;
	}

	public Session() {
		super();
	}
}
