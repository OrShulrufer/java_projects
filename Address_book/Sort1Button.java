import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Comparator;

class Sort1Button extends CommandButton{

	public Sort1Button(AddressBookPane pane, RandomAccessFile r, MyListIterator lit) {
		super(pane, r, lit);
		this.setText("Sort1");
	}

	public void Execute()
	{	
		//i did this without my list iterator because it was the assignment

		Comparator<Address> nc = new NameComparator();
		//n is number of addresses in file
		long n;
		long position = 0;
		Address address1 = new Address();
		Address address2 = new Address();

		try {
			n = raf.length()/(CHAR_SIZE_IN_BYTES*RECORD_SIZE);			
			//bubble sort
			for(int i=0; i < n; i++){  
				for(int j=1; j < (n-i); j++){  

					raf.seek((j-1)*CHAR_SIZE_IN_BYTES*RECORD_SIZE);
					address1 = readAddressFromFile(raf.getFilePointer());

					raf.seek(j*CHAR_SIZE_IN_BYTES*RECORD_SIZE);
					address2 = readAddressFromFile(raf.getFilePointer());
				

					//Compare by name comparator
					if( nc.compare(address1, address2) > 0){  
						//swap elements  
						raf.seek((j-1)*CHAR_SIZE_IN_BYTES*RECORD_SIZE); 
						position = raf.getFilePointer();
						writeAddressToFile(position, address2);

						raf.seek(j*CHAR_SIZE_IN_BYTES*RECORD_SIZE); 
						position = raf.getFilePointer();
						writeAddressToFile(position, address1);
					}  
				}   
			}
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
}