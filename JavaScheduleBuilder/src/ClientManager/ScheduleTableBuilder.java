package ClientManager;

import java.util.*;

import ScheduleObjectBuilder.Schedule;

public class ScheduleTableBuilder {
    private String[][] scheduleMatrix;
    private ArrayList<String> courses;
    private StringBuilder htmlTable;
    private final String[] TIMESLOTS = {"7:00", "7:30", "8:00", "8:30", "9:00", "9:30", "10:00", "10:30", "11:00", "11:30", "12:00", "12:30", "1:00", "1:30", "2:00", "2:30", "3:00", "3:30", "4:00", "4:30", "5:00", "5:30", "6:00", "6:30", "7:00", "7:30", "8:00", "8:30", "9:00", "9:30", "10:00"};
    private final String[] COLORS = {"blue", "red", "green", "purple", "yellow", "orange", "grey"};

    public ScheduleTableBuilder(Schedule schedule) {
        scheduleMatrix = schedule.getScheduleMatrix();
        courses = schedule.getCoursesTitleAndSection();
        htmlTable = new StringBuilder().append("<html>");
        buildTable(scheduleMatrix);
    }

    private void buildTable(String[][] scheduleMatrix) {
        htmlTable.append("<head><style>");
        htmlTable.append("table { border-collapse: separate; border-spacing: 0px 0px; } th, td { text-align: center; border: 2px solid black;}"); // Add padding to <th> tags
        htmlTable.append("</style></head>");
        for (String course: courses) {
            htmlTable.append("<p>" + course + "</p>");
        }
        htmlTable.append("<table><tr><th></th><th>Monday</th><th>Tuesday</th><th>Wednesday</th><th>Thursday</th><th>Friday</th><th>Saturday</th></tr>");
        int colorInd = 0;
        Map<String, String> classColorMap = new HashMap<>();
        for (int timeInd = 0; timeInd < scheduleMatrix[0].length; timeInd++) {
            htmlTable.append("<tr>");
            htmlTable.append("<td>" + TIMESLOTS[timeInd] + "</td>");
            for (int dayInd = 0; dayInd < scheduleMatrix.length; dayInd++) {
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
    }

    public String returnTableString() {
        htmlTable.append("</table><br></html>");
        return htmlTable.toString();
    }
}