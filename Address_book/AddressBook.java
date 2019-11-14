import java.io.IOException;
import java.io.RandomAccessFile;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;

// Or Shulrufer 310994710
public class AddressBook extends Application {
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		AddressBookPane pane = new AddressBookPane();
		Scene scene = new Scene(pane);
		scene.getStylesheets().add("styles.css");
		primaryStage.setTitle("AddressBook");
		primaryStage.setScene(scene);
		primaryStage.show();
		primaryStage.setAlwaysOnTop(true);
	}
}

class AddressBookPane extends GridPane {
	private RandomAccessFile raf;

	// Text fields
	private TextField jtfName = new TextField();
	private TextField jtfStreet = new TextField();
	private TextField jtfCity = new TextField();
	private TextField jtfState = new TextField();
	private TextField jtfZip = new TextField();

	// Buttons
	private AddButton jbtAdd;
	private FirstButton jbtFirst;
	private NextButton jbtNext;
	private PreviousButton jbtPrevious;
	private LastButton jbtLast;
	private IterButton jbtIter;
	private Sort1Button jbtSort1;
	private Sort2Button jbtSort2;

	public EventHandler<ActionEvent> ae =
			new EventHandler<ActionEvent>() {
		public void handle(ActionEvent arg0) {
			((Command) arg0.getSource()).Execute();
		}
	};

	public AddressBookPane() {
		// Open or create a random access file
		try {
			raf = new RandomAccessFile("address.dat", "rw");
		}
		catch (IOException ex) {
			System.out.print("Error: " + ex);
			System.exit(0);
		}

		jtfState.setAlignment(Pos.CENTER_LEFT);
		jtfState.setPrefWidth(80);
		jtfZip.setPrefWidth(80);

		MyListIterator lit = new MyListIterator(raf);
		jbtAdd = new AddButton(this, raf, lit);
		jbtFirst = new FirstButton(this, raf, lit);
		jbtNext = new NextButton(this, raf, lit);
		jbtPrevious = new PreviousButton(this, raf, lit);
		jbtLast = new LastButton(this, raf, lit);
		jbtSort1 = new Sort1Button(this, raf, lit);
		jbtSort2 = new Sort2Button(this, raf, lit);
		jbtIter = new IterButton(this, raf, lit);

		Label state = new Label("State");
		Label zp = new Label("Zip");
		Label name = new Label("Name");
		Label street = new Label("Street");
		Label city = new Label("City");

		// Panel p1 for holding labels Name, Street, and City
		GridPane p1 = new GridPane();
		p1.add(name, 0, 0);
		p1.add(street, 0, 1);
		p1.add(city, 0, 2);
		p1.setAlignment(Pos.CENTER_LEFT);
		p1.setVgap(8);
		p1.setPadding(new Insets(0, 2, 0, 2));

		GridPane.setVgrow(name, Priority.ALWAYS);
		GridPane.setVgrow(street, Priority.ALWAYS);
		GridPane.setVgrow(city, Priority.ALWAYS);

		// City Row
		GridPane adP = new GridPane();
		adP.add(jtfCity, 0, 0);
		adP.add(state, 1, 0);
		adP.add(jtfState, 2, 0);
		adP.add(zp, 3, 0);
		adP.add(jtfZip, 4, 0);
		adP.setAlignment(Pos.CENTER_LEFT);

		GridPane.setHgrow(jtfCity, Priority.ALWAYS);
		GridPane.setVgrow(jtfCity, Priority.ALWAYS);
		GridPane.setVgrow(jtfState, Priority.ALWAYS);
		GridPane.setVgrow(jtfZip, Priority.ALWAYS);
		GridPane.setVgrow(state, Priority.ALWAYS);
		GridPane.setVgrow(zp, Priority.ALWAYS);

		// Panel p4 for holding jtfName, jtfStreet, and p3
		GridPane p4 = new GridPane();
		p4.add(jtfName, 0, 0);
		p4.add(jtfStreet, 0, 1);
		p4.add(adP, 0, 2);
		p4.setVgap(1);

		GridPane.setHgrow(jtfName, Priority.ALWAYS);
		GridPane.setHgrow(jtfStreet, Priority.ALWAYS);
		GridPane.setHgrow(adP, Priority.ALWAYS);
		GridPane.setVgrow(jtfName, Priority.ALWAYS);
		GridPane.setVgrow(jtfStreet, Priority.ALWAYS);
		GridPane.setVgrow(adP, Priority.ALWAYS);

		// Place p1 and p4 into jpAddress
		GridPane jpAddress = new GridPane();
		jpAddress.add(p1, 0, 0);
		jpAddress.add(p4, 1, 0);
		GridPane.setHgrow(p1, Priority.NEVER);
		GridPane.setHgrow(p4, Priority.ALWAYS);
		GridPane.setVgrow(p1, Priority.ALWAYS);
		GridPane.setVgrow(p4, Priority.ALWAYS);

		// Set the panel with line border
		jpAddress.setStyle("-fx-border-color: grey;"
				+ " -fx-border-width: 1;"
				+ " -fx-border-style: solid outside ;");

		// Add buttons to a panel
		FlowPane jpButton = new FlowPane();
		jpButton.setHgap(5);
		jpButton.getChildren().	addAll(jbtAdd, jbtFirst,
				jbtNext, jbtPrevious, jbtLast, jbtSort1, jbtSort2, jbtIter );
		jpButton.setAlignment(Pos.CENTER);

		GridPane.setVgrow(jpButton, Priority.NEVER);
		GridPane.setVgrow(jpAddress, Priority.ALWAYS);
		GridPane.setHgrow(jpButton, Priority.ALWAYS);
		GridPane.setHgrow(jpAddress, Priority.ALWAYS);

		// Add jpAddress and jpButton to the stage
		this.setVgap(5);
		this.add(jpAddress, 0, 0);
		this.add(jpButton, 0, 1);

		jbtAdd.setOnAction(ae);
		jbtFirst.setOnAction(ae);
		jbtNext.setOnAction(ae);
		jbtPrevious.setOnAction(ae);
		jbtLast.setOnAction(ae);
		jbtFirst.setOnAction(ae);
		jbtSort1.setOnAction(ae);
		jbtSort2.setOnAction(ae);
		jbtIter.setOnAction(ae);
	}




