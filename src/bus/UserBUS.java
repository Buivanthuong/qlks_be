package bus;

import java.util.ArrayList;

import dao.UserDAO;
import dto.Config;
import dto.User;
import utill.Utill;

public class UserBUS {
	public static boolean LoginUser(String user_name, String password){
		User ob = UserDAO.LoginUser(user_name, password);
		if(ob != null) {
			if(Utill.SaveUser(ob)){
				return true;
			}
		}
        return false;
	}
	public static boolean UpdateUser(int id ,String name,String full_name, String password){
		
		if(UserDAO.UpdateUser(id, name, full_name, password)) {
			return true;
		}
		
		return false;
	}
	public static boolean InsertUser(String name,String full_name, String password){
		if(UserDAO.InsertUser( name, full_name, password)  > 0) {
			return true;
		}
		
		return false;
	}	
	public static boolean InsertConfigUser(String nUM_TYPE_ROOM, String nUM_TYPE_CUSTOMER, String nUM_CUSTOMER_IN_ROOM,
			String nUM_SURCHARGE_CUSTOMER, String sURCHARGE_CUSTOMER, String nUM_SURCHARGE_CUSTOMER_TYPE, String hOTEL_NAME,
			String aDDRESS) {
		 try {
			Config ob = new Config();
			ob.setADDRESS(aDDRESS);
			ob.setHOTEL_NAME(hOTEL_NAME);
			ob.setNUM_CUSTOMER_IN_ROOM(Integer.parseInt(nUM_CUSTOMER_IN_ROOM));
			ob.setNUM_SURCHARGE_CUSTOMER(Integer.parseInt(nUM_SURCHARGE_CUSTOMER));
			ob.setNUM_SURCHARGE_CUSTOMER_TYPE(Integer.parseInt(nUM_SURCHARGE_CUSTOMER_TYPE));
			ob.setNUM_TYPE_CUSTOMER(Integer.parseInt(nUM_TYPE_CUSTOMER));
			ob.setNUM_TYPE_ROOM(Integer.parseInt(nUM_TYPE_ROOM));
			ob.setSURCHARGE_CUSTOMER(Integer.parseInt(sURCHARGE_CUSTOMER));
			
			return UserDAO.UpdateConfigUser(ob);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return false;
	}
	public static Config selectConfig() {
		return UserDAO.selectConfig();
	}
	public static boolean deleteUser(int id) {
		return UserDAO.deleteUser(id);
	}
	public static ArrayList<User> selectUser(){
		return UserDAO.selectUser();
	}
}
