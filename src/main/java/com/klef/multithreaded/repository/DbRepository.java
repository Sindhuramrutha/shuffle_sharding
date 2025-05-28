package com.klef.multithreaded.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.klef.multithreaded.entity.Movie;

public interface DbRepository extends JpaRepository<Movie,Integer> 
{
	
}
