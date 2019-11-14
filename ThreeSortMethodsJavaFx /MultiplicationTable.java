import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class MultiplicationTable {

	public static void main(String[] args) {
		int max = 4;
		int treshold = max / (Runtime.getRuntime().availableProcessors());
		
		
		ForkJoinPool pool = new ForkJoinPool();
		WriteMul mul = new WriteMul(0, max, 2);
		pool.invoke(mul);

	}

}


