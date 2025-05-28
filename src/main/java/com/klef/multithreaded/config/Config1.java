package com.klef.multithreaded.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@EnableAsync
@EnableScheduling
public class Config1 
{
	   public static MyThread t[] = new MyThread[10];
       public MyThread[] createThreads()
       {
    	     for ( int i=0;i<10;i++)
    	     {
    	    	   t[i] = new MyThread();
    	     }
    	     return t;
       }
      
	    
}
