package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.ToDo;
import model.ToDoList;
import org.json.JSONObject;
import org.json.JSONArray;

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
			String dueTime = hour + ":" + minute + " " + amPm;
			String dueDate = request.getParameter("txbDueDate");
			boolean validDate = tdl.isValidDate(dueDate);
			if (validDate == true && !taskDesc.trim().equals("")) {
				String addTask = tdl.addTask(taskDesc, details, dueDate, dueTime);
				System.out.println(tdl.showNotCompleted());
				out.println(addTask);
			}
			else if (validDate == false) {
				System.out.println("Invalid date.");
				out.println("{\n" + 
						"	\"validDate\": \"false\"\n" + 
						"}");
			}
			else if (taskDesc.trim().equals("")) {
				System.out.println("Please enter a task description.");
				out.println("{\n" + 
						"	\"emptyTask\": \"false\"\n" + 
						"}");
			}
		}
		else if (request.getParameter("type").equals("btnDeleteAll")) {
		    tdl.deleteAllTasks();
		    out.println("{\n" + 
					"	\"deleteAll\": \"success\"\n" + 
					"}");
		}
		else if (request.getParameter("type").equals("btnDeleteTask")) {
			String strId = request.getParameter("rowId").trim();
			Integer id = Integer.parseInt(strId);
		    tdl.deleteTask(id);
		    out.println("{\n" + 
					"	\"deleteTask\": \"success\"\n" + 
					"}");
		}
		else if (request.getParameter("type").equals("btnSubmitEditTask")) {
			String strId = request.getParameter("rowId");
			System.out.println("ID: " + strId);
			Integer id = Integer.parseInt(strId);
			String taskDesc = request.getParameter("txbEditTaskDes").trim();
			String details = request.getParameter("txtAreaEditDetails").trim();
			String hour = request.getParameter("editHour");
			String minute = request.getParameter("editMinute");
			String amPm = request.getParameter("editAmPm");
			String dueTime = hour + ":" + minute + " " + amPm;
			String dueDate = request.getParameter("txbEditDueDate");
			boolean validDate = tdl.isValidDate(dueDate);
			if (validDate == true && !taskDesc.trim().equals("")) {
				boolean editSuccess = tdl.editTask(id, taskDesc, details, dueDate, dueTime);
				if (editSuccess == true) {
					ToDo t = tdl.getToDo(id);
					boolean overdue = false;
					if (t != null) {
						overdue = t.isOverdue;
					}
					System.out.println(tdl.showNotCompleted());
					out.println("{\n" + 
							"	\"successEdit\": \"true\"," + 
							"	\"overdue\": \"" + overdue + "\"" +
							"}");
				}
				else {
					out.println("{\n" + 
							"	\"successEdit\": \"false\"\n" + 
							"}");
				}
			}
			else if (validDate == false) {
				System.out.println("Invalid date.");
				out.println("{\n" + 
						"	\"validDate\": \"false\"\n" + 
						"}");
			}
			else if (taskDesc.trim().equals("")) {
				System.out.println("Please enter a task description.");
				out.println("{\n" + 
						"	\"emptyTask\": \"false\"\n" + 
						"}");
			}
		}
		else if (request.getParameter("type").equals("btnViewDetails")) {
			String strId = request.getParameter("rowId");
			Integer id = Integer.parseInt(strId);
			String resultString = tdl.escapeQuotes(tdl.getDetails(id));
			out.println("{\n" + 
					"	\"details\": \"" + resultString + "\"\n" + 
					"}");
		}
		else if (request.getParameter("type").equals("btnComplete")) {
			String dataStr = request.getParameter("dataStr");
			JSONObject obj = new JSONObject(dataStr);
		    JSONArray checkedData = obj.getJSONArray("checkedData");
		    for (int i = 0; i < checkedData.length(); i++) {
		      JSONObject task = checkedData.getJSONObject(i);
		      String strId = task.getString("id");
		      Integer id = Integer.parseInt(strId);
		      boolean completeStatus = Boolean.parseBoolean(task.getString("checked"));
		      tdl.completeTask(id, completeStatus);
		    }
		    if (request.getParameter("viewMode").equals("showAll")) {
		    		String strTdl = tdl.showAll();
		        response.getWriter().print(strTdl);
		    }
		    else {
		    		String strTdl = tdl.showNotCompleted();
		        response.getWriter().print(strTdl);
		    }
		}
		else if (request.getParameter("type").equals("btnCompleteAll")) {
			tdl.completeAllTasks();
		    if (request.getParameter("viewMode").equals("showAll")) {
		    		String strTdl = tdl.showAll();
		        response.getWriter().print(strTdl);
		    }
		    else {
		    		String strTdl = tdl.showNotCompleted();
		        response.getWriter().print(strTdl);
		    }
		}
		else if (request.getParameter("type").equals("btnShowAll")) {
		    		String strTdl = tdl.showAll();
		        response.getWriter().print(strTdl);
		}
		else if (request.getParameter("type").equals("btnShowNotCompleted")) {
	    		String strTdl = tdl.showNotCompleted();
	        response.getWriter().print(strTdl);
		}
		else {
			System.out.println("Confused");
		}
		out.close();
	}

}
