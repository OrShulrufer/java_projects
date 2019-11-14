import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.RecursiveTask;

public class WriteMul extends RecursiveAction{
	private int low;
	private int high;
	private int treshold;
	ForkJoinPool pool;
	private Object lock;
	RecursiveTask<Long> t1 ;


	public WriteMul(int low, int high, int treshold) {
		this.low = low;
		this.high = high;
		this.treshold = treshold;
		this.pool = pool;
		
	}

	public void compute() {
		if(high - low <= treshold) {
			for(int i = low; i <= high; i++) 
				for(int j = 0; j <= 4; j++) 
				
					System.out.println(i + " * " + j + " = " + i*j); 	
			
		}
		else {
		// (this) {
			
		 
		int mid = (low + high) / 2;
		RecursiveAction t1 = new WriteMul(low, mid, treshold);
		RecursiveAction t2 = new WriteMul(mid + 1, high, treshold);
		invokeAll(t1, t2);
		}
	//	}
		
		
	}
}


