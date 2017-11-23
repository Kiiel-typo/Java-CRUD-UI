package ui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import domain.Customer;
import techserv.CustomerDA;

import javax.swing.JButton;
import java.awt.Font;
import java.awt.Color;

public class CustomerUI {

	private JFrame frame;
	private JTextField custIdTF;
	private JTextField custNameTF;
	private JTextField paytermTF;
	private JTextField addressTF;
	private JTextField companyTF;
	private CustomerDA custDA;
	private JButton btnNext;
	private JButton btnPrevious;
	private JButton btnAdd;
	private JButton btnUpdate;
	private JButton btnDelete;
	private JButton btnRecover;

	private CustListener custListener;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Theme.setLookAndFeel();
					CustomerUI window = new CustomerUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public CustomerUI() {
		custDA = new CustomerDA();
		custListener = new CustListener();
		initialize();
		updateCustomerForm();
		editableBtns(false);
	}

	private void updateCustomerForm() {
		Customer cust = custDA.getCurrentCustomer();
		
		custIdTF.setText(cust.getCustId());
		custNameTF.setText(cust.getCustName());
		paytermTF.setText(cust.getPayTerm());
		addressTF.setText(cust.getAddress());
		companyTF.setText(cust.getCompany());
	}
	
	private void editableBtns(boolean flag) {
		custIdTF.setEditable(flag);
		custNameTF.setEditable(flag);
		paytermTF.setEditable(flag);
		addressTF.setEditable(flag);
		companyTF.setEditable(flag);
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 453, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblCustomerId = new JLabel("Customer ID:");
		lblCustomerId.setBounds(58, 10, 76, 16);
		frame.getContentPane().add(lblCustomerId);
		
		custIdTF = new JTextField();
		custIdTF.setBounds(141, 7, 260, 22);
		frame.getContentPane().add(custIdTF);
		custIdTF.setColumns(10);
		custIdTF.setEditable(false);
		
		JLabel lblCustomerName = new JLabel("Customer name:");
		lblCustomerName.setBounds(38, 39, 96, 16);
		frame.getContentPane().add(lblCustomerName);
		
		custNameTF = new JTextField();
		custNameTF.setBounds(141, 36, 260, 22);
		custNameTF.setColumns(10);
		frame.getContentPane().add(custNameTF);
		
		JLabel lblPayterm = new JLabel("Payterm:");
		lblPayterm.setBounds(82, 68, 52, 16);
		frame.getContentPane().add(lblPayterm);
		
		paytermTF = new JTextField();
		paytermTF.setBounds(141, 65, 260, 22);
		paytermTF.setColumns(10);
		frame.getContentPane().add(paytermTF);
		
		JLabel lblAddress = new JLabel("Address:");
		lblAddress.setBounds(83, 97, 51, 16);
		frame.getContentPane().add(lblAddress);
		
		addressTF = new JTextField();
		addressTF.setBounds(141, 94, 260, 22);
		addressTF.setColumns(10);
		frame.getContentPane().add(addressTF);
		
		JLabel lblCompany = new JLabel("Company:");
		lblCompany.setBounds(76, 126, 58, 16);
		frame.getContentPane().add(lblCompany);
		
		companyTF = new JTextField();
		companyTF.setBounds(141, 123, 260, 22);
		companyTF.setColumns(10);
		frame.getContentPane().add(companyTF);
		
		btnNext = new JButton("Next");
		btnNext.setForeground(Color.BLUE);
		btnNext.setBounds(230, 180, 160, 25);
		btnNext.addActionListener(custListener);
		frame.getContentPane().add(btnNext);
		
		btnPrevious = new JButton("Previous");
		btnPrevious.setForeground(Color.BLUE);
		btnPrevious.setBounds(77, 180, 141, 25);
		btnPrevious.addActionListener(custListener);
		frame.getContentPane().add(btnPrevious);
		
		btnAdd = new JButton("ADD");
		btnAdd.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnAdd.setBounds(76, 218, 97, 25);
		btnAdd.addActionListener(custListener);
		frame.getContentPane().add(btnAdd);
		
		btnUpdate = new JButton("UPDATE");
		btnUpdate.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnUpdate.setBounds(185, 218, 97, 25);
		btnUpdate.addActionListener(custListener);
		frame.getContentPane().add(btnUpdate);
		
		btnDelete = new JButton("DELETE");
		btnDelete.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnDelete.setBounds(293, 218, 97, 25);
		btnDelete.addActionListener(custListener);
		frame.getContentPane().add(btnDelete);
		
		btnRecover = new JButton("RECOVER");
//		btnRecover.setBounds(338, 218, 97, 25);
//		btnRecover.addActionListener(custListener);
//		frame.getContentPane().add(btnRecover);
		
		frame.setLocationRelativeTo(null);
		
		frame.setResizable(false);
		frame.setTitle("Customer Management");
	}
	
