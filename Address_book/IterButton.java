

import java.io.RandomAccessFile;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeSet;


public class IterButton extends CommandButton { 
	private boolean pushTwo = false;
	private Map<MyKey, Address> map = new LinkedHashMap<MyKey,Address>();

	public IterButton(AddressBookPane pane, RandomAccessFile r, MyListIterator lit) {
		super(pane, r, lit);
		this.setText("Iter");
	}

	@Override
	public void  Execute() {

		if(!pushTwo) {
			Address a = new Address();
			MyKey key = new MyKey();
			//go to beginning
			while(lit.hasPrevious()) {
				lit.previous();		
			}
			//put addresses into map (with list of keys);
			while(lit.hasNext()) {
				a = lit.next();

				key.setS1(a.getName());
				key.setS2(a.getStreet());
				key.setS3(a.getCity());
				key.setS4(a.getState());	
				//put key's and value to map
				map.put(key, a);
			}

			// delete file with comparator
			while(lit.hasPrevious()) {
				// in list iterator you have to do next or previous before remove
				lit.previous();	
				lit.remove();
			}

			//enter map to file
			Set<Entry<MyKey, Address>> entrySet = map.entrySet();
			Iterator<Entry<MyKey, Address>> it = entrySet.iterator();
			while(it.hasNext()) {
				lit.add(it.next().getValue());
				lit.next();
			}

			//from second push option
			pushTwo = true;
		}
		
		else {
			Set<Address> addressTreeSet = new TreeSet<Address>(new StreetComparator());
			for(Address a : map.values()) {
				addressTreeSet.add(a);
			}
			
            //go to the end of file
			if(lit.hasNext()) {
				while(lit.hasNext()) {
					lit.next();
				}
			}
			
			// delete file with comparator
			while(lit.hasPrevious()) {
				lit.previous();
				lit.remove();
			}

			//adding value's from tree to (empty) file
			Iterator<Address> treeitr = addressTreeSet.iterator();
			while(treeitr.hasNext()) {
				lit.add(treeitr.next());
				lit.next();
			}
			//go to beginning
			while(lit.hasPrevious()) {
				lit.previous();
			}
			
		}
	}
}
