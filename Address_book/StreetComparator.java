import java.util.Comparator;

public class StreetComparator implements Comparator<Address>{

	@Override
	public int compare(Address a1, Address a2) {
		if(a1.getStreet().compareTo(a2.getStreet()) == 0)
			return 1;		
		return  a1.getStreet().compareTo(a2.getStreet());
	}
}
