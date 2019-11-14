import java.util.Comparator;

public class NameComparator implements Comparator<Address>{

	@Override
	public int compare(Address a1, Address a2) {
		
		return  a1.getName().compareTo(a2.getName());
	}
}
