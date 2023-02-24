package ScheduleObjectBuilder;

import java.util.*; 
import CourseObjectBuilder.*;
import ScheduleAnalyzer.*;

public class ScheduleBuilder {
        private HashMap<String, Course[]> sectionsPerCourse;
        private Schedule[] viableSchedules;
        private ScheduleSpreadAnalyzer timeSpreadRanker;
        private ScheduleTimeAnalyzer timeDensityRanker;
        
        public void findViableSchedules() {

        }

        public void buildSchedule() {

        }

        public void doClassTimesConflict() {

        }

        public Schedule[] returnViableSchedules() {
                return viableSchedules;
        }
           
    
}
