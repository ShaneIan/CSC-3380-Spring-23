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
        //throw new UnsupportedOperationException("Unimplemented method 'rateSchedule'");
        int[] arr = new int[schedules.length]; 
        
        int n = arr.length;
    
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
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

    public double ScheduleRanker(Schedule schedule) {
        double rankingpoints = schedule.getNumberHoursBeforeTwelve(); 
        return rankingpoints;    
    }
}
