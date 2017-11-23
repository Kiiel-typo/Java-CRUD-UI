package techserv;

public enum Queries {

	SELECTALL ("SELECT * FROM customer"),
	ADDCUSTOMER ("INSERT INTO customer VALUES ('%s', '%s', '%s', '%s', '%s', 'active')"), 
	UPDATECUSTOMER("UPDATE customer SET custId = '%s', name = '%s', payterm = '%s', address = '%s', company = '%s' WHERE custId = '%s'"),
	DELETECUSTOMER("UPDATE customer SET stamp = 'inactive' WHERE custId = '%s'"),
	RECOVERCUSTOMER("UPDATE customer SET stamp = 'active' WHERE custId = '%s'");
	
	private String query;
	
	private Queries(String query) {
		this.query = query;
	}
	
	public String getQuery() {
		return query;
	}
	
}
