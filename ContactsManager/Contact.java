import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class Contact implements Comparable<Contact>, IContact{
	public final static int FIRST_NAME_SIZE = 10;
	public final static int LAST_NAME_SIZE = 15;
	public final static int PHONE_NUMBER_SIZE = 15;
	public final static int CONTACT_SIZE = FIRST_NAME_SIZE+LAST_NAME_SIZE+
			PHONE_NUMBER_SIZE;

	private static int idGen = FIRST_NAME_SIZE;
	private int id;
	private String firstName;
	private String lastName;
	private String phoneNumber;
	
	public Contact(String firstName, String lastName, String phoneNumber) {
		this.id = idGen++;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
	}
	
	public Contact() {
	};
	
	public int compareTo(Contact c) {
		return this.id - c.id ;
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

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public int getId() {
		return id;
	}

	public void writeObject(RandomAccessFile raf) throws IOException {
		FixedLengthStringIO.writeFixedLengthString(firstName, FIRST_NAME_SIZE, raf);
		FixedLengthStringIO.writeFixedLengthString(lastName, LAST_NAME_SIZE, raf);
		FixedLengthStringIO.writeFixedLengthString(phoneNumber, PHONE_NUMBER_SIZE, raf);
	}

	@Override
	public void export(String format, File file) throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String[] getUiData() {
		// TODO Auto-generated method stub
		return null;
	}

	public int getObjectSize() {
		return CONTACT_SIZE;
	}


	
	
	
 


}
