

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 * Servlet implementation class addcompany
 */
@WebServlet("/addcompany")
@MultipartConfig(maxFileSize = 16177215)
public class addcompany extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public addcompany() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		String Company_Id=request.getParameter("ID");
		String CompanyNames=request.getParameter("name");
		String CPI=request.getParameter("cpi");
		String Comp_info = request.getParameter("file");
		Connection connect = null; // connection to the database
        
        try {
            // connects to the database
        	Class.forName("com.mysql.cj.jdbc.Driver");
        	connect=DriverManager.getConnection("jdbc:mysql://localhost:3306/first","root","anuj3107");
 
            // constructs SQL statement
            PreparedStatement pst = connect.prepareStatement("insert into companies values(?,?,?,?);");
            pst.setString(1, Company_Id);
            pst.setString(2, CompanyNames);
            pst.setString(3, CPI);
            pst.setString(4, Comp_info);
            int r1=pst.executeUpdate(); 
            if(r1>0)
            {
            	request.getSession().removeAttribute("message6");
				request.getRequestDispatcher("companyupdation.jsp").forward(request, response);
            }
            }
        catch(Exception e)
        {
        	System.out.println(e);
        	request.getSession().setAttribute("message6", "Company with this ID already exist");
			request.getRequestDispatcher("companyupdation.jsp").forward(request, response);
        }
     }

}
