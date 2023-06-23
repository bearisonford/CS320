package contactService;

//Author: Lukas Pentowski
//CS320-T5520 - Software Testing and Analysis
//May 20, 2023

public class Contact {
	
	//Attributes for class
	private String id;
	private String firstName;
	private String lastName;
	private String Number;
	private String Address;
	
	//Public Constructor for class
	public Contact(String firstName, String lastName, String Number, String Address, String id) {
		
		//Exceptions for verifying valid input for class attributes
		if(id == null || id.length()>10) {
			throw new IllegalArgumentException("Invalid id");
		}
		if(firstName == null || firstName.length()>10) {
			throw new IllegalArgumentException("Invalid first name");
		}
		if(lastName == null || lastName.length()>10) {
			throw new IllegalArgumentException("Invalid last name");
		}
		if(Number == null || Number.length()!=10) {
			throw new IllegalArgumentException("Invalid phone number");
		}
		if(Address == null || Address.length()>30) {
			throw new IllegalArgumentException("Invalid address");
		}
		
		//initializing attributes with constructor values
		this.firstName = firstName;
		this.lastName = lastName;
		this.Number = Number;
		this.Address = Address;
		this.id = id;
	}
	
	//Getters
	public String getFirstName() {
		return firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public String getNumber() {
		return Number;
	}
	
	public String getAddress() {
		return Address;
	}
	
	public String getId() {
		return id;
	}
	
	//Setters
	public void setFirstName(String first) {
		firstName = first;
	}
	
	public void setLastName(String last) {
		lastName = last;
	}
	
	public void setNumber(String num) {
		Number = num;
	}
	
	public void setAddress(String address) {
		Address = address;
	}
	
}
