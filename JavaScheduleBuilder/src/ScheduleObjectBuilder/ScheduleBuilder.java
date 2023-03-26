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
                findViableSchedules(courses);
        }

        public void findViableSchedules(Course[][] allCourseAndSections) {
                buildSchedule(new Schedule(), 0, 0);
        }

        public void buildSchedule(Schedule newSchedule, int course, int section) {
                if (newSchedule.checkClassTimesConflict(allCourseAndSections[course][section])) {
                        newSchedule.AddClass(allCourseAndSections[course][section]);
                        if (course == allCourseAndSections.length - 1) {
                                viableSchedules.add(newSchedule);
                        }
                
                        if (course < allCourseAndSections.length - 1) {
                                buildSchedule(newSchedule, course + 1, 0);
                        }
                
                        if (section < allCourseAndSections[course].length - 1) {
                                buildSchedule(newSchedule, course, section + 1);
                        }
                }
        }

        public ArrayList<Schedule> returnViableSchedules() {
                return viableSchedules;
        }
           
    
}
