package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import contactService.ContactService;

//Author: Lukas Pentowski
//CS320-T5520 - Software Testing and Analysis
//May 20, 2023

class ContactServiceTest {
	private String id;
	private String firstName;
	private String lastName;
	private String Number;
	private String Address;
	
	
	
	
	@BeforeEach
	//Establishing the base values of the different variables for ease of use.
	void setUp(){
		firstName = "John";
		lastName = "Doe";
		Number = "5555551212";
		Address = "123 Main St, Anytown AS";
	}

	//Testing that adding a contact properly creates a contact in the arraylist
	@Test
	void testAddContact() {
		ContactService contact = new ContactService();
		contact.addContact(firstName, lastName, Number, Address);
		assertTrue(contact.getContacts().get(0).getFirstName().equals("John"));
		assertTrue(contact.getContacts().get(0).getLastName().equals("Doe"));
		assertTrue(contact.getContacts().get(0).getNumber().equals("5555551212"));
		assertTrue(contact.getContacts().get(0).getAddress().equals("123 Main St, Anytown AS"));
	}
	
	//Testing that the Contact exceptions are functional with the arraylist
	@Test
	void testContactFirstNameLong() {
		ContactService contact = new ContactService();
		Assertions.assertThrows(IllegalArgumentException.class, ()-> {
			contact.addContact("Johnnnnnnnn", lastName, Number, Address);
		});
	}
	
	@Test
	void testContactFirstNameNull() {
		ContactService contact = new ContactService();
		Assertions.assertThrows(IllegalArgumentException.class, ()-> {
			contact.addContact(null, lastName, Number, Address);
		});
	}
	
	@Test
	void testContactLastNameLong() {
		ContactService contact = new ContactService();
		Assertions.assertThrows(IllegalArgumentException.class, ()-> {
			contact.addContact(firstName, "Doeeeeeeeee", Number, Address);
		});
	}
	
	@Test
	void testContactLastNameNull() {
		ContactService contact = new ContactService();
		Assertions.assertThrows(IllegalArgumentException.class, ()-> {
			contact.addContact(firstName, null, Number, Address);
		});
	}
	
	@Test
	void testContactNumberLong() {
		ContactService contact = new ContactService();
		Assertions.assertThrows(IllegalArgumentException.class, ()-> {
			contact.addContact(firstName, lastName, "555555121212", Address);
		});
	}
	
	@Test
	void testContactNumberNull() {
		ContactService contact = new ContactService();
		Assertions.assertThrows(IllegalArgumentException.class, ()-> {
			contact.addContact(firstName, lastName, null, Address);
		});
	}
	
	@Test
	void testContactAddressLong() {
		ContactService contact = new ContactService();
		Assertions.assertThrows(IllegalArgumentException.class, ()-> {
			contact.addContact(firstName, lastName, Number, "123123 Main St, Anytown AS      ");
		});
	}
	
	@Test
	void testContactAddressNull() {
		Assertions.assertThrows(IllegalArgumentException.class, ()-> {
			ContactService contact = new ContactService();
			contact.addContact(firstName, lastName, Number, null);
		});
	}
	
	//Testing the unique contact ID from UUID
	@Test
	void testUniqueID() {
		ContactService contact = new ContactService();
		contact.addContact(firstName, lastName, Number, Address);
		contact.addContact("Mary", "Jo", "5551112222", "321 South St, Anytown AS");
		assertTrue(contact.getContacts().get(0).getId() != contact.getContacts().get(1).getId());
	}
	
	//Testing setting the first name
	@Test
	void testSetFirst() {
		ContactService contact = new ContactService();
		contact.addContact(firstName, lastName, Number, Address);
		contact.setFirstName("Phil", contact.getContacts().get(0).getId());
		assertTrue(contact.getContacts().get(0).getFirstName().equals("Phil"));
	}
	
	//Testing the Search function to verify wrong ID throws exception
	@Test
	void testSearch() {
		ContactService contact = new ContactService();
		contact.addContact(firstName, lastName, Number, Address);
		Assertions.assertThrows(IllegalArgumentException.class, ()-> {
			contact.search("ABCD12");
		});
	}
	
	//Testing the Search function to verify wrong ID throws exception
	@Test
	void testSearchIDLength() {
		ContactService contact = new ContactService();
		contact.addContact(firstName, lastName, Number, Address);
		Assertions.assertThrows(IllegalArgumentException.class, ()-> {
			contact.search("ABCD1231231212121212");
		});
	} 
	
	//Testing input validation for ID being alphanumeric
	@Test
	void testSearchIDAlphanumeric() {
		ContactService contact = new ContactService();
		contact.addContact(firstName, lastName, Number, Address);
		Assertions.assertThrows(IllegalArgumentException.class, ()-> {
			contact.search("AB87!@$!98");
		});
	}
	
	@Test
	void testSetLast() {
		ContactService contact = new ContactService();
		contact.addContact(firstName, lastName, Number, Address);
		contact.setLastName("Johnson", contact.getContacts().get(0).getId());
		assertTrue(contact.getContacts().get(0).getLastName().equals("Johnson"));
	}
	
	@Test
	void testSetNum() {
		ContactService contact = new ContactService();
		contact.addContact(firstName, lastName, Number, Address);
		contact.setNumber("5554441234", contact.getContacts().get(0).getId());
		assertTrue(contact.getContacts().get(0).getNumber().equals("5554441234"));
	}
	
	@Test
	void testSetAddress() {
		ContactService contact = new ContactService();
		contact.addContact(firstName, lastName, Number, Address);
		contact.setAddress("987 South St, Anytown AS", contact.getContacts().get(0).getId());
		assertTrue(contact.getContacts().get(0).getAddress().equals("987 South St, Anytown AS"));
	}
	
	//Test verifying removeContact() function
	@Test
	void testRemoveContact() {
		ContactService contact = new ContactService();
		contact.addContact(firstName, lastName, Number, Address);
		contact.addContact("Mary", "Jo", "5551112222", "321 South St, Anytown AS");
		contact.removeContact(contact.getContacts().get(1).getId());
		assertTrue(contact.getContacts().size() == 1);
	}
	
	//Test ID Length verification
	@Test
	void testRemoveContactLength() {
		ContactService contact = new ContactService();
		contact.addContact(firstName, lastName, Number, Address);
		contact.addContact("Mary", "Jo", "5551112222", "321 South St, Anytown AS");
		Assertions.assertThrows(IllegalArgumentException.class, ()->{
			contact.removeContact("ABC12387NHW32");
		});
	}
	
	//Test ID Alphanumeric verification
	@Test
	void testRemoveContactAlphanumeric() {
		ContactService contact = new ContactService();
		contact.addContact(firstName, lastName, Number, Address);
		contact.addContact("Mary", "Jo", "5551112222", "321 South St, Anytown AS");
		Assertions.assertThrows(IllegalArgumentException.class, ()->{
			contact.removeContact("ABC123!&43");
		});
	}
}

