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
    }

    public Schedule() {
        courses = new ArrayList<>();
        totalHours = 0;
        scheduleMatrix = BuildScheduleMatrix();
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
    }

    private String[][] BuildScheduleMatrix() {
        String[][] scheduleArray = new String[6][31];
        for (String[] Day: scheduleArray)
        {
                Arrays.fill(Day, "-");
                        
        }
        return scheduleArray;
    }

    //Takes preexisting a schedule array and the course to be checked for conflicts
    //and iterates through the course's SessionDayTimeMap to see if the class times per day
    //coincide with any class times in the schedule array by checking all time indexes between (and including) 
    //the startTimeIndex and endTimeIndex for each day in the SessionDayTimeMap, where any string other than "-" 
    //indicates a filled timeslot
    public boolean checkClassTimesConflict(Course newClass) {
        System.out.println(newClass.toString());
        HashMap<Integer, int[]> classTimes = newClass.getSessionDayTimeMap();
        int day, startTime, endTime;
        for (Map.Entry<Integer, int[]> entry: classTimes.entrySet()) {
                day = entry.getKey();
                startTime = entry.getValue()[0];
                endTime = entry.getValue()[1];
                for (int i = startTime; i < endTime; i++) {
                        if (!scheduleMatrix[day][i].equals("-")) {
                                return false;
                        }
                }
        }
        if (newClass.isLabCourse()) {
                HashMap<Integer, int[]> labTimes = newClass.getSessionDayTimeMap();
                for (Map.Entry<Integer, int[]> entry: labTimes.entrySet()) {
                        day = entry.getKey();
                        startTime = entry.getValue()[0];
                        endTime = entry.getValue()[1];
                        for (int i = startTime; i < endTime; i++) {
                                if (!scheduleMatrix[day][i].equals("-")) {
                                        return false;
                                }
                        }
                }
        }
        return true;
    }

    //Takes a preexisting schedule array and the course to be added
    //and iterates through the course's SessionDayTimeMap to add the class times per day to the schedule array by using
    //the startTimeIndex and endTimeIndex for each day in the SessionDayTimeMap, and changing the schedule array 
    //timeslot string to the course title
    public void AddClass(Course newClass) {
        HashMap<Integer, int[]> classTimes = newClass.getSessionDayTimeMap();
        int day, startTime, endTime;
        for (Map.Entry<Integer, int[]> entry: classTimes.entrySet()) {
                day = entry.getKey();
                startTime = entry.getValue()[0];
                endTime = entry.getValue()[1];
                for (int i = startTime; i < endTime; i++) {
                        scheduleMatrix[day][i] = newClass.getCourseNumber();
                }
        }
        if (newClass.isLabCourse()) {
                HashMap<Integer, int[]> labTimes = newClass.getSessionDayTimeMap();
                for (Map.Entry<Integer, int[]> entry: labTimes.entrySet()) {
                        day = entry.getKey();
                        startTime = entry.getValue()[0];
                        endTime = entry.getValue()[1];
                        for (int i = startTime; i < endTime; i++) {
                            scheduleMatrix[day][i] = newClass.getCourseNumber();
                        }
                }
        }
        courses.add(newClass);
    }

    //Takes a preexisting schedule array and the course to be removed
    //and iterates through the course's SessionDayTimeMap to remove the class times per day from the schedule array by using
    //the startTimeIndex and endTimeIndex for each day in the SessionDayTimeMap, and changing the schedule array 
    //timeslot string to to "-"
    public void removeClass(Course remCourse) {
        //Check if course being removed is in schedule, return if not
        if (!containsCourse(remCourse.getCourseNumber())) {
            return;
        }
        HashMap<Integer, int[]> classTimes = remCourse.getSessionDayTimeMap();
        int day, startTime, endTime;
        for (Map.Entry<Integer, int[]> entry: classTimes.entrySet()) {
                day = entry.getKey();
                startTime = entry.getValue()[0];
                endTime = entry.getValue()[1];
                for (int i = startTime; i < endTime; i++) {
                    if (scheduleMatrix[day][i] == remCourse.getCourseNumber())
                        scheduleMatrix[day][i] = "-";
                }
        }
        if (remCourse.isLabCourse()) {
                HashMap<Integer, int[]> labTimes = remCourse.getSessionDayTimeMap();
                for (Map.Entry<Integer, int[]> entry: labTimes.entrySet()) {
                        day = entry.getKey();
                        startTime = entry.getValue()[0];
                        endTime = entry.getValue()[1];
                        for (int i = startTime; i < endTime; i++) {
                            if (scheduleMatrix[day][i] == remCourse.getCourseNumber())
                                scheduleMatrix[day][i] = "-";
                        }
                }
        }
        courses.remove(remCourse);
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

    public boolean containsCourse(String courseCode) {
        for (Course course: courses) {
                if (course.getCourseNumber().equals(courseCode)) {
                        return true;
                }
        }
        return false;
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

    public double getNumberHoursBeforeTwelve() {
        HashMap<Integer, ArrayList<int[]>> times = getClassTimes();
        double hoursBefore12 = 0.0;
        for (ArrayList<int[]> day: times.values()) {
            for (int[] classTime: day) {
                double startTime = classTime[0];
                double endTime = classTime[1];
                if (startTime > 10) {  //shouldnt this be <
                    while (startTime < endTime) {
                        hoursBefore12 += 0.5;
                        startTime++;
                    }
                }
            }
        }
        return hoursBefore12;
    }

    public double getNumberHoursOfGap(){  //needs to be implemented shoul work like getNumberHoursBeforeTwelve
        HashMap<Integer, ArrayList<int[]>> times = getClassTimes();
        double hoursOfGapTime = 0.0;
        for (int day = 0; day <= 4; day++){
            ArrayList<int[]> classes = times.get(day);
            int numberClasses = classes.size();
            for (int i = 1; i < numberClasses; i++){
                int[] classPrevious = classes.get(i-1);
                int[] classCurrent = classes.get(i);
                hoursOfGapTime = classPrevious[1] - classCurrent[0];
            }
        }
        return hoursOfGapTime;
    }
}
