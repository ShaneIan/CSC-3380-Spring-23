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
        classTimes = compileClassTimes();
    }

    public int calculateTotalHours() {
        int numScheduleHours = 0;
        for (Course course: courses) {
            numScheduleHours += course.getCreditHours();
        }
        return numScheduleHours;
    }

    public HashMap<String, String> compileClassTimes() {
        HashMap<String, String> classTimeAndDayMap = new HashMap<String, String>();
        String daysStr, timesStr;
        for (Course course: courses) {
            daysStr = course.getClassDays();
            timesStr = course.getClassTime();
            course.getClassTime()
            if (course.isLabCourse()) {
                Lab lab = course.getLab();
                daysStr += lab.getLabDays();
                timesStr += lab.getLabTimes();
            }
            classTimes.put(course.getCourseTitle(), dayAndTimeStr);
        }
        return classTimeAndDayMap;
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
