import java.io.RandomAccessFile;

class AddButton extends CommandButton { 


	public AddButton(AddressBookPane pane, RandomAccessFile r, MyListIterator lit) {	
		super(pane, r, lit);
		this.setText("Add");
	}

	@Override
	public void Execute() {
		a = getAdressFromTextFields();
		//adding to the end of file
		while(lit.hasNext())
			lit.next();
		lit.add(a);
	}
}
