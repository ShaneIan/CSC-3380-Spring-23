package CourseObjectBuilder;
import java.util.*;
import java.util.regex.*;

public abstract class LearningSession {
    private String sessionTime;
    private int timeStartIndex;
    private int timeEndIndex;
    private String sessionDays;
    private int[] sessionDayIndices;
    private HashMap<Integer, int[]> sessionDayTimeMap;

    public LearningSession(String sessionTime, String sessionDays) {
        sessionTime = this.sessionTime;
        int[] timeIndices = convertTimeSlotStringToIndices(this.sessionTime);
        timeStartIndex = timeIndices[0];
        timeEndIndex = timeIndices[1];
        sessionDays = this.sessionDays;
        sessionDayIndices = convertDayStringToIndices(this.sessionDays);
        sessionDayTimeMap = makeDayTimeMap(sessionDayIndices, timeStartIndex, timeEndIndex);
    }

    private int[] convertTimeSlotStringToIndices(String timeString) {
        Pattern p = Pattern.compile("(\\d+)-(\\d+)");
        Matcher match = p.matcher(timeString);
        int startTime = Integer.parseInt(match.group(1));
        int endTime = Integer.parseInt(match.group(2));

        //Logic to convert endTime to nearest half hour
        //Divide by 50 to get times ending in x:50 to next hour
        //Since integers are used, 50 must be added to get time int to nearest hundred
        //Ex. Class ending at 4:50 would be converted to 5:00 by converting 450 to 500
        if (endTime % 50 == 0) {
                endTime += 50;
        }
        //Add 10 to get x:20 to x:30
        //Since integers are used, numbers ending in fifty will represent x:30
        //Ex. Class ending at 4:20 would be converted to 4:30 by converting 430 to 450
        else {
                endTime += 10;
        }

        //Convert timeInt to index from 0 to 30
        startTime = convertTimeIntToIndex(startTime, timeString.contains("N"));
        endTime = convertTimeIntToIndex(endTime, timeString.contains("N"));

        return new int[] {startTime, endTime};
    }

    //Converts Time int to index between 0 and 30
    private int convertTimeIntToIndex(int timeInt, Boolean isNight) {
        if (timeInt < 700 || isNight) {
            int timeInd = timeInt / 50 + 10;
            return timeInd;
        }
        else {
            int timeInd = timeInt / 50 - 14;
            return timeInd;
        }
    }

    private int[] convertDayStringToIndices(String dayString) {
        String[] days = dayString.split("\\s");
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
            else if (day.equals("Th")) {
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

    public HashMap<Integer, int[]> makeDayTimeMap(int[] dayIndices, int startIndex, int endIndex) {
        HashMap<Integer, int[]> TimeAndDayMap = new HashMap<Integer, int[]>();
        int dayArrIndex = 0;
        while (dayArrIndex < dayIndices.length) 
        {
            TimeAndDayMap.put(dayIndices[dayArrIndex], new int[] {startIndex, endIndex});
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
