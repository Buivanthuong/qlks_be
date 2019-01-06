package dto;

public class User {
	private int ID;
	private String USER_NAME;
	private String PASSWORD;
	private String FULL_NAME;
	
	
	public String getFULL_NAME() {
		return FULL_NAME;
	}
	public void setFULL_NAME(String fULL_NAME) {
		FULL_NAME = fULL_NAME;
	}
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public String getUSER_NAME() {
		return USER_NAME;
	}
	public void setUSER_NAME(String uSER_NAME) {
		USER_NAME = uSER_NAME;
	}
	public String getPASSWORD() {
		return PASSWORD;
	}
	public void setPASSWORD(String pASSWORD) {
		PASSWORD = pASSWORD;
	}
	public User() {
		ID = -1;
		USER_NAME ="";
		FULL_NAME = "";
	}
	
}
