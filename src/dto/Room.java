package dto;

public class Room {
	private int ID;
	private int PRICE;
	private String TYPE;
	private int STATUS_ROOM;
	private String NAME;
	private String NOTE;
	private int TYPE_ROOM_ID;
	
	
	public int getSTATUS_ROOM() {
		return STATUS_ROOM;
	}
	public void setSTATUS_ROOM(int sTATUS_ROOM) {
		STATUS_ROOM = sTATUS_ROOM;
	}
	public int getPRICE() {
		return PRICE;
	}
	public void setPRICE(int pRICE) {
		PRICE = pRICE;
	}
	public String getTYPE() {
		return TYPE;
	}
	public void setTYPE(String tYPE) {
		TYPE = tYPE;
	}
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public String getNAME() {
		return NAME;
	}
	public void setNAME(String nAME) {
		NAME = nAME;
	}
	public String getNOTE() {
		return NOTE;
	}
	public void setNOTE(String nOTE) {
		NOTE = nOTE;
	}
	public int getTYPE_ROOM_ID() {
		return TYPE_ROOM_ID;
	}
	public void setTYPE_ROOM_ID(int tYPE_ROOM_ID) {
		TYPE_ROOM_ID = tYPE_ROOM_ID;
	}
	public Room() {
		super();
	}
	
	
}
