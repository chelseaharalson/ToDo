package model;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.LocalDateTime;
import java.time.Instant;
import java.time.ZoneId;

/**
 *
 * @author chelseametcalf
 */
public class ToDo {
    public Integer taskID;
    public String taskDescr;
    public String detail;
    public String dueDate;
    public String timeDue;
    public LocalDate currentDate;
    public LocalTime currentTime;
    public boolean isComplete;
    public boolean isOverdue;

    public ToDo(Integer pTaskID, String pTaskDescr, String pDetail, String pDueDate, String pTimeDue) {
        taskID = pTaskID;
        taskDescr = pTaskDescr;
        detail = pDetail;
        dueDate = pDueDate;
        timeDue = pTimeDue;
        currentDate = LocalDate.now();
        currentTime = LocalTime.now();
        isComplete = false;
        
        checkOverdue(dueDate, timeDue);
    }
    
    public void checkOverdue(String pDueDate, String pTimeDue) {
        // Setting if the task is overdue based on date and time
		try {
			Date dueDateObj = new SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH).parse(pDueDate);
			LocalDate localDueDate = new java.sql.Date(dueDateObj.getTime()).toLocalDate();
	        Date timeDueObj = new SimpleDateFormat("hh:mm a", Locale.ENGLISH).parse(pTimeDue);
	        Instant instant = Instant.ofEpochMilli(timeDueObj.getTime());
	        LocalTime localTimeDue = LocalDateTime.ofInstant(instant, ZoneId.systemDefault()).toLocalTime();
	        /*System.out.println("currentDate: " + currentDate);
	        System.out.println("currentTimeDue: " + currentTime);
	        System.out.println("localDueDate: " + localDueDate);
	        System.out.println("localTimeDue: " + localTimeDue);*/
	        if (currentDate.isAfter(localDueDate) /*&& currentTime.isAfter(localTimeDue)*/) {
	            isOverdue = true;
	            System.out.println("Task is overdue");
	        }
	        else {
	            isOverdue = false;
	            System.out.println("Task is NOT overdue");
	        }
		} catch (ParseException e) {
			//e.printStackTrace();
			System.out.println("Parse exception");
		}
    }
}
