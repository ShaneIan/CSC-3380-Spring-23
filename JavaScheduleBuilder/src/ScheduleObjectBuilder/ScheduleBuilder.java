package ScheduleObjectBuilder;

import CourseObjectBuilder.*;

public interface ScheduleBuilder {
    public void addCourse(Schedule schedule, Course course);

    public boolean checkCourseTimesConflict(Schedule schedule, Course course);

    public void removeCourse(Schedule schedule, Course course);

    public boolean containsCourse(Schedule schedule, String courseCode);
}
