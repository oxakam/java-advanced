package web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/users")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		List<User> users = List.of(new User("Sommer","warm"), new User("Winter","cold"));		
		
		Gson gson = new Gson();							
		String json = gson.toJson(users);				//json format
		
		response.setContentType("application/json");	//display on http://localhost:8080/WebGradle/users
		response.getWriter().println(json);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		doGet(request, response);
		
		var name = request.getParameter("nameName");
		var password = request.getParameter("passName");
		
		response.setContentType("text/html");
		response.getWriter().println("<html>Form submitted: " + name + "</html>");
		
		System.out.println(name + ": " + password);
	}

}
