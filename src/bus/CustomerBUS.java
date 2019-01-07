package bus;

import java.util.ArrayList;

import dao.CustomerDAO;
import dto.Customer;

public class CustomerBUS {
	public static ArrayList<Customer> selectCustomer() {
		ArrayList<Customer>  lsOb_c = CustomerDAO.selectCustomer();
		
		return lsOb_c;
	}
	private boolean saveCustomer(int id ,String name,String passport,String address, int id_type) {
		return CustomerDAO.saveCustomer(id, name, passport, address, id_type);
	}
}
