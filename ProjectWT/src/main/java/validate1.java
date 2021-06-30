

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class validate1
 */
@WebServlet("/validate1")
public class validate1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public validate1() {
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
		String password=request.getParameter("password");
		if(ID.equals("root") && password.equals("admin"))
		{
			request.getSession().setAttribute("ID", ID);
			request.getSession().removeAttribute("message2");
			request.getRequestDispatcher("aftertpologin.jsp").forward(request, response);
		}
		else {
			request.getSession().setAttribute("message2", "Invalid Credentials");
			request.getRequestDispatcher("tpologin.jsp").forward(request, response);
		}
	}

}
