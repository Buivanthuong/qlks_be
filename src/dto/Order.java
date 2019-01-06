package dto;

public class Order {
	private int ID;
	private String DATE_ORDER;
	private String ROOM_NAME;
	private int NUM_DATE;

	private int ROOM_ID;
	private int RECEIPT_ID;
	private int USER_ID;
	private int PRICE;
	private int AMOUNT;

	public String getROOM_NAME() {
		return ROOM_NAME;
	}
	public void setROOM_NAME(String rOOM_NAME) {
		ROOM_NAME = rOOM_NAME;
	}
	public int getNUM_DATE() {
		return NUM_DATE;
	}
	public void setNUM_DATE(int nUM_DATE) {
		NUM_DATE = nUM_DATE;
	}
	public int getAMOUNT() {
		return AMOUNT;
	}
	public void setAMOUNT(int aMOUNT) {
		AMOUNT = aMOUNT;
	}
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public String getDATE_ORDER() {
		return DATE_ORDER;
	}
	public void setDATE_ORDER(String dATE_ORDER) {
		DATE_ORDER = dATE_ORDER;
	}
	public int getROOM_ID() {
		return ROOM_ID;
	}
	public void setROOM_ID(int rOOM_ID) {
		ROOM_ID = rOOM_ID;
	}
	public int getRECEIPT_ID() {
		return RECEIPT_ID;
	}
	public void setRECEIPT_ID(int rECEIPT_ID) {
		RECEIPT_ID = rECEIPT_ID;
	}
	public int getUSER_ID() {
		return USER_ID;
	}
	public void setUSER_ID(int uSER_ID) {
		USER_ID = uSER_ID;
	}
	public int getPRICE() {
		return PRICE;
	}
	public void setPRICE(int pRICE) {
		PRICE = pRICE;
	}
	
	
}
