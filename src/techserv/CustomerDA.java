package techserv;

import java.sql.*;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import domain.Customer;

public class CustomerDA {

	private ArrayList<Customer> customerList;
	private int recordCursor;
	
	private Connection connection;
	private final String dbDir = "..\\Battery Exam\\BattExam2.db";
	
	public CustomerDA() {
		setRecordCursorAtStart();
		setConnection();
		
		populateCustomerList();
	}

	public void addCustomer(Customer cust) {
		commitStatement(String.format(Queries.ADDCUSTOMER.getQuery(),
									  cust.getCustId(),
									  cust.getCustName(),
									  cust.getPayTerm(),
									  cust.getAddress(),
									  cust.getCompany()));
		
		setRecordCursorAtEnd();
	}
	
	public void updateCustomer(String oldCustId, Customer cust) {
		commitStatement(String.format(Queries.UPDATECUSTOMER.getQuery(),
									  cust.getCustId(),
									  cust.getCustName(),
									  cust.getPayTerm(),
									  cust.getAddress(),
									  cust.getCompany(),
									  oldCustId));
	}
	
	public void deleteCustomer(String custId) {
		commitStatement(String.format(Queries.DELETECUSTOMER.getQuery(), custId));
		setRecordCursorAtStart();
	}
	
	public void recoverCustomer(String custId) {
		commitStatement(String.format(Queries.RECOVERCUSTOMER.getQuery(), custId));
		setRecordCursorAtStart();
	}
	
	private void commitStatement(String query) {
		try {			
			PreparedStatement ps = connection.prepareStatement(query);
			ps.executeUpdate();
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "An error occured due invalid/conflict in customer data inputted.", "", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}		
		
		populateCustomerList();
	}

	public Customer getCurrentCustomer() {
		return customerList.get(recordCursor);
	}
	
	public void setRecordCursorAtStart() {
		recordCursor = 0;
	}
	
	public void setRecordCursorAtEnd() {
		recordCursor = customerList.size()-1;
	}
	
	public Customer getNextCustomer() {
		return customerList.get(
				recordCursor = recordCursor >= customerList.size()-1? customerList.size()-1:++recordCursor); 
	}
	
	public Customer getPreviousCustomer() {
		return customerList.get(
				recordCursor = recordCursor <= 0? 0:--recordCursor); 
	}
	
	private void populateCustomerList() {
		customerList = new ArrayList<Customer>();
		
		try {			
			PreparedStatement ps = connection.prepareStatement(Queries.SELECTALL.getQuery());
			ResultSet rs = ps.executeQuery();
			
			Customer cust;
			while(rs.next()) {
				if(rs.getString("stamp").contains("inactive"))
					continue;
				
				cust = new Customer(rs.getString("custId"),
								   	rs.getString("name"),
								   	rs.getString("payterm"),
								   	rs.getString("address"),
								   	rs.getString("company"));
				
				customerList.add(cust);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public ArrayList<Customer> getCustomerList() {
		return customerList;
	}
	
	private void setConnection() {
		try {
			Class.forName("org.sqlite.JDBC");
			connection = DriverManager.getConnection("jdbc:sqlite:" + dbDir);
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
