package ScheduleObjectBuilder;

import java.util.*; 
import CourseObjectBuilder.*;
//import ScheduleAnalyzer.*;

public class ScheduleBuilder {
        private Course[][] allCourseAndSections;
        private ArrayList<Schedule> viableSchedules;
        //private ScheduleSpreadAnalyzer timeSpreadRanker;
        //private ScheduleTimeAnalyzer timeDensityRanker;

        public ScheduleBuilder(Course[][] courses) {
                allCourseAndSections = courses;
                viableSchedules = new ArrayList<>();
                findViableSchedules();
        }

        public void findViableSchedules() {
                buildSchedule(new Schedule(), 0, 0);
        }

        public void buildSchedule(Schedule newSchedule, int course, int section) {
                if (newSchedule.checkClassTimesConflict(allCourseAndSections[course][section])) {
                        //System.out.println(Arrays.deepToString(newSchedule.getScheduleMatrix()));
                        //Check if schedule already contains the course being checked
                        //if it doesn't, add the class to the schedule
                        if (!newSchedule.containsCourse(allCourseAndSections[course][section].getCourseNumber())) {
                                newSchedule.AddClass(allCourseAndSections[course][section]);
                        }

                        if (newSchedule.getCourses().size() == allCourseAndSections.length) {
                                viableSchedules.add(new Schedule(newSchedule));
                        }

                        //If there are still courses to add, call build schedule again with the course index increased by 1
                        if (course < allCourseAndSections.length - 1) {
                                buildSchedule(newSchedule, course + 1, 0);
                        }

                        //If there are still sections to check for the given course, 
                        //call build schedule again with the section index increased by 1
                        if (section < allCourseAndSections[course].length - 1) {
                                newSchedule.removeClass(allCourseAndSections[course][section]);
                                buildSchedule(newSchedule, course, section + 1);
                        }
                }
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
