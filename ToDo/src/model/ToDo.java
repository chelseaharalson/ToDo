package model;
import java.text.DateFormat;
import java.util.Date;
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
    public Date dueDate;
    public Date timeDue;
    public LocalDate currentDate;
    public LocalTime currentTime;
    public boolean isComplete;
    public boolean isOverdue;

    public ToDo(Integer pTaskID, String pTaskDescr, String pDetail, Date pDueDate, Date pTimeDue) {
        taskID = pTaskID;
        taskDescr = pTaskDescr;
        detail = pDetail;
        dueDate = pDueDate;
        timeDue = pTimeDue;
        currentDate = LocalDate.now();
        currentTime = LocalTime.now();
        isComplete = false;
        
        checkOverdue();
    }
    
    public void checkOverdue() {
        // Setting if the task is overdue based on date and time
        LocalDate localDueDate = new java.sql.Date(dueDate.getTime()).toLocalDate();
        Instant instant = Instant.ofEpochMilli(timeDue.getTime());
        LocalTime localTimeDue = LocalDateTime.ofInstant(instant, ZoneId.systemDefault()).toLocalTime();
        if (currentDate.isAfter(localDueDate) && currentTime.isAfter(localTimeDue)) {
            isOverdue = true;
        }
        else {
            isOverdue = false;
        }
    }
}
