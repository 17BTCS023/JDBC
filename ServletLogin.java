package assignment;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 * Servlet implementation class ServletLogin
 * This servlet will collect the information from the login page 
 * and let the user log in to next page, if the credentials are correct, 
 * where the balance will be displayed to the user.
 * If not, the user will be asked to register or try again",
 */
@WebServlet(description = "loginServlet", 
urlPatterns = { "/ServletLogin" })
public class ServletLogin extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		PrintWriter pw=res.getWriter();
		
		RequestDispatcher header=req.getRequestDispatcher("header.html");
		RequestDispatcher footer=req.getRequestDispatcher("footer.html");
		
		ServletContext context=getServletContext();
		String dburl=context.getInitParameter("dburl");
		String dbusername=context.getInitParameter("dbusername");
		String dbpassword=context.getInitParameter("dbpassword");
		String username=req.getParameter("username");
		String password=req.getParameter("password");
		String query = "SELECT * FROM account WHERE accname=? AND password=? ";
		
		try {
			
			Connection conn = DriverManager.getConnection(dburl, dbusername, dbpassword);
			PreparedStatement pst = conn.prepareStatement(query);
			pst.setString(1, username);
			pst.setString(2, password);
			ResultSet result  = pst.executeQuery();
			if(result.next())
			{			
				pw.println("Weclcome "+username +"! Login Successful!");
			}
			else {
				pw.println("Record Not Found. Register or try Again!");
			}
		}
		catch(Exception e) {
			pw.println(e);
		}
	}
}
