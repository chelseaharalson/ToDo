package controller;

import java.io.IOException;
import java.io.PrintWriter;

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
	private static ToDoList tdl = new ToDoList();

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
        String strTdl = tdl.showNotCompleted();
        response.getWriter().print(strTdl);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
		System.out.println("Received request");
		System.out.println(request.getParameter("type"));
		if (request.getParameter("type").equals("btnAddTask")) {
		//if (request.getParameter("btnAddTask") != null) {
			System.out.println(request.getParameter("txbAddTaskDes"));
			String taskDesc = request.getParameter("txbAddTaskDes").trim();
			String details = request.getParameter("txtAreaAddDetails").trim();
			String hour = request.getParameter("addHour");
			String minute = request.getParameter("addMinute");
			String amPm = request.getParameter("addAmPm");
			String dueTime = hour + ":" + minute + " " + amPm;
			String addTask = tdl.addTask(taskDesc, details, "5/22/1992", dueTime);
			PrintWriter out = response.getWriter();
			System.out.println(tdl.showNotCompleted());
			out.println(addTask);
			//out.println(taskDesc + " " + details + " " + dueTime);
			//response.getWriter().write(addTask);
			//response.sendRedirect("index.jsp");
		}
		else if (request.getParameter("action2") != null) {
		    // Invoke action 2.
		}
		else if (request.getParameter("action3") != null) {
		    // Invoke action 3.
		}
		else {
			System.out.println("Confused");
		}
	}

}
