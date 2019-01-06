package utill;

import java.text.DecimalFormat;
import java.util.prefs.Preferences;
import java.text.Normalizer;
import java.util.regex.Pattern;

import dto.User;
public class Utill {
	final static String USER_ID = "user_id";
	final static String USER_NAME = "user_name";
	final static String FULL_NAME = "full_name";
	final static String SAVE_LOGIN = "save_login";

	public static boolean SaveUser(User user) {
		// Retrieve the user preference node for the package com.mycompany
		Preferences prefs = Preferences.userNodeForPackage(utill.Utill.class);

		// Set the value of the preference
		prefs.putInt(USER_ID, user.getID());
		prefs.put(USER_NAME, user.getUSER_NAME());
		prefs.put(FULL_NAME, user.getFULL_NAME());

		// Get the value of the preference;
		// default value is returned if the preference does not exist
		int defaultValue = -1;
		int user_id = prefs.getInt(USER_ID, defaultValue); // "a string"
		
		if(user_id >= 0) {
			return true;
		}
		return false;
	}
	public static User GetSaveUser() {
		// Retrieve the user preference node for the package com.mycompany
		Preferences prefs = Preferences.userNodeForPackage(utill.Utill.class);
		User user = new User();

		// Get the value of the preference;
		// default value is returned if the preference does not exist
		int defaultValue = -1;
		user.setID(prefs.getInt(USER_ID, defaultValue));
		user.setUSER_NAME(prefs.get(USER_NAME, ""));
		user.setFULL_NAME(prefs.get(FULL_NAME, ""));

		return user;
	}
	public static boolean SaveLogin(boolean save_login) {
		// Retrieve the user preference node for the package com.mycompany
		Preferences prefs = Preferences.userNodeForPackage(utill.Utill.class);

		// Set the value of the preference
		prefs.putBoolean(SAVE_LOGIN, save_login);
		// Get the value of the preference;
		// default value is returned if the preference does not exist
		boolean defaultValue = false;
		defaultValue = prefs.getBoolean(SAVE_LOGIN, defaultValue); // "a string"
		
		return defaultValue;
	}
	public static boolean GetSaveLogin() {
		// Retrieve the user preference node for the package com.mycompany
		Preferences prefs = Preferences.userNodeForPackage(utill.Utill.class);

		// Get the value of the preference;
		// default value is returned if the preference does not exist
		boolean defaultValue = false;
		defaultValue = prefs.getBoolean(SAVE_LOGIN, defaultValue); 
		if(defaultValue && prefs.getInt(USER_ID, -1) >= 0) {
			return true;
		}
		return false;
	}
    public static String formatCurrency(int amount) {
        DecimalFormat formatter = new DecimalFormat("###,###,###");
        return formatter.format(amount);
    }
    public static String ConvertUnicode(String s) {
    	String ss = "";
    	for (int i = 0; i < s.length(); i++) {
    	    char c = s.charAt(i);
    	    if (c > 31 && c < 127)
    	    	ss +=(String.valueOf(c));
    	    else
    	    	ss +=(String.format("\\u%04x", (int)c));
    	}
		return ss;
	}
    public static String removeAccent(String s) {
    	  
    	  String temp = Normalizer.normalize(s, Normalizer.Form.NFD);
    	  Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
    	  return pattern.matcher(temp).replaceAll("");
    	 }
    
}
