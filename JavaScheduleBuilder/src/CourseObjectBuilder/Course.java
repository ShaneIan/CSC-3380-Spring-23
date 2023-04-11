package CourseObjectBuilder;

public class Course extends LearningSession{
    private Boolean isFull;
    private String courseNumber;
    private String courseTitle;
    private String sectionNumber;
    private int creditHours;
    private String instructor;
    private Boolean hasLab;
    private Lab lab;

    //Course constructor for a class without a lab
    public Course(Boolean isFull, String courseNumber, String courseTitle, String sectionNumber, int creditHours, String classTimes, String classWeekDays, String instructor) {
        super(classTimes, classWeekDays);
        this.isFull = isFull;
        this.courseNumber = courseNumber;
        this.courseTitle = courseTitle;
        this.sectionNumber = sectionNumber;
        this.creditHours = creditHours;
        this.instructor = instructor;
        hasLab = false;
        lab = null;
    }

    //Course constructor for a class with a lab
    public Course(Boolean isFull, String courseNumber, String courseTitle, String sectionNumber, int creditHours, String classTime, String classDays, String instructor, Lab lab) {
        super(classTime, classDays);
        this.isFull = isFull;
        this.courseNumber = courseNumber;
        this.courseTitle = courseTitle;
        this.sectionNumber = sectionNumber;
        this.creditHours = creditHours;
        this.instructor = instructor;
        hasLab= true;
        lab = this.lab;
    }

    //Course constructor for making a deep copy of a preexisting course
    public Course(Course courseToCopy) {
        super(courseToCopy.getCourseTime(), courseToCopy.getCourseDays());
        isFull = courseToCopy.getIsFull();
        courseNumber = courseToCopy.getCourseNumber();
        courseTitle = courseToCopy.getCourseTitle();
        sectionNumber = courseToCopy.getSectionNumber();
        creditHours = courseToCopy.getCreditHours();
        instructor = courseToCopy.getInstructor();
        hasLab = courseToCopy.isLabCourse();
        lab = courseToCopy.getLab();
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

    public String getSectionNumber() {
        return sectionNumber;
    }

    public String getCourseTime() {
        return super.getSessionTime();
    }

    public String getCourseDays() {
        return super.getSessionDays();
    }

    public int getCreditHours() {
        return creditHours;
    }

    public String getInstructor() {
        return instructor;
    }

    public Boolean isLabCourse() {
        return hasLab;
    }

    public Lab getLab() {
        return lab;
    }


    @Override
    public String toString() {
        String courseString = courseNumber + " Section: " + sectionNumber + " Time: " + super.getSessionTime() + " " + super.getSessionDays() + " Instructor: " + instructor;
        return courseString;
    }
}
