package Threading;

import org.springframework.beans.factory.annotation.Autowired;

import com.klef.multithreaded.entity.Movie;
import com.klef.multithreaded.repository.DbRepository;

public class OperationTask implements Runnable
{
	
	DbRepository repo;
	
	String op;
	Movie m;
	int id;
       public OperationTask(String op, Movie m, DbRepository repo)
       {
    	    this.op =op;
    	    this.m = m;
    	    this.repo = repo;
       }
        public OperationTask(String op, int id,  DbRepository repo)
       {
    	    this.op =op;
    	      this.id = id;
    	        this.repo =  repo;
       }
       
       public void run()
       {
    	     if(op=="Save")
    	     {
    	    	 System.out.println(" Save movie ");
//    	    	 if(repo == null)
//    	    		        System.out.println(" NULL ");
    	    	  repo.save(m);
    	     }
    	     else if( op == "Delete")
    	     {
    	    	 System.out.println("  delete movie ");
    	    	 repo.deleteById(id);
    	     }
    	     else
    	     {
    	    	 System.out.println("No ope");
    	     }
       }
}
