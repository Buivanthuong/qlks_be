package dto;

public class Customer {
	private int ID;
	private String PASSPORT;
	private String NAME;
	private String TYPE;
	private double SURCHARGE;
	private String ADDRESS;
	private int TYPE_CUSTOMER_ID;
	  
	  
	public String getNAME() {
		return NAME;
	}
	public void setNAME(String nAME) {
		NAME = nAME;
	}
	public String getTYPE() {
		return TYPE;
	}
	public void setTYPE(String tYPE) {
		TYPE = tYPE;
	}
	public double getSURCHARGE() {
		return SURCHARGE;
	}
	public void setSURCHARGE(double sURCHARGE) {
		SURCHARGE = sURCHARGE;
	}
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public String getPASSPORT() {
		return PASSPORT;
	}
	public void setPASSPORT(String pASSPORT) {
		PASSPORT = pASSPORT;
	}
	public String getADDRESS() {
		return ADDRESS;
	}
	public void setADDRESS(String aDDRESS) {
		ADDRESS = aDDRESS;
	}
	public int getTYPE_CUSTOMER_ID() {
		return TYPE_CUSTOMER_ID;
	}
	public void setTYPE_CUSTOMER_ID(int tYPE_CUSTOMER_ID) {
		TYPE_CUSTOMER_ID = tYPE_CUSTOMER_ID;
	}
	  
}
