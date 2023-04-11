package ScheduleAnalyzer;
import ScheduleObjectBuilder.*;
import java.util.*;
//Class specifically for analyzing the amount of time between classes each day
public class ScheduleSpreadAnalyzer implements ScheduleAnalyzer{
    private Schedule[] schedules;
    HashMap<Integer, Schedule> schedulesHashMap =  new HashMap<Integer, Schedule>();
    int[] sortedSchedulesArray;

    public ScheduleSpreadAnalyzer(Schedule[] viableschedules) {
        schedules = viableschedules;
        for(int i = 0; i < schedules.length; i++){  //key:original index in schedules value: schedule 
            schedulesHashMap.put(i, schedules[i]);
        }
        rankScheduleOptions(); //sets sortedSchedulesArray
    }

    @Override
    public void rankScheduleOptions() {
        // Rank all schedules by least to greatest time between classes, summed over each day
        int[] arrayOfKeys = new int[schedules.length];
        double[] arrayOfRanks = new double[schedules.length];
        for (int i = 0; i < schedules.length; i++){
            arrayOfKeys[i] = i;
            arrayOfRanks[i] = ScheduleRanker(schedules[i]);
        }
        int n = schedules.length;
        if (n > 1) {
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
        sortedSchedulesArray = arrayOfKeys;
    }

    @Override
    public Schedule getHighestRankedScheduleOption() {
        // public schedule getHighest user .get()
        // Get schedule with least time between classes, i.e., schedule with most time spread
        return schedulesHashMap.get(sortedSchedulesArray[0]);

    }

    @Override
    public Schedule getLowestRankedScheduleOption() {
        // Get schedule with most time between classes, i.e., schedule with least time spread
        return schedulesHashMap.get(sortedSchedulesArray[sortedSchedulesArray.length - 1]);

    }

    //returns total number of hours before 12 for a certain schedule
    public double ScheduleRanker(Schedule schedule) { 
        HashMap<Integer, ArrayList<int[]>> times = schedule.getClassTimes();
        double rankingpoints = 0.0;
        for (int day = 0; day <= 4; day++){
            ArrayList<int[]> classes = times.get(day);
            int numberClasses = classes.size();
            if (numberClasses > 1) {
                for (int i = 1; i < numberClasses; i++){
                    int[] classPrevious = classes.get(i-1);
                    int[] classCurrent = classes.get(i);
                    rankingpoints = classCurrent[0] - classPrevious[1];
                }
            }
        }
        return Math.abs(rankingpoints);     
    }
}

