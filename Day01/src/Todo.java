import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

public class Todo {
    private String task;
    private Date dueDate;
    private int hoursOfWork;

    public Todo(String task, Date dueDate, int hoursOfWork) {
        setTask(task);
        setDueDate(dueDate);
        setHoursOfWork(hoursOfWork);
    }

    public Todo(String dataLine) {
        String[] parts = dataLine.split(";");

        if (parts.length == 3) {
            String task = parts[0].trim();
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
                Date date = sdf.parse(parts[1].trim());
                int hoursOfWork = Integer.parseInt(parts[2].trim());

                setTask(task);
                setDueDate(date);
                setHoursOfWork(hoursOfWork);
            } catch (ParseException e) {
                System.out.println("Invalid date format.");
            }
        }
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        if (task == null || task.isEmpty()) {
            throw new IllegalArgumentException("Task cannot be null or empty.");
        } else if (task.length() < 2 || task.length() > 50) {
            throw new IllegalArgumentException("Task length must be between 2 and 50 characters long.");
        } else if (task.matches(".*[;|`].*")) {
            throw new IllegalArgumentException("Task cannot contain ; or | or `.");
        }
        this.task = task;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        int year = dueDate.getYear() + 1900;

        if (dueDate == null) {
            throw new IllegalArgumentException("Due Date cannot be null.");
        } else if(year < 1900 || year > 2100) {
            throw new IllegalArgumentException("Due Date must be between year 1900 and 2100.");
        }

        this.dueDate = dueDate;
    }

    public int getHoursOfWork() {
        return hoursOfWork;
    }

    public void setHoursOfWork(int hoursOfWork) {
        if (hoursOfWork < 0) {
            throw new IllegalArgumentException("Hours of Work must be 0 or greater.");
        }
        this.hoursOfWork = hoursOfWork;
    }

    public String toDataString() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
        return task + ";" + sdf.format(dueDate) +";" + hoursOfWork;
    }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/mm/dd");
        return task + ", " + sdf.format(dueDate) +", will take " + hoursOfWork + " hour(s) of work, ";
    }
}
