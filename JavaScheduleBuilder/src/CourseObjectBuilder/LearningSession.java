package CourseObjectBuilder;
import java.util.*;

public abstract class LearningSession {
    private String sessionTime;
    private int timeStartIndex;
    private int timeEndIndex;
    public String sessionDays;
    private int[] sessionDayIndices;
    private HashMap<Integer, int[]> sessionDayTimeMap;

    public LearningSession(String sessionTimes, String sessionWeekDays) {
        sessionTime = sessionTimes;
        int[] timeIndices = convertTimeSlotStringToIndices(sessionTimes);
        timeStartIndex = timeIndices[0];
        timeEndIndex = timeIndices[1];
        sessionDays = sessionWeekDays;
        sessionDayIndices = convertDayStringToIndices(this.sessionDays);
        sessionDayTimeMap = makeDayTimeMap(sessionDayIndices, timeStartIndex, timeEndIndex);
    }

    //Take time string and convert it into a start time and end time index between 0 and 30 to be placed into
    //the schedule matrix with the starting and ending time indexes
    //Ex. String "0100-0220" would be converted to the index array [13, 15]
    private int[] convertTimeSlotStringToIndices(String timeString) {
        String[] timeArr = timeString.split("-");
        int startTime = Integer.parseInt(timeArr[0].trim());
        int endTime = Integer.parseInt(timeArr[1].replace("N", ""));
        //Logic to convert start time at a half hour from x:30 to x50
        //Ex. Class starting at 4:30 would be from 430 to 450
        if (startTime % 100 != 0) {
            startTime += 20;
        }
        //Logic to convert endTime to nearest half hour
        //Divide by 50 to get times ending in x:50 to next hour
        //Since integers are used, 50 must be added to get time int to nearest hundred
        //Ex. Class ending at 4:50 would be converted to 5:00 by converting 450 to 500
        if (endTime % 50 == 0) {
                endTime += 50;
        }
        //Add 10 to get x:20 to x:30
        //Since integers are used, numbers ending in fifty will represent x:30
        //Ex. Class ending at 4:20 would be converted to 4:30 by converting 420 to 450
        else {
                endTime += 30;
        }

        //Convert timeInt to index from 0 to 30
        startTime = convertTimeIntToIndex(startTime, timeString.contains("N"));
        endTime = convertTimeIntToIndex(endTime, timeString.contains("N"));
        return new int[] {startTime, endTime};
    }

    //Converts Time int to index between 0 and 30
    //Uses a boolean to differentiate night classes from other classes for proper indexing
    //Ex. convertTimeIntToIndex(700, true) would return 24
    private int convertTimeIntToIndex(int timeInt, Boolean isNight) {
        int timeInd;
        if (timeInt < 700 || isNight) {
            timeInd = timeInt / 50 + 10;
            return timeInd;
        }
        else {
            timeInd = timeInt / 50 - 14;
            return timeInd;
        }
    }

    //Converts a day string into each individual day string
    //and converts each day string into an index to be placed into the schedule matrix
    //Ex. The string "M W F" would be converted into the string "M", "W", "F",
    //which would then be converted into the array [0, 2, 4]
    private int[] convertDayStringToIndices(String dayString) {
        String[] days = dayString.trim().split(" ");
        int[] dayIndices = new int[days.length];
        int dayInd = 0;
        for (String day: days) {
            if (day.equals("M")) {
                dayIndices[dayInd] = 0;
            }
            else if (day.equals("T")) {
                dayIndices[dayInd] = 1;
            }
            else if (day.equals("W")) {
                dayIndices[dayInd] = 2;
            }
            else if (day.equals("TH")) {
                dayIndices[dayInd] = 3;
            }
            else if (day.equals("F")) {
                dayIndices[dayInd] = 4;
            }
            else if (day.equals("S")) {
                dayIndices[dayInd] = 5;
            }
            else {
                System.out.println("Recieved non day of the week value");
            }
            dayInd++;
        }
        return dayIndices;
    }

    //Produces a HashMap of the key,value pair form 'dayIndex:[startTimeIndex, endTimeIndex]'
    //which can be iterated through the check if learningSession subclass times conflict and 
    //to add a learningSession subclass time to a schedule matrix
    //Ex. A learning session with class "M W F" at "0100-0220" with the classIndex array [0, 2, 4],
    //startTimeIndex = 13 and endTimeIndex = 15 would be returned as the map
    //TimeAndDayMap = {0: [13, 15], 2: [13, 15], 4: [13, 15]}
    public HashMap<Integer, int[]> makeDayTimeMap(int[] dayIndices, int startIndex, int endIndex) {
        HashMap<Integer, int[]> TimeAndDayMap = new HashMap<Integer, int[]>();
        int dayArrIndex = 0;
        while (dayArrIndex < dayIndices.length) 
        {
            TimeAndDayMap.put(dayIndices[dayArrIndex], new int[] {startIndex, endIndex});
            dayArrIndex++;
        }
        return TimeAndDayMap;
    }

    public String getSessionTime() {
        return sessionTime;
    }

    public int getTimeStartIndex() {
        return timeStartIndex;
    }

    public int getTimeEndIndex() {
        return timeEndIndex;
    }

    public String getSessionDays() {
        return sessionDays;
    }

    public int[] getSessionDaysIndices() {
        return sessionDayIndices;
    }

    public HashMap<Integer, int[]> getSessionDayTimeMap() {
        return sessionDayTimeMap;
    }

}
