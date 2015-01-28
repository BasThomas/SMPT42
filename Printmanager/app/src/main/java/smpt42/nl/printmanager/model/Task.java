package smpt42.nl.printmanager.model;

import java.util.Date;

/**
 * Created by jeroen on 22-1-15.
 */
public class Task {

    private final int taskOrder;
    private final String taskType;
    private Date dateCompleted;
    private int scan_id;

    public Task(String taskType, int taskOrder, Date dateCompleted, int scan_id) {
        this.taskType = taskType;
        this.taskOrder = taskOrder;
        this.dateCompleted = dateCompleted;
        this.scan_id = scan_id;
    }

    public String getTaskType() {
        return taskType;
    }

    public int getOrderID() {
        return taskOrder;
    }

    public Date getDateCompleted() {
        return dateCompleted;
    }

    public int getScan_id() {
        return scan_id;
    }
}
