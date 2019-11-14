import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;



public class MyListIterator implements ListIterator<Address>{


	protected List<Address> li = new ArrayList<Address>();
	public final static int CHAR_SIZE_IN_BYTES = 2;
	public final static int NAME_SIZE = 30;
	public final static int STREET_SIZE = 30;
	public final static int CITY_SIZE = 20;
	public final static int STATE_SIZE = 10;
	public final static int ZIP_SIZE = 10;
	public final static int RECORD_SIZE = 
			(NAME_SIZE + STREET_SIZE + CITY_SIZE + STATE_SIZE + ZIP_SIZE);;
			protected RandomAccessFile raf; 
			protected AddressBookPane pane;
			protected int indexAfterLastReturndElement = 0; 
			protected boolean nextFlag = false;
			protected boolean privFlag = false;

			public MyListIterator(RandomAccessFile raf) {
				this.raf = raf; 
				// i use the list for add and remove
				fillTheArray();	
			}

			//when i create new this iterator i need to fill up my list for adding and removing addresses
			private void fillTheArray() {
				while(this.hasNext()) {
					li.add(this.next());
				}
				while(this.hasPrevious()) {				
					this.previous();
				}
			}


			@Override
			public boolean hasNext() {	
				try {				
					if (indexAfterLastReturndElement < (raf.length()/(RECORD_SIZE * CHAR_SIZE_IN_BYTES)))
						return true;		
					else 
						return false;
				} catch (IOException e) {				
					e.printStackTrace();				
				}
				return false;		  
			}

			@Override
			public Address next() {
				Address address = new Address();	
				//seek location 
				long position = nextIndex();
				//get address from file 
				address = readAddress(position);
				indexAfterLastReturndElement++;
				nextFlag = true;
				privFlag = false;
				return address;	
			}

			@Override
			public boolean hasPrevious() {
				//after i get first element with next() metod indexOfLastReturndElement = 1
				if(indexAfterLastReturndElement <= 0)
					return false;
				else 
					return true;
			}

			@Override
			public Address previous() {
				Address address = new Address();
				long position = previousIndex();
				address = readAddress(position);
				indexAfterLastReturndElement--;
				privFlag = true;
				nextFlag = false;
				return address;
			}

			@Override
			public int nextIndex() { //returns next index by number of addresses
				return indexAfterLastReturndElement;
			}

			@Override
			public int previousIndex() {
				return indexAfterLastReturndElement -1;
			}

			@Override
			public void remove() {
				int indexToRemove = -1;

				if(nextFlag == true)
					indexToRemove = indexAfterLastReturndElement -1;
				if(privFlag == true) 
					indexToRemove = indexAfterLastReturndElement;
                 if(nextFlag == true || privFlag == true) {
				li.remove(indexToRemove);	
				try {
					raf.seek(0);
					raf.setLength(0);	
					for(Address a : li)
						writeAddress(a);
					
				} catch (IOException e) {					
					e.printStackTrace();
				}
				nextFlag = false;
				privFlag = false;
                 }
			}

			@Override
			public void set(Address a) {
				int indexForSet = -1;
				if(nextFlag == true) 
					indexForSet = indexAfterLastReturndElement -1;
				if(privFlag == true) 
					indexForSet = indexAfterLastReturndElement;
			
				li.set(indexForSet,a);
				writeAddress(a, indexForSet);		
			}

			@Override
			public void add(Address a) {
				try {
					raf.seek(0);
					raf.setLength(0);
					
					li.add(indexAfterLastReturndElement, a);
					for(Address a1 : li)
						writeAddress(a1);
					
				} catch (IOException e1) {					
					e1.printStackTrace();
				}
			}

			public Address readAddress(long position){			
				Address address = new Address();
				try {	
					raf.seek(position*(CHAR_SIZE_IN_BYTES  * RECORD_SIZE));
					address.setName(FixedLengthStringIO.readFixedLengthString(NAME_SIZE, raf));
					address.setStreet(FixedLengthStringIO.readFixedLengthString(STREET_SIZE, raf));
					address.setCity(FixedLengthStringIO.readFixedLengthString(CITY_SIZE, raf));
					address.setState(FixedLengthStringIO.readFixedLengthString(STATE_SIZE, raf));
					address.setZip(Integer.parseInt(FixedLengthStringIO.readFixedLengthString(ZIP_SIZE, raf).trim()));	
				} catch (IOException e) {
					e.printStackTrace();
				}
				return address;
			}

			public void writeAddress(Address a, long position){	
				try{
					raf.seek(position * (CHAR_SIZE_IN_BYTES  * RECORD_SIZE));
					writeAddress(a);
				} 
				catch (IOException ex){
					ex.printStackTrace();
				}
			}
			
			public void writeAddress(Address a){	
				try
				{ 
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
				catch (IOException ex)
				{ ex.printStackTrace();
				}
			}

}
