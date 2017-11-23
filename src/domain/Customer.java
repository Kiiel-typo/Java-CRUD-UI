package domain;

public class Customer {

	private String custId,
				   studName,
				   payTerm,
				   company,
				   address;

	public Customer(String custId, String studName, String payTerm, String address, String company) {
		this.custId = custId;
		this.studName = studName;
		this.payTerm = payTerm;
		this.address = address;
		this.company = company;
	}

	public String getCustId() {
		return custId;
	}

	public void setCustId(String custId) {
		this.custId = custId;
	}

	public String getCustName() {
		return studName;
	}

	public void setCustName(String custName) {
		this.studName = custName;
	}

	public String getPayTerm() {
		return payTerm;
	}

	public void setPayTerm(String payTerm) {
		this.payTerm = payTerm;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
}
