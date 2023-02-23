package ScheduleAnalyzer;
import ScheduleObjectBuilder.*;

public class ScheduleSpreadAnalyzer implements ScheduleAnalyzer{
    private Schedule[] schedules;


    public ScheduleSpreadAnalyzer(Schedule[] schedules) {
        schedules = this.schedules;
    }
    @Override
    public void rateSchedule() {
        // Rate Schedule by amount of time between classes, summed over each day
        throw new UnsupportedOperationException("Unimplemented method 'rateSchedule'");
    }

    @Override
    public void rankScheduleOptions() {
        // Rank all schedules by least to greatest time between classes, summed over each day
        throw new UnsupportedOperationException("Unimplemented method 'rankScheduleOptions'");
    }

    @Override
    public void getHighestRankedScheduleOption() {
        // Get schedule with least time between classes, i.e., schedule with least time spread
        throw new UnsupportedOperationException("Unimplemented method 'getBestScheduleOption'");
    }

    @Override
    public void getLowestRankedScheduleOption() {
        // Get schedule with most time between classes, i.e., schedule with most time spread
        throw new UnsupportedOperationException("Unimplemented method 'getLowestRankedScheduleOption'");
    }

    
    
}
