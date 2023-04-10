package ScheduleAnalyzer;
import ScheduleObjectBuilder.*;
import java.util.*;
//Class specifically for analyzing the amount of time between classes each day
public class ScheduleSpreadAnalyzer implements ScheduleAnalyzer{
    private Schedule[] schedules;
    HashMap<Integer, Schedule> schedulesHashMap =  new HashMap<Integer, Schedule>();
    int[] sortedSchedulesArray;

    public ScheduleSpreadAnalyzer(Schedule[] schedules) {
        schedules = this.schedules;
        for(int i = 0; i < schedules.length; i++){  //key:original index in schedules value: schedule 
            schedulesHashMap.put(i, schedules[i]);
        }
        rankScheduleOptions(); //sets sortedSchedulesArray
    }
    @Override
    public void rateSchedule() {
        // Rate Schedule by amount of time between classes, summed over each day
        throw new UnsupportedOperationException("Unimplemented method 'rateSchedule'");
    }

    @Override
    public void rankScheduleOptions() {
        // Rank all schedules by least to greatest time between classes, summed over each day
        //throw new UnsupportedOperationException("Unimplemented method 'rankScheduleOptions'");
        int[] arrayOfKeys = new int[schedules.length];
        double[] arrayOfRanks = new double[schedules.length];
        for (int i = 0; i < schedules.length; i++){
            arrayOfKeys[i] = i;
            arrayOfRanks[i] = ScheduleRanker(schedules[i]); //this could be schedules[i].getNumberHoursBeforeTwelve

        }
        int n = schedules.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (arrayOfRanks[j] < arrayOfRanks[j + 1]) {
                    double tempR = arrayOfRanks[j];
                    arrayOfRanks[j] = arrayOfRanks[j + 1];
                    arrayOfRanks[j + 1] = tempR;
                    int tempK = arrayOfKeys[j];
                    arrayOfKeys[j] = arrayOfKeys[j + 1];
                    arrayOfKeys[j + 1] = tempK;
                }
            }
        }
    }

    @Override
    public void getHighestRankedScheduleOption() {
        // public schedule getHighest user .get()
        // Get schedule with least time between classes, i.e., schedule with least time spread
        throw new UnsupportedOperationException("Unimplemented method 'getBestScheduleOption'");
        //return schedulesHashMap.get(sortedSchedulesArray[0]);

    }

    @Override
    public void getLowestRankedScheduleOption() {
        // Get schedule with most time between classes, i.e., schedule with most time spread
        throw new UnsupportedOperationException("Unimplemented method 'getLowestRankedScheduleOption'");
        //return schedulesHashMap.get(sortedSchedulesArray[sortedSchedulesArray.length - 1]);

    }

    public double ScheduleRanker(Schedule schedule) {  //returns total number of hours before 12 for a certain schedule
        double rankingpoints = schedule.getNumberHoursOfGap(); 
        return rankingpoints;     
    }

}

