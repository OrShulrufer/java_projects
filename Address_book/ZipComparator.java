import java.util.Comparator;

public class ZipComparator implements Comparator<Address>{

	@Override
	public int compare(Address a1, Address a2) {

		if (a1.getZip()  > a2.getZip())
			return 1;
		else if (a1.getZip() < a2.getZip())
			return -1;
		else
			return 0;
	}



}
