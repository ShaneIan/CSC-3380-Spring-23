package ScheduleAnalyzer;
import ScheduleObjectBuilder.*;
//Class specifically for analyzing the amount of time between classes each day
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

        int n = arr.length;
    
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] < arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
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
