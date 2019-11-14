import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class ContactsManager {
	public final static int CHAR_SIZE_IN_BYTES = 2;
	public final static int NEW_CONTACT_STARTER = 0;
	
	private String fileName;
	private RandomAccessFile raf;

	private int newContactIndexGen = NEW_CONTACT_STARTER; //index for the of file
	private int arrowsIndex; // index for using arrows
	

	public ContactsManager(String fileName) throws IOException {
		try {
			raf = new RandomAccessFile(new File(fileName), "rw") ;
		} catch (IOException ex) {
			System.out.println("Error:" + ex);
			System.exit(0);
		}
	}

	public String getFileName() {
		return fileName;
	}

	public RandomAccessFile getRaf() {
		return raf;
	}

	public void addContact(String fn, String ln, String pn) throws IOException {
		Contact c = new Contact(fn, ln, pn);
		c.writeObject(this.raf);
		arrowsIndex = newContactIndexGen;
		pasteContactOnPanelAt(fileName, newContactIndexGen++);     
	}
	
	public void getPreviousContact() throws IOException {
		if(arrowsIndex > 0)
		    pasteContactOnPanelAt(fileName,--arrowsIndex);
		else
		    System.out.println("It's a first contact");
	} 
	
	public void getNextContact() throws IOException {
		if(arrowsIndex < newContactIndexGen - 1)
			pasteContactOnPanelAt(fileName,++arrowsIndex);
		else 
			System.out.println("It's the last contact");		
	}
	
	public void getFirsContact() throws IOException {
		pasteContactOnPanelAt(fileName, 0);	
	}
	
	public void getLastContact() throws IOException {
		pasteContactOnPanelAt(fileName, newContactIndexGen - 1);	
	}
	

	public void pasteContactOnPanelAt(String fileName, long index) throws IOException {
		//reading from file to create temporary contact
		Contact c = getContactAt(fileName, index); 
		
		//setting labels according to temporary contact
		ContactsManagerPanel.setFirstNameL3(c.getFirstName());
		ContactsManagerPanel.setLastNameL3(c.getLastName());
		ContactsManagerPanel.setPhoneNumberL3(c.getPhoneNumber());
	}
	
	
	public Contact getContactAt(String fileName, long index) throws IOException {
		Contact c = new Contact();
		raf.seek(index*CHAR_SIZE_IN_BYTES*c.getObjectSize());
		
		//reading file to strings
		String firstName = FixedLengthStringIO.readFixedLengthString
				(Contact.FIRST_NAME_SIZE, raf).trim();
		String lastName = FixedLengthStringIO.readFixedLengthString
				(Contact.LAST_NAME_SIZE, raf).trim();	
		String phoneNumber = FixedLengthStringIO.readFixedLengthString
				(Contact.PHONE_NUMBER_SIZE, raf).trim();
		
		//creating contact from strings
		c = new Contact(firstName, lastName, phoneNumber);
		return c; //returning contact
	}


}
