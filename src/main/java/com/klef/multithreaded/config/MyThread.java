package com.klef.multithreaded.config;

import com.klef.multithreaded.entity.Movie;
import com.klef.multithreaded.repository.DbRepository;

public class MyThread extends Thread{
	
	DbRepository repo;
	
	public void run()
	{
		 
	}
	
	public void save( Movie m)
	{
		  repo.save(m);
	}
	
	public void delete( int id)
	{
		repo.deleteById(id);
	}

}
