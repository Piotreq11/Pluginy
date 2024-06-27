package me.piotreq.snowauth.objects;

public class User {

	private boolean hasAccount;
	private boolean logged;
	private String name;
	private String password;
	private String IP;
	private int joins;
	private String UUID;
	
	public User(String pName, String password, boolean logged, boolean account, String IP, int joins, String UUID) {
		this.hasAccount = account;
		this.logged = logged;
		this.password = password;
		this.name = pName;
		this.IP = IP;
		this.joins = joins;
		this.UUID = UUID;
	}
	public void setUUID(String uuid) {
		this.UUID = uuid;
	}
	public String getUUID() {
		return UUID;
	}
	public int getJoins() {
		return joins;
	}
	public void setJoins(int joins) {
		this.joins = joins;
	}
	public String getPassword() {
		return password;
	}
    public boolean isLogged() {
        return logged;
    }

    public void setLogged(boolean logged) {
        this.logged = logged;
    }

    public void setAddress(String password) {
        this.password = password;
    }

    public void setHasAccount(boolean hasAccount) {
        this.hasAccount = hasAccount;
    }

    public boolean isHasAccount() {
        return hasAccount;
    }
    public void setPassword(String password) {
        this.password = password;
    }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	public String getIP() {
		return this.IP;
	}
	public void setIP(String l) {
		this.IP = l;
	}
}
