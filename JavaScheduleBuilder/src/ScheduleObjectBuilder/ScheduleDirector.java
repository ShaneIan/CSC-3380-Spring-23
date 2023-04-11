package ScheduleObjectBuilder;
import java.util.*; 
import CourseObjectBuilder.*;
import ScheduleAnalyzer.*;

public class ScheduleDirector {
        private Course[][] allCourseAndSections;
        private ArrayList<Schedule> viableSchedules;
        private ScheduleSpreadAnalyzer spreadRanker;
        private ScheduleTimeAnalyzer timeRanker;

        public ScheduleDirector(ScheduleBuilder schedbldr, Course[][] courses) {
                allCourseAndSections = courses;
                viableSchedules = new ArrayList<>();
                findViableSchedules(schedbldr);
                Schedule[] viableSchedArr = new Schedule[viableSchedules.size()];
                for (int i = 0; i < viableSchedArr.length; i++) {
                        viableSchedArr[i] = viableSchedules.get(i);
                }
                spreadRanker = new ScheduleSpreadAnalyzer(viableSchedArr);
                timeRanker = new ScheduleTimeAnalyzer(viableSchedArr);
        }

        public void findViableSchedules(ScheduleBuilder schedbldr) {
                makeCourseCombinations(schedbldr, new Schedule(), 0, 0);
        }

        public void makeCourseCombinations(ScheduleBuilder schedbldr, Schedule newSchedule, int course, int section) {
                if (schedbldr.checkCourseTimesConflict(newSchedule, allCourseAndSections[course][section])) {
                        //Check if schedule already contains the course being checked
                        //if it doesn't, add the class to the schedule
                        if (!schedbldr.containsCourse(newSchedule, allCourseAndSections[course][section].getCourseNumber())) {
                                schedbldr.addCourse(newSchedule, allCourseAndSections[course][section]);
                        }

                        if (newSchedule.getCourses().size() == allCourseAndSections.length) {
                                viableSchedules.add(new Schedule(newSchedule));
                        }

                        //If there are still courses to add, call build schedule again with the course index increased by 1
                        if (course < allCourseAndSections.length - 1) {
                                makeCourseCombinations(schedbldr, newSchedule, course + 1, 0);
                        }

                        //If there are still sections to check for the given course, 
                        //call build schedule again with the section index increased by 1
                        if (section < allCourseAndSections[course].length - 1) {
                                schedbldr.removeCourse(newSchedule, allCourseAndSections[course][section]);
                                makeCourseCombinations(schedbldr, newSchedule, course, section + 1);
                        }
                }
        }

        //returns a list of viable schedules
        public ArrayList<Schedule> returnViableSchedules() {
                return viableSchedules;
        }

        //returns the spreadRanker, ranks based on gap between class times
        public ScheduleSpreadAnalyzer getSpreadAnalyzer() {
                return spreadRanker;
        }

        //returns the timeRanker, ranks based on the time between classes
        public ScheduleTimeAnalyzer getMorningAnalyzer() {
                return timeRanker;
        }
}