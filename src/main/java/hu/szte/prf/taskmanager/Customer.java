package hu.szte.prf.taskmanager;

import org.json.JSONObject;

public class Customer {
	private long id;
	private String firstName, lastName;

	public Customer(long id, String firstName, String lastName) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
	}

/*	@Override
	public String toString() {
		JSONObject jsonObject = new JSONObject(this);
		return jsonObject.toString();
	}*/

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Override
	public boolean equals(Object obj) {
		return this.id == ((Customer) obj).getId();
	}
	
	@Override
	public int hashCode() {	
		return super.hashCode();
	}

}