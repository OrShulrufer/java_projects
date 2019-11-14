import java.util.concurrent.*;
public class ParallelMaxWithForkJoin
{ final static int N = 80000000;
  public static void main(String[] args)
  { // Create a list
    int[] list = new int[N];
    for (int i = 0; i < list.length; i++)
      list[i] = (int)(Math.random() * N);
    long startTime = System.currentTimeMillis();
    System.out.println("\nThe maximal number is " + max(list));
    long endTime = System.currentTimeMillis();
    System.out.println("Number of processors is " + 
      Runtime.getRuntime().availableProcessors()); 
    System.out.println("Time:  " + (endTime - startTime) 
      + " milliseconds"); 
  }
  public static int max(int[] list)
  { RecursiveTask<Integer> task = 
       new MaxTask(list, 0, list.length);
    ForkJoinPool pool = new ForkJoinPool();
    return pool.invoke(task);
  }
  private static class MaxTask extends RecursiveTask<Integer>
  { private static final long serialVersionUID = 1L;
    private final static int THRESHOLD = 
    		2 * N / Runtime.getRuntime().availableProcessors(); 
    private int[] list;
    private int low;
    private int high;
    public MaxTask(int[] list, int low, int high)
    { this.list = list;
      this.low = low;
      this.high = high;
    }
    @Override
    public Integer compute()
    { if (high - low < THRESHOLD)
      { int max = list[0];
        for (int i = low; i < high; i++)
          if (list[i] > max)
            max = list[i];
        return new Integer(max);
      } 
      else
      { int mid = (low + high) / 2;
        RecursiveTask<Integer> left = 
        	new MaxTask(list, low, mid);
        RecursiveTask<Integer> right = 
        	new MaxTask(list, mid+1, high);
        invokeAll(right, left);
        //right.fork();
        //left.fork(); 
        return new Integer(Math.max(left.join(), right.join()));
      }
    }
  }
}
