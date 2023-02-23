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
    
}
