package ScheduleAnalyzer;
import ScheduleObjectBuilder.*;
//Class specifically for analyzing the timing of schedules to  
public class ScheduleTimeAnalyzer implements ScheduleAnalyzer{
    private Schedule[] schedules;


    public ScheduleTimeAnalyzer(Schedule[] schedules) {
        schedules = this.schedules;
    }

    @Override
    public void rateSchedule() {
        // Rate Schedule by what percentage of classes occur before 12:00 PM 
        throw new UnsupportedOperationException("Unimplemented method 'rateSchedule'");
    }

    @Override
    public void rankScheduleOptions() {
        // Rank all schedules by greatest to lowest morning class percentage score
        //for(all schedules)
        //ScheduleRanker(Schedule)
        //sort schedules on highest to lowest percentmorning
        throw new UnsupportedOperationException("Unimplemented method 'rateSchedule'");
    }

    @Override
    public void getHighestRankedScheduleOption() {
        // Get schedule with highest percentage of morning classes 
        throw new UnsupportedOperationException("Unimplemented method 'rateSchedule'");
    }

    @Override
    public void getLowestRankedScheduleOption() {
        // Get schedule with lowest percentage of morning classes
        throw new UnsupportedOperationException("Unimplemented method 'rateSchedule'");
    }

    public int ScheduleRanker(Schedule schedule) {
/*      int morninghours;
        float percentmorning
        for (i = # of classes, i < # number of classes + 1, i++) {
            if class start && stop < 10
                ranking point + 1
                add class hours to morninghours
        }

        morninghours/totalhours = percentmorning
        percentmorning * 100
        return percentmorning
        return ranking points

    }
*/ 
}