	class CustListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent ev) {
			String ac = ev.getActionCommand();
			
			switch(ac) {
			case "Next": nextCust(); break;
			case "Previous": prevCust(); break;
			case "ADD": setEditMode("Add"); break;
			case "UPDATE": setEditMode("Update"); break;
			case "Save Add": addCust(); 
							 resetEditMode(); break;
			case "Save Update": updateCust(); 
								resetEditMode(); break;
			case "Cancel": resetEditMode(); break;
			case "DELETE": deleteCust(); break;
			case "RECOVER": recoverCust(); break;
			}
		}

		private void resetEditMode() {
			btnNext.setText("Next");
			btnPrevious.setText("Previous");
			
			editableBtns(false);
			btnAdd.setEnabled(true);
			btnUpdate.setEnabled(true);
			btnDelete.setEnabled(true);
			btnRecover.setEnabled(true);
			
			updateCustomerForm();
		}
		
		private void setEditMode(String mode) {			
			editableBtns(true);
			
			if(mode.equals("Add")) clearForm();
			else if(mode.equals("Update")) custIdTF.setEditable(false);
				
			
			btnNext.setText("Cancel");
			btnPrevious.setText("Save " + mode);
			
			btnAdd.setEnabled(false);
			btnUpdate.setEnabled(false);
			btnDelete.setEnabled(false);
			btnRecover.setEnabled(false);
		}
		
		private void clearForm() {
			custIdTF.setText("");
			custNameTF.setText("");
			paytermTF.setText("");
			addressTF.setText("");
			companyTF.setText("");
		}

		private void recoverCust() {
			int res = JOptionPane.showConfirmDialog(null, "Are you sure you want to recover customer?", "", 
					JOptionPane.YES_NO_OPTION);
			
			if(res == 0) {
				custDA.recoverCustomer(custIdTF.getText());
				updateCustomerForm();
			}
		}

		private void deleteCust() {
			int res = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete customer?", "", 
					JOptionPane.YES_NO_OPTION);
			
			if(res == 0) {
				custDA.deleteCustomer(custIdTF.getText());
				updateCustomerForm();
			}
		}

		private void updateCust() {
			custDA.updateCustomer(custIdTF.getText(),
									new Customer(
									custIdTF.getText(),
									custNameTF.getText(),
									paytermTF.getText(),
									addressTF.getText(),
									companyTF.getText())
									);
			updateCustomerForm();
		}

		private void addCust() {
			custDA.addCustomer(	new Customer(
								custIdTF.getText(),
								custNameTF.getText(),
								paytermTF.getText(),
								addressTF.getText(),
								companyTF.getText())
								);
			updateCustomerForm();
		}

		private void prevCust() {
			custDA.getPreviousCustomer();
			updateCustomerForm();
		}

		private void nextCust() {
			custDA.getNextCustomer();
			updateCustomerForm();
		}
		
	}
}
