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
                                Time = true;
                        }
                                
                }
                return ConflictArray;
        }

        public boolean doClassTimesConflict(Course NewClass) {
                //String ClassDayArray[] = NewClass.getClassDays();
                //int ClassTimeArray[] = NewClass.getClassTime();
                //for (String[] Day: ClassDayArray)
                //{
                //        while ()
                //}
                //
                // while (i <= newclass(time))
                // if newclass(time) = already taken part of array (already schedule class)
                // then conflict, return true
                // if no time conflict then call addclass
                // if ConflictArray[1][]
                // if newclass 
                return false;
        }

        public void findViableSchedules() {
        
        }

        public void buildSchedule() {

        }

        public void AddClass() {
                
        }

        public Schedule[] returnViableSchedules() {
                return viableSchedules;
        }
           
    
}
