import java.io.FileNotFoundException;
import java.io.IOException;

public class App {

	public static void main(String[] args) {
		ContactsManager cm;
		try {
			cm = new ContactsManager("contacts.dat");

			ContactsManagerFrame cmf = new ContactsManagerFrame(cm);
			cmf.init();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	
	
}
