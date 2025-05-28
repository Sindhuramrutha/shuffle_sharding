package com.klef.multithreaded.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.klef.multithreaded.entity.Movie;
import com.klef.multithreaded.service.ServerService;

@RestController
public class Controller1 {

	@Autowired
	private ServerService service;	

	@PostMapping(value="/movies",consumes= {MediaType.MULTIPART_FORM_DATA_VALUE},produces="application/json")
    public CompletableFuture< List<Movie> > saveMovies(@RequestParam(value="files") MultipartFile[] files) throws Exception
    {
		CompletableFuture<List<Movie>> movies = null;
		for(MultipartFile file: files)
		{
			 movies= service.saveMovies(file);
		}
		return movies;
    }
	
	@GetMapping("/user_signin")
	public ModelAndView donator_signin()
	{
		ModelAndView mv=new ModelAndView();
		mv.setViewName("login");
		return mv;
	}
	@GetMapping("/user_signup")
	public ModelAndView donator_signup()
	{
		ModelAndView mv=new ModelAndView();
		mv.setViewName("register");
		return mv;
	}
	@GetMapping("/logout")
	public void logout(HttpServletRequest req, HttpServletResponse res) throws Exception
	{
		HttpSession session = req.getSession(false);
		   session.invalidate();
		res.sendRedirect("user_signin");
	}
	@PostMapping("/user_signup_2")
	  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    String name=request.getParameter("name");
	    String email=request.getParameter("email");
	    String phonenumber=request.getParameter("phonenumber");
	    String password=request.getParameter("password");
//	    System.out.println(name);
//	    System.out.println(email);
//	    System.out.println(phonenumber);
//	    System.out.println(password);
	    
	    try {
	            Class.forName("com.mysql.jdbc.Driver");
	            
	            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/multithreadedserver","root","root");
	            
	            
	            String sql="insert into user(name,email,mobile,password) values(?,?,?,?)";
	            PreparedStatement pstmt=con.prepareStatement(sql);
	            pstmt.setString(1,name);
	            pstmt.setString(2,email);
	            pstmt.setString(3,phonenumber);
	            pstmt.setString(4, password);
	            int e=pstmt.executeUpdate();
	            
	            if(e>0) {
	            	HttpSession session  = request.getSession();
	                session.setAttribute("user_name", name);
	              //RequestDispatcher rd=request.getRequestDispatcher("donator_homepage");
	              response.sendRedirect("user_signin");
	              //System.out.println("Register Succesfully");
	              //rd.forward(request, response);
	            }
	            else {
	              RequestDispatcher rd=request.getRequestDispatcher("/user_login");
	              System.out.println("Register Failed");
	              rd.forward(request, response);
	            }
	            con.close();
	        }
	        catch(Exception e){
	            System.out.println(e);
	            
	        }
	  }
	
	@RequestMapping("/user_signin_2")
	  protected void doPost1(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    
	    String email=request.getParameter("email");
	    String password =request.getParameter("password");
//	    System.out.println(email);
//	    System.out.println(password);
	    
	     try {
	              Class.forName("com.mysql.jdbc.Driver");
	             
	              Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/multithreadedserver","root","root");
		          
	              String sql="select  * from  user where email=? and password=?";
	              PreparedStatement pstmt=con.prepareStatement(sql);
	              pstmt.setString(1,email);
	              pstmt.setString(2,password);
	              ResultSet rs=pstmt.executeQuery();
	              
	              
	              if(rs.next()) {
	            	 
	                HttpSession session  = request.getSession();
	                session.setAttribute("user_name", rs.getString("name"));
	            	 
	            	  response.sendRedirect("AllMovies");
	                //rd.forward(request, response);
	              }
	              else {
	                RequestDispatcher rd=request.getRequestDispatcher("/user_login");
	                 //System.out.println("Login Failed");
	                rd.forward(request, response);
	              }
	              con.close();
	          }
	          catch(Exception e){
	              System.out.println(e);
	              
	          }
	  }
	
	@PostMapping("/movie")
    public void saveMovie(@ModelAttribute("movie") Movie m, HttpServletResponse response ,HttpServletRequest req) throws Exception
    {
		HttpSession session = req.getSession(false);
		try {
            Class.forName("com.mysql.jdbc.Driver");
            
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/multithreadedserver","root","root");
	            
            String sql="select  * from  user where name = ?";
            PreparedStatement pstmt=con.prepareStatement(sql);
            pstmt.setString(1,session.getAttribute("user_name").toString());
            ResultSet rs=pstmt.executeQuery();
            
            
            if(rs.next()) 
            {
          	     int id = rs.getInt("id");
          	   service.saveMovie(id,m);
             
            }
            else {
              RequestDispatcher rd=req.getRequestDispatcher("/AllMovies");
            
              rd.forward(req, response);
            }
            con.close();
        }
        catch(Exception e){
            System.out.println(e);
            
        }
	                   
	            response.sendRedirect("/AllMovies");
    }
	
	@GetMapping("/deleteMovie/{id1}")
    public void deleteMovie(@PathVariable("id1")int id1 , HttpServletResponse response, HttpServletRequest req) throws Exception
    {
		
		HttpSession session = req.getSession(false);
		try {
            Class.forName("com.mysql.jdbc.Driver");
            
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/multithreadedserver","root","root");
	            
            String sql="select  * from  user where name = ?";
            PreparedStatement pstmt=con.prepareStatement(sql);
            pstmt.setString(1,session.getAttribute("user_name").toString());
            ResultSet rs=pstmt.executeQuery();
            
            
            if(rs.next()) 
            {
          	     int id = rs.getInt("id");
          	   service.deleteMovie(id,id1);
             
            }
            else {
              RequestDispatcher rd=req.getRequestDispatcher("/AllMovies");
            
              rd.forward(req, response);
            }
            con.close();
        }
        catch(Exception e){
            System.out.println(e);
            
        }
	                   
	            response.sendRedirect("/AllMovies");
    }
	@RequestMapping(value="/AddMovie" ,method = RequestMethod.GET)
	public ModelAndView AddMovie(Model model)
	{
		return new ModelAndView("addMovie","movie",new Movie());
	}
	
	@RequestMapping(value="/")
	public ModelAndView home()
	{
		ModelAndView m=new ModelAndView();   
	     m.setViewName("index");  
	     return m;
	}
	@RequestMapping(value="/login")
	public ModelAndView login()
	{
		ModelAndView m=new ModelAndView();   
	     m.setViewName("login");  
	     return m;
	}
	@RequestMapping(value="/register")
	public ModelAndView register()
	{
		ModelAndView m=new ModelAndView();   
	     m.setViewName("register");  
	     return m;
	}
	
	

@GetMapping(value="/AllMovies",produces="application/json")
public ModelAndView getAllMovies() throws Exception
{
	CompletableFuture<List<Movie>> movies= service.findAllMovies();
     ModelAndView m=new ModelAndView();   
     m.setViewName("allMovies");  //
     m.addObject("moviedata",movies); 
     return m;

}
}
