package com.klef.multithreaded.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;


import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.klef.multithreaded.config.Config1;
import com.klef.multithreaded.config.MyThread;
import com.klef.multithreaded.entity.Movie;
import com.klef.multithreaded.repository.DbRepository;

import Threading.OperationTask;
import Threading.ThreadPool;

@Service
public class ServerService 
{
	
    @Autowired
	private DbRepository repo;
    
    ThreadPool pool = new ThreadPool(10);
    Logger logg=LoggerFactory.getLogger(ServerService.class);
   @Async
    public CompletableFuture<List<Movie>> saveMovies(MultipartFile file) throws Exception
    {
	   
    	List<Movie> movies=parseJSONFile(file);
    	//logg.info("Saving list of movies of  "+movies.size()+"  "+Thread.currentThread().getName());  	 
    	  
    	    //for(Movie movie :  movies)
    	
    	    	repo.saveAll(movies);
       return CompletableFuture.completedFuture(movies); 
    }
   @Async
   public CompletableFuture<List<Movie>> saveMovie(int id,Movie m) throws Exception
   {
	   
	   
	   int threadnum = id/10;
	   OperationTask o = new OperationTask("Save",m,repo);
	     //o.run();
	   pool.execute(o,threadnum);
	    
	    
	   List<Movie> movies=repo.findAll();
	   return CompletableFuture.completedFuture(movies);  
   }
   
   @Async
   public void deleteMovie(int id,int mid)
   {
	   int threadnum = id/10;
	   OperationTask o = new OperationTask("Delete", mid,repo);
	   pool.execute(o,threadnum);
	   
	    
   }
   
   @Async
   public Optional<Movie> findMovieById(int id)
   {
	   
	   return repo.findById(id);
   }

   
   @Async
   public CompletableFuture<List<Movie>> findAllMovies() throws Exception
   {
	   
       //logg.info("fetching list of movies "+Thread.currentThread().getName());
       Thread.currentThread().sleep(900);
	   List<Movie> movies= repo.findAll();
	   return CompletableFuture.completedFuture(movies);  
   }
   
   @Async
    private List<Movie> parseJSONFile(final MultipartFile file) throws Exception
    {
    List<Movie> movies=new ArrayList<>();
  try
    	{
          final BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream()));

          String line;
          while((line=br.readLine())!=null)
          {
        	  if(line.charAt(0)=='[')
        	  {
        		  line = line.substring(1, line.length());
        	  }
        	  
        	  JSONObject json = new JSONObject(line) ;
        Movie m=new Movie();
       m.setName((String)json.getString("name"));
       m.setYearOfRelease((int)json.getInt("yearOfRelease"));
       m.setCast((String)json.getString("cast"));
       m.setDirector((String)json.getString("director"));
       movies.add(m);
          }
    	return movies;
    	}
    	catch(Exception e )
    	{
    		logg.error("Failed to parse JSON file{}",e);
    		throw new Exception("Failed to parse JSON file{}",e);
    	}

    }
}
