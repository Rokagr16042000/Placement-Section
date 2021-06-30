

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
/**
 * Servlet implementation class addstudent
 */
@WebServlet("/addstudent")
public class addstudent extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public addstudent() {
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
		String ID=request.getParameter("ID");
		String CompanyID=request.getParameter("CompanyID");
		String CompanyName=request.getParameter("CompanyName");
		String Package=request.getParameter("Package");
		
		Connection connect = null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connect=DriverManager.getConnection("jdbc:mysql://localhost:3306/first","root","anuj3107");
			PreparedStatement pst = connect.prepareStatement("insert into candidate_to_company values(?,?,?,?);");
            pst.setString(1, ID);
            pst.setString(2, CompanyID);
            pst.setString(3, CompanyName);
            pst.setString(4, Package);
            int r=pst.executeUpdate();
            if(r>0)
            {
            	request.getSession().removeAttribute("message3");
            	request.getRequestDispatcher("placed.jsp").forward(request, response);
            	System.out.println("added");
            }
            else{
            	request.getSession().setAttribute("message3", "Student not added! Enter correct details");
    			request.getRequestDispatcher("placed.jsp").forward(request, response);
    			System.out.println("not added");
            }
		}
		catch (SQLException ex) {
			request.getSession().setAttribute("message3", "Student not added! Enter correct details");
			request.getRequestDispatcher("placed.jsp").forward(request, response);
			System.out.println("not added sql");
        } catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
            if (connect != null) {
                // closes the database connection
                try {
                    connect.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }

        }
	}

}
