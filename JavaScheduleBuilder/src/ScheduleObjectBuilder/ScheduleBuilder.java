package ScheduleObjectBuilder;

import java.util.*; 
import CourseObjectBuilder.*;
import ScheduleAnalyzer.*;

public class ScheduleBuilder {
        private HashMap<String, Course[]> sectionsPerCourse;
        private Course[][] allCourseAndSections;
        private ArrayList<Schedule> viableSchedules;
        private ScheduleSpreadAnalyzer timeSpreadRanker;
        private ScheduleTimeAnalyzer timeDensityRanker;

        public ScheduleBuilder(Course[][] courses) {
                allCourseAndSections = courses;
                viableSchedules = new ArrayList<>();
                findViableSchedules();
        }

        public void findViableSchedules() {
                System.out.println(allCourseAndSections.length + " Courses searched");
                buildSchedule(new Schedule(), 0, 0);
        }

        public void buildSchedule(Schedule newSchedule, int course, int section) {
                System.out.println("Course: " + course + " Section: " + section);
                if (newSchedule.checkClassTimesConflict(allCourseAndSections[course][section])) {
                        System.out.println(Arrays.deepToString(newSchedule.getScheduleMatrix()));
                        //Check if schedule already contains the course being checked
                        //if it doesn't, add the class to the schedule
                        if (!newSchedule.containsCourse(allCourseAndSections[course][section].getCourseNumber())) {
                                        newSchedule.AddClass(allCourseAndSections[course][section]);
                        }

                        //If the last course in the 2D courses array has been reached, 
                        //and the schedule contains all classes searched, add schedule to viable schedules
                        if (course == allCourseAndSections.length - 1) {
                                if (newSchedule.getCourses().size() == allCourseAndSections.length) {
                                        viableSchedules.add(newSchedule);
                                        System.out.println("Added schedule"); 
                                        System.out.println(Arrays.deepToString(newSchedule.getScheduleMatrix())); 
                                }
                        }

                        //If there are still courses to add, call build schedule again with the course index increased by 1
                        if (course < allCourseAndSections.length - 1) {
                                buildSchedule(new Schedule(newSchedule), course + 1, 0);
                        }

                        //If there are still sections to check for the given course, 
                        //call build schedule again with the section index increased by 1
                        if (section < allCourseAndSections[course].length - 1) {
                                newSchedule.removeClass(allCourseAndSections[course][section]);
                                System.out.println("Removed class");
                                buildSchedule(new Schedule(newSchedule), course, section + 1);
                        }
                
                }
                return;
        }

        public ArrayList<Schedule> returnViableSchedules() {
                return viableSchedules;
        }

        public ArrayList<String[][]> returnViableScheduleMatrices() {
                ArrayList<String[][]> viableScheduleMatrices = new ArrayList<>();
                for (Schedule schedule: viableSchedules) {
                        viableScheduleMatrices.add(schedule.getScheduleMatrix());
                }
                return viableScheduleMatrices;
        }
}
