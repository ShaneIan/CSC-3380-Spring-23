package ScheduleObjectBuilder;

import java.util.*; 
import CourseObjectBuilder.*;
import ScheduleAnalyzer.*;

public class ScheduleBuilder {
        private HashMap<String, Course[]> sectionsPerCourse;
        private Schedule[] viableSchedules;
        private ScheduleSpreadAnalyzer timeSpreadRanker;
        private ScheduleTimeAnalyzer timeDensityRanker;
        
        public boolean[][] BuildConflictMatrix() {
                boolean ConflictArray[][] = new boolean [5][29];
                for (boolean[] Day: ConflictArray)
                {
                        for (boolean Time: Day){
                                Time=true;
                        }
                                
                }
                return ConflictArray;
        }
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
