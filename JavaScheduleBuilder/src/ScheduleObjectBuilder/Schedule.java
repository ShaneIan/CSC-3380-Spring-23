package ScheduleObjectBuilder;
import CourseObjectBuilder.*;
import java.util.*;

public class Schedule {
    private ArrayList<Course> courses;
    private int totalHours;
    private HashMap<String, String> classTimes;
    private String[][] scheduleMatrix;


    public Schedule(ArrayList<Course> courses) {
        courses = this.courses;
        totalHours = calculateTotalHours();
        scheduleMatrix = BuildScheduleMatrix();
    }

    public Schedule() {
        courses = new ArrayList<>();
        totalHours = 0;
        scheduleMatrix = BuildScheduleMatrix();
    }

    public String[][] BuildScheduleMatrix() {
        String[][] scheduleArray = new String[5][31];
        for (String[] Day: scheduleArray)
        {
                for (String TimeSlot: Day){
                        TimeSlot = "-";
                }
                        
        }
        return scheduleArray;
    }

    //Takes preexisting a schedule array and the course to be checked for conflicts
    //and iterates through the course's SessionDayTimeMap to see if the class times per day
    //coincide with any class times in the schedule array by checking all time indexes between (and including) 
    //the startTimeIndex and endTimeIndex for each day in the SessionDayTimeMap, where any string other than "-" 
    //indicates a filled timeslot
    public boolean checkClassTimesConflict(Course newClass) {
        HashMap<Integer, int[]> classTimes = newClass.getSessionDayTimeMap();
        int day, startTime, endTime;
        for (Map.Entry<Integer, int[]> entry: classTimes.entrySet()) {
                day = entry.getKey();
                startTime = entry.getValue()[0];
                endTime = entry.getValue()[1];
                for (int i = startTime; i <= endTime; i++) {
                        if (!scheduleMatrix[day][i].equals("-")) {
                                return false;
                        }
                }
        }
        if (newClass.isLabCourse()) {
                HashMap<Integer, int[]> labTimes = newClass.getSessionDayTimeMap();
                for (Map.Entry<Integer, int[]> entry: labTimes.entrySet()) {
                        day = entry.getKey();
                        startTime = entry.getValue()[0];
                        endTime = entry.getValue()[1];
                        for (int i = startTime; i <= endTime; i++) {
                                if (!scheduleMatrix[day][i].equals("-")) {
                                        return false;
                                }
                        }
                }
        }
        return true;
    }

    //Takes a preexisting schedule array and the course to be added
    //and iterates through the course's SessionDayTimeMap to add the class times per day to the schedule array by using
    //the startTimeIndex and endTimeIndex for each day in the SessionDayTimeMap, and changing the schedule array 
    //timeslot string to the course title
    public void AddClass(Course newClass) {
        HashMap<Integer, int[]> classTimes = newClass.getSessionDayTimeMap();
        int day, startTime, endTime;
        for (Map.Entry<Integer, int[]> entry: classTimes.entrySet()) {
                day = entry.getKey();
                startTime = entry.getValue()[0];
                endTime = entry.getValue()[1];
                for (int i = startTime; i <= endTime; i++) {
                        scheduleMatrix[day][i] = newClass.getCourseTitle();
                }
        }
        if (newClass.isLabCourse()) {
                HashMap<Integer, int[]> labTimes = newClass.getSessionDayTimeMap();
                for (Map.Entry<Integer, int[]> entry: labTimes.entrySet()) {
                        day = entry.getKey();
                        startTime = entry.getValue()[0];
                        endTime = entry.getValue()[1];
                        for (int i = startTime; i <= endTime; i++) {
                            scheduleMatrix[day][i] = newClass.getCourseTitle();
                        }
                }
        }
        courses.add(newClass);
    }

    public int calculateTotalHours() {
        int numScheduleHours = 0;
        for (Course course: courses) {
            numScheduleHours += course.getCreditHours();
        }
        return numScheduleHours;
    }

    public ArrayList<Course> getCourses() {
        return courses;
    }

    public HashMap<String, String> getClassTimes() {
        return classTimes;
    }

    public int getTotalCreditHours() {
        return totalHours;
    }
}
