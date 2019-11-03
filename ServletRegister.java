package assignment;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/ServletRegister") 

public class ServletRegister extends HttpServlet{
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		PrintWriter pw=res.getWriter();
		
		RequestDispatcher header=req.getRequestDispatcher("header.html");
		RequestDispatcher footer=req.getRequestDispatcher("footer.html");
		
		ServletContext context=getServletContext();
		String dburl=context.getInitParameter("dburl");
		String dbusername=context.getInitParameter("dbusername");
		String dbpassword=context.getInitParameter("dbpassword");
		String accountname= req.getParameter("account-name");
		String accountpassword= req.getParameter("account-password");
		String confirmpassword= req.getParameter("confirm-password");
		String phonenumber= req.getParameter("phone-number");
		String eamilid= req.getParameter("eamil-id"); 
		int balance= Integer.parseInt(req.getParameter("account-balance"));
		//pw.println("I can reach here");
		
		String que = "INSERT INTO account(accname,password,cpassword,phoneno,email,balance) VALUES(?,?,?,?,?,?)";
	
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			
Connection conn = DriverManager.getConnection(dburl, dbusername, dbpassword);
PreparedStatement ps = conn.prepareStatement(que);
			ps.setString(1, accountname);
			ps.setString(2, accountpassword);
			ps.setString(3, confirmpassword);
			ps.setString(4, phonenumber);
			ps.setString(5, eamilid);
			ps.setInt(6, balance);
			pw.println("I can reach here 2");
			
			HttpSession hses=req.getSession();
			hses.setAttribute("NewUser", accountname);
			hses.setAttribute("Newbalance", balance);
			int result = ps.executeUpdate();
			if(result==1) {
				RequestDispatcher rs = req.getRequestDispatcher("registered.jsp");
				rs.forward(req, res);
				
			}
			else {
				pw.println("<h2 style='color:red'>Registration Failed</h2>");
			}
			
		} catch (Exception e) {
			pw.println(e);
		}
		//pw.println("I can reach here 4");
	}
}
