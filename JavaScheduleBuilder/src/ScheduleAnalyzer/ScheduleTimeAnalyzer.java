package ScheduleAnalyzer;
import ScheduleObjectBuilder.*;
//Class specifically for analyzing the timing of schedules to  
public class ScheduleTimeAnalyzer implements ScheduleAnalyzer{
    private Schedule[] schedules;
    HashMap<int, Schedule> schedulesHashMap;
    int[] sortedSchedulesArray;


    public ScheduleTimeAnalyzer(Schedule[] schedules) {
        schedules = this.schedules;
        sortedSchedulesArray = rankScheduleOptions;
        for(int i = 0; i < schedules.length; i++){  //key:original index in schedules value: schedule 
            schedulesHashMap.put(i, schedules[i]);
        }
    }

    @Override
    public void rateSchedule() {
        // Rate Schedule by what percentage of classes occur before 12:00 PM 
        throw new UnsupportedOperationException("Unimplemented method 'rateSchedule'");
    }

    @Override

    public int[] rankScheduleOptions() {  //returns a sorted array of key values for schedulesHashMap based on morning rank
        
        //throw new UnsupportedOperationException("Unimplemented method 'rateSchedule'");
        int[] arrayOfKeys = new int[schedules.length];
        for (int i = 0; i < schedules.length; i++){
            arrayOfKeys[i] = i
        }
        int[] arrayOfRanks = new int[schedules.length];
        for (int i = 0; i < schedules.length; i++){
            arrayOfRanks[i] = ScheduleRanker(schedules[i]) //this could be schedules[i].getNumberHoursBeforeTwelve
        }
        int n = schedules.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (arrayOfRanks[j] < arrayOfRanks[j + 1]) {
                    int temp = arrayOfRanks[j];
                    arrayOfRanks[j] = arrayOfRanks[j + 1];
                    arrayOfRanks[j + 1] = temp;
                    int temp = arrayOfKeys[j];
                    arrayOfKeys[j] = arrayOfKeys[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
        return arrayOfKeys;
    }

    @Override
    public void getHighestRankedScheduleOption() { //returns schedule with most amount of morning hours 
        //throw new UnsupportedOperationException("Unimplemented method 'rateSchedule'");
        return schedulesHashMap.get(sortedSchedulesArray[0]);
    }

    @Override
    public void getLowestRankedScheduleOption() { //returns schedule with the least amount of hours before 12
        
        //throw new UnsupportedOperationException("Unimplemented method 'rateSchedule'");
        return schedulesHashMap.get(sortedSchedulesArray[sortedSchedulesArray.length - 1]);
    }

    public double ScheduleRanker(Schedule schedule) {  //returns total number of hours before 12 for a certain schedule
        double rankingpoints = schedule.getNumberHoursBeforeTwelve(); 
        return rankingpoints;    
    }
}
