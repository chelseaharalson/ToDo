package model;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.Locale;
import java.util.ArrayList;
import java.util.Calendar;

/**
 *
 * @author chelseametcalf
 */
public class ToDoList {
    private ArrayList<ToDo> toDoList;
    private Integer taskCount;
    
    public ToDoList() {
        toDoList = new ArrayList<ToDo>();
        taskCount = 0;
        
        addTask("Complete tasks", "Get checked values and transfer to other table", "05/22/2017", "4:22 AM");
        addTask("Complete all tasks", "", "09/25/2017", "4:00 PM");
        addTask("Show not completed list", "...", "09/25/2017", "4:00 PM");
        addTask("Show all list", "...", "09/25/2017", "4:00 PM");
        addTask("If overdue, turn red", "...", "09/23/2017", "4:00 PM");
        addTask("JUNIT tests", "...", "09/23/2017", "7:00 PM");
    }
    
    public String addTask(String pTaskDescr, String pDetail, String pDueDate, String pTimeDue) {
        Integer pTaskID = taskCount;
        taskCount++;        // Increment taskID so each new task has a unique ID
        System.out.println("Adding new task: " + pTaskDescr);
        toDoList.add(new ToDo(pTaskID, pTaskDescr, pDetail, pDueDate, pTimeDue));
        String resultString = toDoObjToAjaxString(toDoList.get(toDoList.size()-1));
        return resultString;
    }
    
    public void deleteTask(Integer pTaskID) {
        for (int i = 0; i < toDoList.size(); i++) {     // Removing a specific task
            if (toDoList.get(i).taskID == pTaskID) {
                toDoList.remove(i);
                return;
            }
        }
    }
    
    public boolean editTask(Integer pTaskID, String pTaskDescr, String pDetail, String pDueDate, String pTimeDue) {
        for (int i = 0; i < toDoList.size(); i++) {
            if (toDoList.get(i).taskID == pTaskID) {
                toDoList.get(i).taskDescr = pTaskDescr;
                toDoList.get(i).detail = pDetail;
                toDoList.get(i).dueDate = pDueDate;
                toDoList.get(i).timeDue = pTimeDue;
                return true;
            }
        }
        return false;
    }
    
    public void completeTask(Integer pTaskID, boolean completeStatus) {
        for (int i = 0; i < toDoList.size(); i++) {
            if (toDoList.get(i).taskID == pTaskID) {
                toDoList.get(i).isComplete = completeStatus;
                return;
            }
        }
    }
    
    public void deleteAllTasks() {
        toDoList.clear();
        taskCount = 0;
    }
    
    public void completeAllTasks() {
        for (int i = 0; i < toDoList.size(); i++) {
            toDoList.get(i).isComplete = true;
        }
    }
    
    public String showAll() {
	    	ArrayList<ToDo> allTaskList = new ArrayList<ToDo>();
        for (int i = 0; i < toDoList.size(); i++) {
        		toDoList.get(i).checkOverdue();
            allTaskList.add(toDoList.get(i));
        }

        String resultString = "{\"data\":[";
        for (int i = 0; i < allTaskList.size(); i++) {
        		resultString += toDoObjToAjaxString(allTaskList.get(i));
        		if (i+1 < allTaskList.size()) {
        			resultString += ",";
        		}
        }
        resultString += "]}";
        System.out.println(resultString);
        return resultString;
    }
    
    public String showNotCompleted() {
        ArrayList<ToDo> notCompletedList = new ArrayList<ToDo>();
        for (int i = 0; i < toDoList.size(); i++) {
        		toDoList.get(i).checkOverdue();
            if (toDoList.get(i).isComplete == false) {
                notCompletedList.add(toDoList.get(i));
            }
        }

        String resultString = "{\"data\":[";
        for (int i = 0; i < notCompletedList.size(); i++) {
        		resultString += toDoObjToAjaxString(notCompletedList.get(i));
        		if (i+1 < notCompletedList.size()) {
        			resultString += ",";
        		}
        }
        resultString += "]}";
        System.out.println(resultString);
        return resultString;
    }
    
    public String toDoObjToAjaxString(ToDo td) {
	    	String resultString = "";
    		resultString += "{\"id\":\"" + td.taskID + "\",\"taskDescr\":\"" + escapeQuotes(td.taskDescr) 
    				+ "\",\"detail\":\"" + escapeQuotes(td.detail) + "\",\"dueDate\":\"" + td.dueDate 
    				+ "\",\"timeDue\":\"" + td.timeDue
    				+ "\",\"isComplete\":\"" + td.isComplete + "\",\"isOverdue\":\"" + td.isOverdue 
    				+ "\"}";
	     return resultString;
    }
    
    public String escapeQuotes(String input) {
    		return input.replaceAll("\"", "\\\\\"");
    }
    
    public String getDetails(Integer rowID) {
    	String resultString = "";
    		for (int i = 0; i < toDoList.size(); i++) {
            if (toDoList.get(i).taskID == rowID) {
                resultString = toDoList.get(i).detail;
                return resultString;
            }
        }
    		return "";
    }
    
    public boolean isValidDate(String input) {
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        String formattedDate;
		try {
			formattedDate = sdf.format(sdf.parse(input));
			if (formattedDate.equals(input)) {
	            return true;
	        } 
	        else {
	            return false;
	        }
		} catch (ParseException e) {
			System.out.println("Invalid date");
			return false;
		}
        
    }
}
