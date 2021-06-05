package resource;


import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Job {
    /**
     *
     */
    private final SimpleStringProperty task;
    private final SimpleStringProperty day;
    private final SimpleIntegerProperty hour;

    public Job(String day, String task, Integer hour) {
        this.day = new SimpleStringProperty(day);
        this.task = new SimpleStringProperty(task);
        this.hour = new SimpleIntegerProperty(hour);
    }

    public String getDay() {
        return day.get();
    }

    public String getTask() {
        return task.get();
    }

    public Integer getHour() {
        return hour.get();
    }

}
