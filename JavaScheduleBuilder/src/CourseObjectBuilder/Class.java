package CourseObjectBuilder;

public class Class {
    private Boolean isFull;
    private String courseNumber;
    private String courseTitle;
    private int creditHours;
    private String classTime;
    private String classDays;
    private String instructor;
    private Lab lab;

    public Class(Boolean isFull, String courseNumber, String courseTitle, int creditHours, String classTime, String classDays, String instructor) {
        isFull = this.isFull;
        courseNumber = this.courseNumber;
        courseTitle = this.courseTitle;
        creditHours = this.creditHours;
        classTime = this.classTime;
        classDays = this.classDays;
        instructor = this.instructor;
        lab = null;
    }

    public Class(Boolean isFull, String courseNumber, String courseTitle, int creditHours, String classTime, String classDays, String instructor, Lab lab) {
        isFull = this.isFull;
        courseNumber = this.courseNumber;
        courseTitle = this.courseTitle;
        creditHours = this.creditHours;
        classTime = this.classTime;
        classDays = this.classDays;
        instructor = this.instructor;
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

    public String getClassDays() {
        return classDays;
    }
}
