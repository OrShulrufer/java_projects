///* parallel merge sort with multi threading
//  @ course 10119
//  @ itzhik aviv    
// */
//import java.util.concurrent.RecursiveAction;
//import java.util.concurrent.ForkJoinPool;
//public class MergeSortWithForkJoin
//{ final static int SIZE = 80000000; // try 1,000, 10,000, 100,000, 1,000,000, 10,000,000
//  final static int PN = Runtime.getRuntime().availableProcessors();
//  public static void main(String[] args)
//  { int[] list1 = new int[SIZE];
//    int[] list2 = new int[SIZE];
//    for (int i = 0; i < list1.length; i++)
//      list1[i] = list2[i] = (int)(Math.random() * SIZE);
//    long startTime = System.currentTimeMillis();
//    parallelMergeSort(list1); // Invoke parallel merge sort
//    long endTime = System.currentTimeMillis();
//    System.out.println("\nParallel time with "   + PN  + 
//      " processors is " + (endTime - startTime) + " milliseconds");
//    for (int i=0; i< Math.min(1000, list1.length); i++)
//    {   if (i % 20 == 0) System.out.println();         
//    	System.out.print(list1[i] + " ");
//    }
//    try
//    { Thread.sleep(2000);
//	} 
//    catch (InterruptedException e)
//    { e.printStackTrace();
//	}
//    startTime = System.currentTimeMillis();
//    MergeSort.mergeSort(list2); 
//    endTime = System.currentTimeMillis();
//    System.out.println("\n\nSequential time is " + 
//      (endTime - startTime) + " milliseconds");
//    for (int i=0; i< Math.min(100,  list2.length); i++)
//    {   if (i % 20 == 0) System.out.println();     
//    	System.out.print(list2[i] + " ");
//    }
//   
//   }
//  public static void parallelMergeSort(int[] list)
//  { RecursiveAction mainTask = new SortTask(list);
//    ForkJoinPool pool = new ForkJoinPool();
//    pool.invoke(mainTask);
//  }
//  private static class SortTask extends RecursiveAction
//  { private static final long serialVersionUID = 1L;
//    private final int THRESHOLD =  8000000;
//    		//SIZE / (PN); // try PN, (PN-1), 2*PN
//    private int[] list;
//    SortTask(int[] list)
//    { this.list = list;
//    }
//    @Override
//    protected void compute()
//    { if (list.length < THRESHOLD)
//        java.util.Arrays.sort(list);
//      else
//      { // Obtain the first half
//        int[] firstHalf = new int[list.length / 2];
//        System.arraycopy(list, 0, firstHalf, 0, list.length / 2);
//        // Obtain the second half
//        int secondHalfLength = list.length - list.length / 2;
//        int[] secondHalf = new int[secondHalfLength];
//        System.arraycopy(list, list.length / 2, secondHalf, 
//        		0, secondHalfLength);
//        RecursiveAction left = 
//            	new SortTask(firstHalf);
//        RecursiveAction right = 
//            	new SortTask(secondHalf);
//        // Recursively sort the two halves
//        invokeAll(left, right);
//        //left.fork(); 
//        //right.fork(); 
//        //left.join();
//        //right.join();
//        // Merge firstHalf with secondHalf into list
//        MergeSort.merge(firstHalf, secondHalf, list);
//      }
//    }
//  }
//}
