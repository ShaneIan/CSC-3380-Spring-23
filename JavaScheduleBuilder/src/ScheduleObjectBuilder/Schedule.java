package ScheduleObjectBuilder;
import CourseObjectBuilder.*;
import java.util.*;

public class Schedule {
    private ArrayList<Course> courses;
    private int totalHours;
    private HashMap<Integer, ArrayList<int[]>> classTimes;
    private String[][] scheduleMatrix;


    public Schedule(ArrayList<Course> courses) {
        courses = this.courses;
        totalHours = calculateTotalHours();
        scheduleMatrix = BuildScheduleMatrix();
        classTimes = buildClassPerDayMap();
    }

    public Schedule() {
        courses = new ArrayList<>();
        totalHours = 0;
        scheduleMatrix = BuildScheduleMatrix();
        classTimes = buildClassPerDayMap();
    }

    //Copy contructor for recursive schedule builder method
    public Schedule(Schedule prevScheduleObj) {
        courses = new ArrayList<Course>();
        for (Course course: prevScheduleObj.getCourses()) {
            courses.add(new Course(course));
        }
        totalHours = prevScheduleObj.getTotalCreditHours();
        scheduleMatrix = new String[6][31];
        String[][] originalSchedMatrix = prevScheduleObj.getScheduleMatrix();
        for (int i = 0; i < originalSchedMatrix.length; i++) {
            scheduleMatrix[i] = Arrays.copyOf(originalSchedMatrix[i], originalSchedMatrix[i].length);
        }
        classTimes = buildClassPerDayMap();
    }

    //
    private String[][] BuildScheduleMatrix() {
        String[][] scheduleArray = new String[6][31];
        for (String[] Day: scheduleArray)
        {
                Arrays.fill(Day, "-");
                        
        }
        return scheduleArray;
    }


    public int calculateTotalHours() {
        int numScheduleHours = 0;
        for (Course course: courses) {
            numScheduleHours += course.getCreditHours();
        }
        return numScheduleHours;
    }

    //Compile all course time hashmaps into one dayTime hashmap for a schedule
    //Ex. A schedule with course dayTime maps {0: [13, 15], 2: [13, 15], 4: [13, 15]}, 
    //{0: [15, 17], 2: [15, 17], 4: [15, 17]}, and {1: [13, 15], 3: [13, 15]}
    //should return the schedule dayTime map {0: [[13, 15], [15, 17]], 1: [[13, 15]], 2: [[13, 15], [15, 17]], 3: [[13, 15]], 4: [[13, 15], [15, 17]]}
    public HashMap<Integer, ArrayList<int[]>> buildClassPerDayMap() {
        HashMap<Integer, ArrayList<int[]>> scheduleTimeAndDayMap = new HashMap<Integer, ArrayList<int[]>>();

        //Initialize array list at each day arrayList
        for (int i = 0; i < 6; i++) {
            scheduleTimeAndDayMap.put(i, new ArrayList<int[]>());
        }
        int dayInd;
        ArrayList<int[]> scheduleDayVal;
        for (Course course: courses) {
                for (Map.Entry<Integer, int[]> day: course.getSessionDayTimeMap().entrySet()) {
                    dayInd = day.getKey();
                    scheduleDayVal = scheduleTimeAndDayMap.get(dayInd);
                    scheduleDayVal.add(day.getValue());
                    scheduleTimeAndDayMap.put(dayInd, scheduleDayVal);
                }
        }
        return scheduleTimeAndDayMap;
    }

    public ArrayList<Course> getCourses() {
        return courses;
    }

    public HashMap<Integer, ArrayList<int[]>> getClassTimes() {
        return classTimes;
    }

    public int getTotalCreditHours() {
        return totalHours;
    }

    public String[][] getScheduleMatrix() {
        return scheduleMatrix;
    }
}
