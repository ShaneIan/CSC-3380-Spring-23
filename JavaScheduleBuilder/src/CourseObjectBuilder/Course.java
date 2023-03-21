package CourseObjectBuilder;
import java.util.*;
import java.util.regex.*;

public class Course extends LearningSession{
    private Boolean isFull;
    private String courseNumber;
    private String courseTitle;
    private int creditHours;
    private String instructor;
    private Boolean hasLab;
    private Lab lab;

    //Course constructor for a class without a lab
    public Course(Boolean isFull, String courseNumber, String courseTitle, int creditHours, String classTime, String classDays, String instructor) {
        super(classTime, classDays);
        isFull = this.isFull;
        courseNumber = this.courseNumber;
        courseTitle = this.courseTitle;
        creditHours = this.creditHours;
        instructor = this.instructor;
        hasLab = false;
        lab = null;
    }

    //Course constructor for a class with a lab
    public Course(Boolean isFull, String courseNumber, String courseTitle, int creditHours, String classTime, String classDays, String instructor, Lab lab) {
        super(classTime, classDays);
        isFull = this.isFull;
        courseNumber = this.courseNumber;
        courseTitle = this.courseTitle;
        creditHours = this.creditHours;
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

    public Boolean isLabCourse() {
        return hasLab;
    }

    public Lab getLab() {
        return lab;
    }
}
