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

import com.google.gson.Gson;

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
        
        addTask("This is my first task", "Details!!!!!!", "5/22/2017", "2:22pm");
        addTask("This is my second task", "Details2!!!!!!", "5/22/2017", "2:22pm");
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
    
    public void editTask(Integer pTaskID, String pTaskDescr, String pDetail, String pDueDate, String pTimeDue) {
        for (int i = 0; i < toDoList.size(); i++) {
            if (toDoList.get(i).taskID == pTaskID) {
                toDoList.get(i).taskDescr = pTaskDescr;
                toDoList.get(i).detail = pDetail;
                toDoList.get(i).dueDate = pDueDate;
                toDoList.get(i).timeDue = pTimeDue;
                return;
            }
        }
    }
    
    public void completeTask(Integer pTaskID) {
        for (int i = 0; i < toDoList.size(); i++) {
            if (toDoList.get(i).taskID == pTaskID) {
                toDoList.get(i).isComplete = true;
                return;
            }
        }
    }
    
    public void deleteAllTasks() {
        toDoList.clear();
        taskCount = 0;
    }
    
    public void completeAllTasks() {
        for (int i = 0; i < toDoList.size(); i++) {     // Removing a specific task
            toDoList.get(i).isComplete = true;
        }
    }
    
    public void showAll() {
        // Return todo list as json
    }
    
    public String showNotCompleted() {
        ArrayList<ToDo> notCompletedList = new ArrayList<ToDo>();
        for (int i = 0; i < toDoList.size(); i++) {
            if (toDoList.get(i).isComplete == false) {
                notCompletedList.add(toDoList.get(i));
            }
        }
        // Return not completed list as json
        //Gson gson = new Gson();
        //String json = gson.toJson(notCompletedList);
        
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
    		resultString += "{\"id\":\"" + td.taskID + "\",\"taskDescr\":\"" + td.taskDescr 
    				+ "\",\"detail\":\"" + td.detail + "\",\"dueDate\":\"" + td.dueDate 
    				+ "\",\"timeDue\":\"" + td.timeDue
    				+ "\",\"isComplete\":\"" + td.isComplete + "\",\"isOverdue\":\"" + td.isOverdue 
    				+ "\"}";
	     return resultString;
    }
}