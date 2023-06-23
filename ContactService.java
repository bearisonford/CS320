package contactService;

//Author: Lukas Pentowski
//CS320-T5520 - Software Testing and Analysis
//May 20, 2023

import java.util.ArrayList;
import java.util.Random;    //Generate random integer
import java.util.UUID;        //For unique ID generation.  Info from "How to Generate
                              //a UUID in Java?" by coderolls https://youtu.be/DPLw8Z2NI78

public class ContactService {
	
	private ArrayList<Contact> contacts = new ArrayList<Contact>();
	
	public ArrayList<Contact> getContacts(){
		return contacts;
	}
	
	Random random = new Random();
	
	
	//Method to create a unique ID that is up to 10 characters long.  Verifies that no other ID in
	//use matches through a while loop that compares the generated substring saved to temp
	//variable t to the assigned ID for each list item.  If t equals the item's ID
	//t will be assigned a new generated subset and we will reset the while loop. If t does
	//not match i will increase by 1 and the next list item will be checked.
	private String createUUID() {
		String t;
		t = UUID.randomUUID().toString().replace("-","").substring(0, random.nextInt(10));
		int i = 0;
		while (i < contacts.size()){
			if (t == ((Contact) contacts.get(i)).getId() ){
	               t = UUID.randomUUID().toString().replace("-","").substring(0,random.nextInt(10));
	               i = 0;
	         }
			i++;
		}
		return t;
	}
	
	//Adds a new contact to the ArrayList.  Generates a new id for the contact as well.
	public void addContact(String firstName, String lastName, String Number, String Address){
		String id = createUUID();
		Contact Contact = new Contact(firstName, lastName, Number, Address, id);
		contacts.add(Contact);
	}
	
	//Uses a loop to search through the ArrayList, comparing each item's assigned ID to the
	//user provided ID.  If they match the item will be returned  If no contact is found to
	//have the provided ID, throws exception that contact does not exist. Contact will remain
	//null if no item is found.
	public Contact search(String id) {
		if(id.length()>10 || (id.chars().allMatch(Character::isLetterOrDigit) != true)) {
			throw new IllegalArgumentException("Invalid id");
		}
		
		Contact Contact = null;
		int j = 1;
		for (int i = 0; i < contacts.size(); i++){
			if (id == ((Contact) contacts.get(i)).getId() ){
			Contact = contacts.get(i);
			}
			else if (j == contacts.size()){
				throw new IllegalArgumentException("Contact does not exist.");
			}
			j++;
		}
		return Contact;
	}
	
	//Uses the search function to find the contact that has the provided id value.
	//If the returned item is not null, sets the first name to provided name.
	public void setFirstName(String firstName, String id) {
		Contact contact = search(id);
		if(contact != null) {
			contact.setFirstName(firstName);
		}
	}
	
	//Same format as setFirstName() but changes the last name to the one provided
	public void setLastName(String lastName, String id) {
		Contact contact = search(id);
		if(contact != null) {
			contact.setLastName(lastName);
		}
	}
	
	//Same format as setFirstName() but changes the address to the one provided
	public void setAddress(String Address, String id) {
		Contact contact = search(id);
		if(contact != null) {
			contact.setAddress(Address);
		}
	}
	
	//Same format as setFirstName() but changes the number to the one provided
	public void setNumber(String Number, String id) {
		Contact contact = search(id);
		if(contact != null) {
			contact.setNumber(Number);
		}
	}
	
	//Using a modified search method to find a contact and remove
	//from the list.
	public void removeContact(String id) {
		if(id.length()>10 || (id.chars().allMatch(Character::isLetterOrDigit) == false)) {
			throw new IllegalArgumentException("Invalid id");
		}
		int j = 1;
		for (int i = 0; i < contacts.size(); i++){
			if (id == ((Contact) contacts.get(i)).getId() ){
			contacts.remove(i);
			break;
			}
			else if (j == contacts.size()) {
				throw new IllegalArgumentException("Contact does not exist.");
			}
			j++;
		}
	}
	
}
