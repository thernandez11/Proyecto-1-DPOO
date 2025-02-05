package Componentes;

public abstract class Usuario {

	private String login;
	private String password;
	
	//Constructor (Super)
	Usuario(String login, String password) {
		this.login = login;
		this.password = password;
	}
	
	//Getters y Setters
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
