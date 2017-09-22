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
        
        /*try {
			checkOverdue();
		} catch (ParseException e) {
			e.printStackTrace();
		}*/
    }
    
    public void checkOverdue() throws ParseException {
        // Setting if the task is overdue based on date and time
      	Date dueDateObj = new SimpleDateFormat("yyyy-mm-dd", Locale.ENGLISH).parse(dueDate);
        LocalDate localDueDate = new java.sql.Date(dueDateObj.getTime()).toLocalDate();
        
        Date timeDueObj = new SimpleDateFormat("hh:mm a", Locale.ENGLISH).parse(timeDue);
        Instant instant = Instant.ofEpochMilli(timeDueObj.getTime());
        LocalTime localTimeDue = LocalDateTime.ofInstant(instant, ZoneId.systemDefault()).toLocalTime();
        if (currentDate.isAfter(localDueDate) && currentTime.isAfter(localTimeDue)) {
            isOverdue = true;
        }
        else {
            isOverdue = false;
        }
    }
}
