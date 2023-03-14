package CourseObjectBuilder;
import java.util.*;

public class Course {
    private Boolean isFull;
    private String courseNumber;
    private String courseTitle;
    private int creditHours;
    private String classTime;
    private String[] classDays;
    private String instructor;
    private Boolean hasLab;
    private Lab lab;

    public Course(Boolean isFull, String courseNumber, String courseTitle, int creditHours, String classTime, String[] classDays, String instructor) {
        isFull = this.isFull;
        courseNumber = this.courseNumber;
        courseTitle = this.courseTitle;
        creditHours = this.creditHours;
        classTime = this.classTime;
        classDays = this.classDays;
        instructor = this.instructor;
        hasLab = false;
        lab = null;
    }

    public Course(Boolean isFull, String courseNumber, String courseTitle, int creditHours, String classTime, String[] classDays, String instructor, Lab lab) {
        isFull = this.isFull;
        courseNumber = this.courseNumber;
        courseTitle = this.courseTitle;
        creditHours = this.creditHours;
        classTime = this.classTime;
        classDays = this.classDays;
        instructor = this.instructor;
        hasLab= true;
        lab = this.lab;
    }

    public Boolean getIsFull() {
        return isFull;
    }

    public String getCourseNumber() {
        return courseNumber;
    }

    public String getCourseTitle() {
        return courseTitle;
    }

    public int getCreditHours() {
        return creditHours;
    }

    public String getClassTime() {
        return classTime;
    }

    public String[] getClassDays() {
        return classDays;
    }

    public Boolean isLabCourse() {
        return hasLab;
    }

    public Lab getLab() {
        return lab;
    }

    public HashMap<String, String> compileClassTimes() {
        HashMap<String, String> classTimeAndDayMap = new HashMap<String, String>();
        String[] daysArr;
        String timeStr;
        daysArr = this.classDays;
        timeStr = this.classTime;
        for (String day: daysArr) {
            classTimeAndDayMap.put(day, timeStr);
        }
        if (this.isLabCourse()) {
            Lab lab = this.getLab();
            String[] labDays = lab.getLabDays();
            String labTime = lab.getLabTimes();
            for (String day: labDays) {
                classTimeAndDayMap.put(day, labTime);
            }
        }
        return classTimeAndDayMap;
    }

}
