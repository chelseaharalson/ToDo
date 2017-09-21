package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.ArrayList;
import model.ToDo;
import model.ToDoList;

/**
 * Servlet implementation class DataTableServlet
 */
@WebServlet("/DataTableServlet")
public class DataTableServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public DataTableServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//response.setContentType("application/json");
		response.setContentType("text/plain");
        
        ToDoList tdlObj = new ToDoList();
        String tdl = tdlObj.showNotCompleted();

        //Gson gson = new GsonBuilder().setPrettyPrinting().create();
        // Convert Java Object to Json
        //String json = gson.toJson(tdl);

        response.getWriter().print(tdl);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
