package ClientManager;

import java.util.*;

import ScheduleObjectBuilder.Schedule;
import CourseObjectBuilder.*;

public class ScheduleTableBuilder {
    private StringBuilder htmlTable;
    private final String[] TIMESLOTS = {"7:00-7:30", "7:30-8:00", "8:00-8:30", "8:30-9:00", "9:00-9:30", "9:30-10:00", "10:00-10:30", "10:30-11:00", "11:00-11:30", "11:30-12:00", "12:00-12:30", "12:30-1:00", "1:00-1:30", "1:30-2:00", "2:00-2:30", "2:30-3:00", "3:00-3:30", "3:30-4:00", "4:00-4:30", "4:30-5:00", "5:00-5:30", "5:30-6:00", "6:00-6:30", "6:30-7:00", "7:00-7:30", "7:30-8:00", "8:00-8:30", "8:30-9:00", "9:00-9:30", "9:30-10:00", ""};
    private final String[] COLORS = {"blue", "red", "green", "purple", "yellow", "orange", "grey"};

    public ScheduleTableBuilder(ArrayList<Schedule> schedules) {
        htmlTable = new StringBuilder().append("<html>");
        for (Schedule schedule: schedules) { 
            buildTable(schedule);
        }
    }

    private void buildTable(Schedule schedule) {
        ArrayList<Course> courses = schedule.getCourses();
        String[][] scheduleMatrix = schedule.getScheduleMatrix();
        htmlTable.append("<head><style>");
        htmlTable.append("table { border-collapse: separate; border-spacing: 0px 0px; } th, td { min-width:50px, text-align: center; border: 2px solid black;}"); // Add padding to <th> tags
        htmlTable.append("</style></head>");
        for (Course course: courses) {
            htmlTable.append("<p>" + course.toString() + "</p>");
        }
        //Add day headings to Table
        htmlTable.append("<table><tr><th></th><th>M</th><th>T</th><th>W</th><th>TH</th><th>F</th></tr>");
        int colorInd = 0;
        Map<String, String> classColorMap = new HashMap<>();
        for (int timeInd = 0; timeInd < scheduleMatrix[0].length; timeInd++) {
            htmlTable.append("<tr>");
            htmlTable.append("<td>" + TIMESLOTS[timeInd] + "</td>");

            for (int dayInd = 0; dayInd < scheduleMatrix.length -1; dayInd++) {
                if (!scheduleMatrix[dayInd][timeInd].equals("-")) {
                    if (classColorMap.containsKey(scheduleMatrix[dayInd][timeInd])) {
                        htmlTable.append("<td style=\"background-color: " + classColorMap.get(scheduleMatrix[dayInd][timeInd]) + "\">" + scheduleMatrix[dayInd][timeInd] + "</td>");
                    }
                    else {
                        htmlTable.append("<td style=\"background-color: " + COLORS[colorInd] + "\">" + scheduleMatrix[dayInd][timeInd] + "</td>");
                        classColorMap.put(scheduleMatrix[dayInd][timeInd], COLORS[colorInd]);
                        colorInd++;
                    }
                }
                else {
                    htmlTable.append("<td></td>");
                }
            }
            htmlTable.append("</tr>");
        }
        htmlTable.append("</table><br>");
    }

    public String returnTableString() {
        htmlTable.append("</html>");
        return htmlTable.toString();
    }
}