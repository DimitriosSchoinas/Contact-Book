

public class ContactBook {
	static final int DEFAULT_SIZE = 100;

	private int counter;
	private Contact[] contacts;

	public ContactBook() {
		counter = 0;
		contacts = new Contact[DEFAULT_SIZE];
	}

	/** Pre: name != null */
	public boolean hasContact(String name) {
		return searchIndex(name) >= 0;
	}
	public boolean hasName(int phoneNumber) {
		return searchPhoneIndex(phoneNumber) >= 0;
	}

	public int getNumberOfContacts() {
		return counter;
	}

	/** Pre: name!= null && !hasContact(name) */
	public void addContact(String name, int phone, String email) {
		if (counter == contacts.length) 
			resize();
		contacts[counter] = new Contact(name, phone, email);
		counter++;
	}

	/** Pre: name != null && hasContact(name) */
	public void deleteContact(String name) {
		int index = searchIndex(name);		
		for(int i=index; i<counter; i++)
			contacts[i] = contacts[i+1];
		counter--;
	}

	/** Pre: name != null && hasContact(name) */
	public int getPhone(String name) {
		return contacts[searchIndex(name)].getPhone();
	}

	/** Pre: name != null && hasContact(name) */
	public String getEmail(String name) {
		return contacts[searchIndex(name)].getEmail();
	}
	public String getName(int phoneNumber) {
		return contacts[searchPhoneIndex(phoneNumber)].getName();
	}

	/** Pre: name != null && hasContact(name) */
	public void setPhone(String name, int phone) {
		contacts[searchIndex(name)].setPhone(phone);
	}

	/** Pre: name != null && hasContact(name) */
	public void setEmail(String name, String email) {
		contacts[searchIndex(name)].setEmail(email);
	}

	public ContactIterator iterator() {
		return new ContactIterator(contacts, counter);
	}
	
	private int searchIndex(String name) {
		int i = 0;
		int result = -1;
		boolean found = false;
		while (i<counter && !found)
			if (contacts[i].getName().equals(name))
				found = true;
			else
				i++;
		if (found) result = i;
		return result;
	}
	
	private int searchPhoneIndex(int phoneNumber) {
		int i = 0;
		int result = -1;
		boolean found = false;
		while(i<counter && !found)
			if(contacts[i].getPhone() == phoneNumber)
				found = true;
			else
				i++;
		if (found) result = i;
		return result;
	}
	

	private void resize() {
		Contact tmp[] = new Contact[2*contacts.length];
		for (int i=0;i<counter; i++)
			tmp[i] = contacts[i];
		contacts = tmp;
	}
	public boolean existsSamePhones() {
		
		boolean result = false;
		
		for(int i = 0; i < counter ; i++) {
			for(int z = 0; z < counter; z++) {
				
				if( i != z && contacts[i].getPhone() == contacts[z].getPhone()) {
					result = true;
				}
			}
		}
		return result;
	}
	
}