	public void actionHandled(ActionEvent e) {
		((Command) e.getSource()).Execute();
	}
	public void SetName(String text) {
		jtfName.setText(text);
	}
	public void SetStreet(String text) {
		jtfStreet.setText(text);
	}
	public void SetCity(String text) {
		jtfCity.setText(text);
	}
	public void SetState(String text) {
		jtfState.setText(text);
	}
	public void SetZip(String text) {
		jtfZip.setText(text);
	}
	public String GetName() {
		return jtfName.getText();
	}
	public String GetStreet() {
		return jtfStreet.getText();
	}

	public String GetCity() 	{
		return jtfCity.getText();
	}
	public String GetState() {
		return jtfState.getText();
	}
	public String GetZip() {
		return jtfZip.getText();
	}
}




interface Command {
	public void Execute();
}

class CommandButton extends Button implements Command{
	public final static int CHAR_SIZE_IN_BYTES = 2;
	public final static int NAME_SIZE = 30;
	public final static int STREET_SIZE = 30;
	public final static int CITY_SIZE = 20;
	public final static int STATE_SIZE = 10;
	public final static int ZIP_SIZE = 10;
	public final static int RECORD_SIZE =
			(NAME_SIZE + STREET_SIZE + CITY_SIZE + STATE_SIZE + ZIP_SIZE);
	protected AddressBookPane p;
	protected RandomAccessFile raf;
	protected MyListIterator lit ;
	protected Address a;
	protected static boolean nextFlag = false;
	protected static boolean prevFlag = false;

	public CommandButton(AddressBookPane pane, RandomAccessFile r, MyListIterator lit ) {
		super();
		this.p = pane;
		this.raf = r;
		this.lit = lit;
	}

	public void Execute(){
	}


	//Get  address from text fields on the panel
	Address getAdressFromTextFields() {
		Address a = new Address();

		a.setName(p.GetName());
		a.setStreet(p.GetStreet());
		a.setCity(p.GetCity());
		a.setState(p.GetState());
		a.setZip(Integer.parseInt(p.GetZip().trim()));

		return a;
	}

