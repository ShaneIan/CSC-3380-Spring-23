package ScheduleObjectBuilder;
import CourseObjectBuilder.*;
import java.util.*;

public class Schedule {
    private Course[] courses;
    private int totalHours;
    private HashMap<String, String> classTimes;

    public Schedule(Course[] courses) {
        courses = this.courses;
        totalHours = calculateTotalHours();
    }

    public int calculateTotalHours() {
        int numScheduleHours = 0;
        for (Course course: courses) {
            numScheduleHours += course.getCreditHours();
        }
        return numScheduleHours;
    }

    public Course[] getCourses() {
        return courses;
    }

    public HashMap<String, String> getClassTimes() {
        return classTimes;
    }

    public int getTotalCreditHours() {
        return totalHours;
    }
}
