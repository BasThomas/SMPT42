package smpt42.nl.printmanager.model;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by jeroen on 22-1-15.
 */
public class Task {

    private final int itemID;
    private final String taskType;
    private Date dateCompleted;

    public Task(String taskType, int taskOrder, Date dateCompleted) {
        this.taskType = taskType;
        this.itemID = taskOrder;
        this.dateCompleted = dateCompleted;
    }

    public String getTaskType() {
        return taskType;
    }

    public int getOrderID() {
        return itemID;
    }

    public Date getDateCompleted() {
        return dateCompleted;
    }

    public void setDateCompleted(Date dateCompleted) {
        this.dateCompleted = dateCompleted;
    }
}