	//write address to file by location( in bytes )
	public void writeAddressToFile(long position, Address a) throws IOException {
		raf.seek(position);
		FixedLengthStringIO.writeFixedLengthString(a.getName(),
				NAME_SIZE, raf);
		FixedLengthStringIO.writeFixedLengthString(a.getStreet(),
				STREET_SIZE, raf);
		FixedLengthStringIO.writeFixedLengthString(a.getCity(),
				CITY_SIZE, raf);
		FixedLengthStringIO.writeFixedLengthString(a.getState(),
				STATE_SIZE, raf);
		FixedLengthStringIO.writeFixedLengthString(Integer.toString(a.getZip()),
				ZIP_SIZE, raf);
	}


	/** Write a record at the end of the file */
	public void writeAddress() throws IOException {
		Address a = new Address();
		long position;
		position = raf.length();

		a = getAdressFromTextFields();
		writeAddressToFile(position, a);

	}

	public void readAddress(long position) throws IOException {
		raf.seek(position);
		Address a = new Address();
		a = 	readAddressFromFile(position);
		showAddressOnPanel(a);
	}

	//Read address from file by location in bytes
	public Address readAddressFromFile(long position) throws IOException {
		Address a = new Address();
		raf.seek(position);

		a.setName(FixedLengthStringIO.readFixedLengthString(NAME_SIZE, raf));
		a.setStreet(FixedLengthStringIO.readFixedLengthString(STREET_SIZE, raf));
		a.setCity(FixedLengthStringIO.readFixedLengthString(CITY_SIZE, raf));
		a.setState(FixedLengthStringIO.readFixedLengthString(STATE_SIZE, raf));
		a.setZip(Integer.parseInt(FixedLengthStringIO.readFixedLengthString(ZIP_SIZE, raf).trim()));

		return a;
	}

	//Show given address on panel
	public void showAddressOnPanel(Address a) {
		p.SetName(a.getName());
		p.SetStreet(a.getStreet());
		p.SetCity(a.getCity());
		p.SetState(a.getState());
		p.SetZip(Integer.toString(a.getZip()));
	}
}



class NextButton extends CommandButton {
	public NextButton(AddressBookPane pane, RandomAccessFile r, MyListIterator lit) {
		super(pane, r, lit);
		this.setText("Next");
	}
	@Override
	public void Execute() {
		if(prevFlag == true)
			lit.next();
		if(lit.hasNext()) {
			a = lit.next();
			showAddressOnPanel(a);

		}
		nextFlag = true;
		prevFlag = false;
	}
}


class PreviousButton extends CommandButton {

	public PreviousButton(AddressBookPane pane, RandomAccessFile r, MyListIterator lit) {
		super(pane, r, lit);
		this.setText("Previous");
	}
	@Override
	public void Execute() {
		if(nextFlag == true)
			lit.previous();
		if(lit.hasPrevious()) {
			a = lit.previous();
			showAddressOnPanel(a);
		}
		nextFlag = false;
		prevFlag = true;
	}
}


class LastButton extends CommandButton{

	public LastButton(AddressBookPane pane, RandomAccessFile r, MyListIterator lit) {
		super(pane, r, lit);
		this.setText("Last");
	}

	@Override
	public void Execute() {
		//if i am not at the end of file i iter to the end and get the last element
		if(lit.hasNext()) {
			while(lit.hasNext()) {
				a = lit.next();
			}
		}
		//if i am at the end of file i go to previous and then next
		// this will get me the last element
		else {
			if(lit.hasPrevious())
			lit.previous();
		}
		showAddressOnPanel(a);
	}
}


class FirstButton extends CommandButton {

	public FirstButton(AddressBookPane pane, RandomAccessFile r, MyListIterator lit) {
		super(pane, r, lit);
		this.setText("First");
	}
	@Override
	public void Execute() {
		//if i am not at the end of file i iter to the end and get the last element
		if(lit.hasPrevious()) {
			while(lit.hasPrevious()) {
				a = lit.previous();

			}
			lit.next();
		}
		//if i am at the end of file i go to previous and then next
		// this will get me the last element
		else {
			if(lit.hasNext())
			a = lit.next();
		}
		lit.nextIndex();
		showAddressOnPanel(a);
	}
}
