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
        String dayAndTimeStr;
        for (Course course: courses) {
            dayAndTimeStr = course.getClassDays() + " " + course.getClassTime();
            classTimes.put(course.getCourseTitle(), dayAndTimeStr);
        }
        return classTimeAndDayMap;
    }


}
