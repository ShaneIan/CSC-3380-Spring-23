package CourseDataManager;
import ScheduleObjectBuilder.*;
import CourseObjectBuilder.*;

import java.util.*;

public class DataManager {
    private Course[][] coursesQueried;
    private DatabaseOperations dbOpr;
    private ScheduleBuilder scheduleBldr;
    private ArrayList<Schedule> viableSchedules;
    
    public DataManager(ArrayList<String> searchedCourses) {
        dbOpr = new DatabaseOperations();
        coursesQueried = queryCoursesFromSearch(searchedCourses);
        scheduleBldr = new ScheduleBuilder(coursesQueried);
        viableSchedules = scheduleBldr.returnViableSchedules();
        scheduleBldr.returnViableScheduleMatrices();
    }

    //Take ArrayList<String> from html form, convert each string from
    //format "ABBR #### to ["ABBR", "####"] to pass to dbOpr to fetch
    //all courses with matching course codes
    public Course[][] queryCoursesFromSearch(ArrayList<String> search) {
        if (search == null) {
            System.out.println("Search was null");
            return null;
        }
        ArrayList<Course[]> coursesReturned = new ArrayList<>();
        for (String searchItem: search) {
            String[] courseCode = searchItem.split(" ");
            if (courseCode.length == 2) {
                ArrayList<Course> sectionsReturned = new ArrayList<>();
                String[][] queryData = dbOpr.fetchDataByCourseCode(courseCode[0], courseCode[1]);
                for (String[] courseListing: queryData) {
                    sectionsReturned.add(convertQueryArrayToCourse(courseListing));
                }
                coursesReturned.add(sectionsReturned.toArray(new Course[0]));
            }
        }
        Course[][] finalCourseSectionsList = new Course[coursesReturned.size()][];
        for (int i = 0; i < coursesReturned.size(); i++) {
            Course[] courseSections = coursesReturned.get(i);
            finalCourseSectionsList[i] = courseSections;
        }
        return finalCourseSectionsList;
    }

    //Converts the string array returned by SQL query into a Course object using the
    //entries in the string array as course parameters
    public Course convertQueryArrayToCourse(String[] queryArray) {
        if (queryArray[13].contains("WEB B")) {
            return null;
        }
        boolean isFull = false;
        if (queryArray[1]=="(F)") {
            isFull = true;
        }
        String courseCode = queryArray[3] + " " + queryArray[4];
        int creditHours = (int) Float.parseFloat(queryArray[8]);
        Course convertedCourse = new Course(isFull, courseCode, queryArray[7], queryArray[6], creditHours, queryArray[9], queryArray[10], queryArray[14]);
        return convertedCourse;
    }

    public Course[][] getCoursesQueried() {
        Course[][] coursesQueriedCopy = coursesQueried.clone();
        return coursesQueriedCopy;
    }

    public ArrayList<Schedule> getViableSchedules() {
        return viableSchedules;
    }

}
