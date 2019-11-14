
public class MyKey {
	private String s1;
	private String s2;
	private String s3;
	private String s4;
	
	
	public MyKey(String s1, String s2, String s3, String s4) {

		setS1(s1);
		setS2(s2);
		setS3(s3);
		setS4(s4);
	}
    
	public MyKey() {
		
	}
	
	public int hashCode(){
		System.out.println("In hashcode");
		int hashcode = 0;
		hashcode = s1.hashCode() + s2.hashCode() + s3.hashCode() + s4.hashCode();
		return hashcode;
	}

	public String getS1() {
		return s1;
	}


	public void setS1(String s1) {
		this.s1 = s1;
	}


	public String getS2() {
		return s2;
	}


	public void setS2(String s2) {
		this.s2 = s2;
	}


	public String getS3() {
		return s3;
	}


	public void setS3(String s3) {
		this.s3 = s3;
	}


	public String getS4() {
		return s4;
	}


	public void setS4(String s4) {
		this.s4 = s4;
	}
	
	

}
