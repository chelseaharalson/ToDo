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
		//doGet(request, response);
		//System.out.println("Received request");
		//System.out.println(request.getParameter("type"));
		PrintWriter out = response.getWriter();
		if (request.getParameter("type").equals("btnAddTask")) {
			System.out.println(request.getParameter("txbAddTaskDes"));
			String taskDesc = request.getParameter("txbAddTaskDes").trim();
			String details = request.getParameter("txtAreaAddDetails").trim();
			String hour = request.getParameter("addHour");
			String minute = request.getParameter("addMinute");
			String amPm = request.getParameter("addAmPm");
			String dueTime = hour + ":" + minute + amPm;
			String dueDate = request.getParameter("txbDueDate");
			boolean validDate = tdl.isValidDate(dueDate);
			if (validDate == true && !taskDesc.equals("")) {
				String addTask = tdl.addTask(taskDesc, details, dueDate, dueTime);
				System.out.println(tdl.showNotCompleted());
				out.println(addTask);
			}
			else if (validDate == false) {
				System.out.println("Invalid date.");
				out.println("{\n" + 
						"	\"validDate\": \"false\"\n" + 
						"}");
				/*out.println("<script type=\"text/javascript\">");
				out.println("alert('Invalid date. Please enter in mm/dd/yyyy format.');");
				out.println("</script>");*/
			}
			else if (taskDesc.equals("")) {
				System.out.println("Please enter a task description.");
				out.println("{\n" + 
						"	\"emptyTask\": \"false\"\n" + 
						"}");
			}
			//out.println(taskDesc + " " + details + " " + dueTime);
			//response.getWriter().write(addTask);
			//response.sendRedirect("index.jsp");
		}
		else if (request.getParameter("type").equals("btnDeleteAll")) {
		    tdl.deleteAllTasks();
		    out.println("{\n" + 
					"	\"deleteAll\": \"success\"\n" + 
					"}");
		}
		else if (request.getParameter("action3") != null) {
		    // Invoke action 3.
		}
		else {
			System.out.println("Confused");
		}
	}

}
