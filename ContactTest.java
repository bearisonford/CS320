package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import contactService.Contact;

//Author: Lukas Pentowski
//CS320-T5520 - Software Testing and Analysis
//May 20, 2023

class ContactTest {
	
	//Test the contact constructor
	@Test
	void testContact() {
		Contact contact = new Contact("John", "Doe", "5555551212", "123 Main St, Anytown AS", "12345");
		assertTrue(contact.getFirstName().equals("John"));
		assertTrue(contact.getLastName().equals("Doe"));
		assertTrue(contact.getNumber().equals("5555551212"));
		assertTrue(contact.getAddress().equals("123 Main St, Anytown AS"));
		assertTrue(contact.getId().equals("12345"));
	}
	
	//The following are tests to verify an Illegal Argument Exception is thrown if
	//the various attributes' string are either not the correct length or null
	
	@Test
	void testContactFirstNameTooLong() {
		Assertions.assertThrows(IllegalArgumentException.class, ()-> {
			new Contact("Johnnnnnnnn", "Doe", "5555551212", "123 Main St, Anytown AS", "12345");
		});
	}
	
	@Test
	void testContactFirstNameIsNull() {
		Assertions.assertThrows(IllegalArgumentException.class, ()-> {
			new Contact(null, "Doe", "5555551212", "123 Main St, Anytown AS", "12345");
		});
	}
	
	@Test
	void testContactLastNameTooLong() {
		Assertions.assertThrows(IllegalArgumentException.class, ()-> {
			new Contact("John", "Doe        ", "5555551212", "123 Main St, Anytown AS", "12345");
		});
	}
	
	@Test
	void testContactLastNameIsNull() {
		Assertions.assertThrows(IllegalArgumentException.class, ()-> {
			new Contact("John", null, "5555551212", "123 Main St, Anytown AS", "12345");
		});
	}
	
	@Test
	void testContactNumberIsTooLong() {
		Assertions.assertThrows(IllegalArgumentException.class, ()-> {
			new Contact("John", "Doe", "555555121212", "123 Main St, Anytown AS", "12345");
		});
	}
	
	@Test
	void testContactNumberIsTooShort() {
		Assertions.assertThrows(IllegalArgumentException.class, ()-> {
			new Contact("John", "Doe", "555555121", "123 Main St, Anytown AS", "12345");
		});
	}
	
	@Test
	void testContactNumberIsNull() {
		Assertions.assertThrows(IllegalArgumentException.class, ()-> {
			new Contact("John", "Doe", null, "123 Main St, Anytown AS", "12345");
		});
	}
	
	@Test
	void testContactAddressIsTooLong() {
		Assertions.assertThrows(IllegalArgumentException.class, ()-> {
			new Contact("John", "Doe", "5555551212", "123 Main St, Anytown AS          ", "12345");
		});
	}

	@Test
	void testContactAddressIsNull() {
		Assertions.assertThrows(IllegalArgumentException.class, ()-> {
			new Contact("John", "Doe", "5555551212", null, "12345");
		});
	}

	@Test
	void testContactIDIsTooLong() {
		Assertions.assertThrows(IllegalArgumentException.class, ()-> {
			new Contact("John", "Doe", "5555551212", "123 Main St, Anytown AS", "12345678998");
		});
	}

	@Test
	void testContactIDIsNull() {
		Assertions.assertThrows(IllegalArgumentException.class, ()-> {
			new Contact("John", "Doe", "5555551212", "123 Main St, Anytown AS", null);
		});
	}
}
