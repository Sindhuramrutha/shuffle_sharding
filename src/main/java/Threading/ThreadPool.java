package Threading;


public class ThreadPool 
{
	private final int nThreads;
    private final PoolWorker[] threads;
 
    public ThreadPool(int nThreads) 
    {
        this.nThreads = nThreads;
        threads = new PoolWorker[nThreads];
 
        for (int i = 0; i < nThreads; i++) 
        {
            threads[i] = new PoolWorker();
            threads[i].setName(" Server Thread "+i);
            
            threads[i].start();
            
        }
    }
 
    public void execute(Runnable task ,int i) 
    {
    	threads[i].execute(task);
    	      System.out.print( threads[i].getName() );
    	threads[i].run();
    }
 
    private class PoolWorker extends Thread 
    {
    	Runnable task;
    	public void execute( Runnable task)
    	{
    		 this.task = task;
    		 
    	}
        public void run() 
        {
        	if(task!= null )
        	{
        	           //System.out.print(Thread.currentThread().getName() );
                   task.run();
        	}
        	else
        	{
        		System.out.print("Null" );
        	}
        }
    }
}
