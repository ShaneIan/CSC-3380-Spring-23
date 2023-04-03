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
                        newSchedule.AddClass(allCourseAndSections[course][section]);
                        System.out.println(Arrays.deepToString(newSchedule.getScheduleMatrix()));
                        if (course == allCourseAndSections.length - 1) {
                                if (newSchedule.getCourses().size() == allCourseAndSections.length) {
                                        viableSchedules.add(newSchedule);
                                        System.out.println("Added schedule");  
                                }
                        }
                
                        if (course < allCourseAndSections.length - 1) {
                                buildSchedule(new Schedule(newSchedule), course + 1, 0);
                        }
                
                        if (section < allCourseAndSections[course].length - 1 && !newSchedule.containsCourse(allCourseAndSections[course][section].getCourseNumber())) {
                                buildSchedule(new Schedule(newSchedule), course, section + 1);
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
