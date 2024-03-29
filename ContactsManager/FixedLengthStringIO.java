import java.io.*;

public class FixedLengthStringIO {
	/** Read fixed number of characters from a DataInput stream */
	public static String readFixedLengthString(int size, DataInput in)
			throws IOException {
		char[] chars = new char[size]; // Declare an array of characters
		// Read fixed number of characters to the array
		for (int i = 0; i < size; i++)
			chars[i] = in.readChar();
		return new String(chars);
	}

	/** Write fixed number of characters to a DataOutput stream */
	public static void writeFixedLengthString(String s, int size, DataOutput out)
			throws IOException {
		char[] chars = new char[size];
		s.getChars(0, Math.min(s.length(), size), chars, 0);
		for (int i = s.length(); i < size; i++)
			chars[i] = ' ';

		System.out.println(new String(chars));
		out.writeChars(new String(chars));
	}
}