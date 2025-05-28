import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
@WebServlet(value = "/checkLogin")
public class checkLogin extends HttpServlet
{
  public void service(HttpServletRequest req, HttpServletResponse res) throws IOException
  {
	 res.setContentType("text/html");  
	 PrintWriter out = res.getWriter();
    String name = req.getParameter("name");
    String pass = req.getParameter("pass");
    HttpSession session = req.getSession();
    session.setAttribute("name", name);
    session.setAttribute("pass", pass);
    
    try {
    	Connection con = null;
		Class.forName("com.mysql.jdbc.Driver");
		System.out.println("Driver Class Loaded");
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/multithreadedserver","root","root");
		System.out.println("Connection Established");
		 PreparedStatement pstmt = con.prepareStatement("select * from registration where name=? and pass=?");
		  pstmt.setString(1,name);
		  pstmt.setString(2,pass);
		  ResultSet rs = pstmt.executeQuery();
		  if(rs.next())
		  {
			  if(name.equals("name") && pass.equals("pass")) {
			 session.setAttribute("name",name);
			 session.setAttribute("pass", pass);
			 session.setMaxInactiveInterval(100);
			
			  }
			  out.println("Login sucessful"+name);
			 res.sendRedirect("/AllMovies");
			
		  } 
		  else {
			  out.println("Login invalid");
			  RequestDispatcher rd= req.getRequestDispatcher("/login");
			  rd.include(req, res);
		  }
    }
    catch(Exception e) {
    	System.out.println(e);
    }
  }
}