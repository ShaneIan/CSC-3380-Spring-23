package ScheduleObjectBuilder;

import CourseObjectBuilder.Course;
import java.util.*;

public class ScheduleBuilderImpl implements ScheduleBuilder {

    //Takes a preexisting schedule array and the course to be added
    //and iterates through the course's SessionDayTimeMap to add the class times per day to the schedule array by using
    //the startTimeIndex and endTimeIndex for each day in the SessionDayTimeMap, and changing the schedule array 
    //timeslot string to the course title
    @Override
    public void addCourse(Schedule schedule, Course course) {
        HashMap<Integer, int[]> classTimes = course.getSessionDayTimeMap();
        int day, startTime, endTime;
        String[][] scheduleMatrix = schedule.getScheduleMatrix();
        for (Map.Entry<Integer, int[]> entry: classTimes.entrySet()) {
                day = entry.getKey();
                startTime = entry.getValue()[0];
                endTime = entry.getValue()[1];
                for (int i = startTime; i < endTime; i++) {
                        scheduleMatrix[day][i] = course.getCourseNumber();
                }
        }
        //handles any course with labs, taking into account the separate times for the lab and the class
        if (course.isLabCourse()) {
                HashMap<Integer, int[]> labTimes = course.getSessionDayTimeMap();
                for (Map.Entry<Integer, int[]> entry: labTimes.entrySet()) {
                        day = entry.getKey();
                        startTime = entry.getValue()[0];
                        endTime = entry.getValue()[1];
                        for (int i = startTime; i < endTime; i++) {
                            scheduleMatrix[day][i] = course.getCourseNumber();
                        }
                }
        }
        schedule.getCourses().add(course);
    }

    //Takes preexisting a schedule array and the course to be checked for conflicts
    //and iterates through the course's SessionDayTimeMap to see if the class times per day
    //coincide with any class times in the schedule array by checking all time indexes between (and including) 
    //the startTimeIndex and endTimeIndex for each day in the SessionDayTimeMap, where any string other than "-" 
    //indicates a filled timeslot
    @Override
    public boolean checkCourseTimesConflict(Schedule schedule, Course course) {
        HashMap<Integer, int[]> classTimes = course.getSessionDayTimeMap();
        int day, startTime, endTime;
        String[][] scheduleMatrix = schedule.getScheduleMatrix();
        for (Map.Entry<Integer, int[]> entry: classTimes.entrySet()) {
                day = entry.getKey();
                startTime = entry.getValue()[0];
                endTime = entry.getValue()[1];
                for (int i = startTime; i < endTime; i++) {
                        if (!scheduleMatrix[day][i].equals("-")) {
                                return false;
                        }
                }
        }
        //handles any course with labs, taking into account the separate times for the lab and the class
        if (course.isLabCourse()) {
                HashMap<Integer, int[]> labTimes = course.getSessionDayTimeMap();
                for (Map.Entry<Integer, int[]> entry: labTimes.entrySet()) {
                        day = entry.getKey();
                        startTime = entry.getValue()[0];
                        endTime = entry.getValue()[1];
                        for (int i = startTime; i < endTime; i++) {
                                if (!scheduleMatrix[day][i].equals("-")) {
                                        return false;
                                }
                        }
                }
        }
        return true;
    }

    //Takes a preexisting schedule array and the course to be removed
    //and iterates through the course's SessionDayTimeMap to remove the class times per day from the schedule array by using
    //the startTimeIndex and endTimeIndex for each day in the SessionDayTimeMap, and changing the schedule array 
    //timeslot string to to "-"
    @Override
    public void removeCourse(Schedule schedule, Course course) {
        //Check if course being removed is in schedule, return if not
        if (!containsCourse(schedule, course.getCourseNumber())) {
            return;
        }
        HashMap<Integer, int[]> classTimes = course.getSessionDayTimeMap();
        String[][] scheduleMatrix = schedule.getScheduleMatrix();
        int day, startTime, endTime;
        for (Map.Entry<Integer, int[]> entry: classTimes.entrySet()) {
                day = entry.getKey();
                startTime = entry.getValue()[0];
                endTime = entry.getValue()[1];
                for (int i = startTime; i < endTime; i++) {
                    if (scheduleMatrix[day][i] == course.getCourseNumber())
                        scheduleMatrix[day][i] = "-";
                }
        }
        //handles any course with labs, taking into account the separate times for the lab and the class
        if (course.isLabCourse()) {
                HashMap<Integer, int[]> labTimes = course.getSessionDayTimeMap();
                for (Map.Entry<Integer, int[]> entry: labTimes.entrySet()) {
                        day = entry.getKey();
                        startTime = entry.getValue()[0];
                        endTime = entry.getValue()[1];
                        for (int i = startTime; i < endTime; i++) {
                            if (scheduleMatrix[day][i] == course.getCourseNumber())
                                scheduleMatrix[day][i] = "-";
                        }
                }
        }
        schedule.getCourses().remove(course);
    }

    
    @Override
    public boolean containsCourse(Schedule schedule, String courseCode) {
        for (Course course: schedule.getCourses()) {
            if (course.getCourseNumber().equals(courseCode)) {
                    return true;
            }
        }
        return false;
    }

}
