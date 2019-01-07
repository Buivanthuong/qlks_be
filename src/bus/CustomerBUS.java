package bus;

import java.util.ArrayList;

import dao.CustomerDAO;
import dto.Customer;
import dto.TypeCustomer;

public class CustomerBUS {
	public static ArrayList<Customer> selectCustomer() {
		ArrayList<Customer>  lsOb_c = CustomerDAO.selectCustomer();
		
		return lsOb_c;
	}
	public static boolean saveCustomer(int id ,String name,String passport,String address, int id_type) {
		return CustomerDAO.saveCustomer(id, name, passport, address, id_type);
	}
	public static ArrayList<TypeCustomer> selectTypeCustomer() {
		return CustomerDAO.selectTypeCustomer();
	}
	public static boolean deleteCustomerType(int id)  {
		return CustomerDAO.deleteCustomerType(id);
	}
	public static boolean saveCustomerType(int id ,String name, String surcharge) {
		return CustomerDAO.saveCustomerType(id, name, surcharge);
	}
}
