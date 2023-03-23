package ScheduleObjectBuilder;

import java.util.*; 
import CourseObjectBuilder.*;
import ScheduleAnalyzer.*;

public class ScheduleBuilder {
        private HashMap<String, Course[]> sectionsPerCourse;
        private Schedule[] viableSchedules;
        private ScheduleSpreadAnalyzer timeSpreadRanker;
        private ScheduleTimeAnalyzer timeDensityRanker;
        
        public ScheduleBuilder(Course[][] courses) {
                
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
        public boolean checkClassTimesConflict(String[][] scheduleArray, Course newClass) {
                HashMap<Integer, int[]> classTimes = newClass.getSessionDayTimeMap();
                int day, startTime, endTime;
                for (Map.Entry<Integer, int[]> entry: classTimes.entrySet()) {
                        day = entry.getKey();
                        startTime = entry.getValue()[0];
                        endTime = entry.getValue()[1];
                        for (int i = startTime; i <= endTime; i++) {
                                if (!scheduleArray[day][i].equals("-")) {
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
                                        if (!scheduleArray[day][i].equals("-")) {
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
        public void AddClass(String[][] scheduleArray, Course newClass) {
                HashMap<Integer, int[]> classTimes = newClass.getSessionDayTimeMap();
                int day, startTime, endTime;
                for (Map.Entry<Integer, int[]> entry: classTimes.entrySet()) {
                        day = entry.getKey();
                        startTime = entry.getValue()[0];
                        endTime = entry.getValue()[1];
                        for (int i = startTime; i <= endTime; i++) {
                                scheduleArray[day][i] = newClass.getCourseTitle();
                        }
                }
                if (newClass.isLabCourse()) {
                        HashMap<Integer, int[]> labTimes = newClass.getSessionDayTimeMap();
                        for (Map.Entry<Integer, int[]> entry: labTimes.entrySet()) {
                                day = entry.getKey();
                                startTime = entry.getValue()[0];
                                endTime = entry.getValue()[1];
                                for (int i = startTime; i <= endTime; i++) {
                                        scheduleArray[day][i] = newClass.getCourseTitle();
                                }
                        }
                }
        }

        public Schedule[] findViableSchedules(Course[][] allCourseAndSections) {
                return new Schedule[] {};
        }

        public Schedule buildSchedule() {
                return null;
        }

        public Schedule[] returnViableSchedules() {
                return viableSchedules;
        }
           
    
}
