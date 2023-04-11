package ScheduleAnalyzer;
import ScheduleObjectBuilder.*;
import java.util.*;
//Class specifically for analyzing the timing of schedules  
public class ScheduleTimeAnalyzer implements ScheduleAnalyzer{
    private Schedule[] schedules;
    HashMap<Integer, Schedule> schedulesHashMap = new HashMap<Integer, Schedule>();
    int[] sortedSchedulesArray;


    public ScheduleTimeAnalyzer(Schedule[] viableSchedules) {
        schedules = viableSchedules;
        for(int i = 0; i < schedules.length; i++){  //key:original index in schedules value: schedule 
            schedulesHashMap.put(i, schedules[i]);
        }
        rankScheduleOptions(); //sets sortedSchedulesArray
    }

    @Override
    public void rankScheduleOptions() {  //returns a sorted array of key values for schedulesHashMap based on morning rank
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
    public Schedule getHighestRankedScheduleOption() { //returns schedule with least amount of morning hours 
        return schedulesHashMap.get(sortedSchedulesArray[0]);
    }

    @Override
    public Schedule getLowestRankedScheduleOption() { //returns schedule with the most amount of morning hours
        return schedulesHashMap.get(sortedSchedulesArray[sortedSchedulesArray.length - 1]);
    }

    public double ScheduleRanker(Schedule schedule) { //returns total number of hours after 12 for a certain schedule
        HashMap<Integer, ArrayList<int[]>> times = schedule.getClassTimes();
        double rankingpoints = 0.0;
        for (ArrayList<int[]> day: times.values()) {
            for (int[] classTime: day) {
                double startTime = classTime[0];
                double endTime = classTime[1];
                if (startTime > 10) { 
                    while (startTime < endTime) {
                        rankingpoints += 0.5;
                        startTime++;
                    }
                }
            }
        }
        return rankingpoints;    
    }
}
