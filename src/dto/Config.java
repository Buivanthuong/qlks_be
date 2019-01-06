package dto;

public class Config {
	private int ID;
	private int NUM_TYPE_ROOM;
	private int NUM_TYPE_CUSTOMER;
	private int NUM_CUSTOMER_IN_ROOM;
	private int NUM_SURCHARGE_CUSTOMER;
	private double SURCHARGE_CUSTOMER;
	private int NUM_SURCHARGE_CUSTOMER_TYPE;
	private String HOTEL_NAME;
	private String ADDRESS;
	
	public int getNUM_SURCHARGE_CUSTOMER() {
		return NUM_SURCHARGE_CUSTOMER;
	}
	public void setNUM_SURCHARGE_CUSTOMER(int nUM_SURCHARGE_CUSTOMER) {
		NUM_SURCHARGE_CUSTOMER = nUM_SURCHARGE_CUSTOMER;
	}
	public double getSURCHARGE_CUSTOMER() {
		return SURCHARGE_CUSTOMER;
	}
	public void setSURCHARGE_CUSTOMER(double sURCHARGE_CUSTOMER) {
		SURCHARGE_CUSTOMER = sURCHARGE_CUSTOMER;
	}
	public int getNUM_SURCHARGE_CUSTOMER_TYPE() {
		return NUM_SURCHARGE_CUSTOMER_TYPE;
	}
	public void setNUM_SURCHARGE_CUSTOMER_TYPE(int nUM_SURCHARGE_CUSTOMER_TYPE) {
		NUM_SURCHARGE_CUSTOMER_TYPE = nUM_SURCHARGE_CUSTOMER_TYPE;
	}
	public String getHOTEL_NAME() {
		return HOTEL_NAME;
	}
	public void setHOTEL_NAME(String hOTEL_NAME) {
		HOTEL_NAME = hOTEL_NAME;
	}
	public String getADDRESS() {
		return ADDRESS;
	}
	public void setADDRESS(String aDDRESS) {
		ADDRESS = aDDRESS;
	}
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public int getNUM_TYPE_ROOM() {
		return NUM_TYPE_ROOM;
	}
	public void setNUM_TYPE_ROOM(int nUM_TYPE_ROOM) {
		NUM_TYPE_ROOM = nUM_TYPE_ROOM;
	}
	public int getNUM_TYPE_CUSTOMER() {
		return NUM_TYPE_CUSTOMER;
	}
	public void setNUM_TYPE_CUSTOMER(int nUM_TYPE_CUSTOMER) {
		NUM_TYPE_CUSTOMER = nUM_TYPE_CUSTOMER;
	}
	public int getNUM_CUSTOMER_IN_ROOM() {
		return NUM_CUSTOMER_IN_ROOM;
	}
	public void setNUM_CUSTOMER_IN_ROOM(int nUM_CUSTOMER_IN_ROOM) {
		NUM_CUSTOMER_IN_ROOM = nUM_CUSTOMER_IN_ROOM;
	}
	public Config(int iD, int nUM_TYPE_ROOM, int nUM_TYPE_CUSTOMER, int nUM_CUSTOMER_IN_ROOM,
			int nUM_SURCHARGE_CUSTOMER, double sURCHARGE_CUSTOMER, int nUM_SURCHARGE_CUSTOMER_TYPE, String hOTEL_NAME,
			String aDDRESS) {
		super();
		ID = iD;
		NUM_TYPE_ROOM = nUM_TYPE_ROOM;
		NUM_TYPE_CUSTOMER = nUM_TYPE_CUSTOMER;
		NUM_CUSTOMER_IN_ROOM = nUM_CUSTOMER_IN_ROOM;
		NUM_SURCHARGE_CUSTOMER = nUM_SURCHARGE_CUSTOMER;
		SURCHARGE_CUSTOMER = sURCHARGE_CUSTOMER;
		NUM_SURCHARGE_CUSTOMER_TYPE = nUM_SURCHARGE_CUSTOMER_TYPE;
		HOTEL_NAME = hOTEL_NAME;
		ADDRESS = aDDRESS;
	}
	public Config() {
		// TODO Auto-generated constructor stub
	}
	
	
}
