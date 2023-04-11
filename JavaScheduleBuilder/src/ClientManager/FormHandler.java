package ClientManager;
import CourseDataManager.*;
import ScheduleObjectBuilder.Schedule;

import java.io.*;
import java.util.*;
import java.net.*;
import com.sun.net.httpserver.*;

public class FormHandler implements HttpHandler{
    public void handle(HttpExchange exchange) throws IOException {
        if (exchange.getRequestMethod().equalsIgnoreCase("POST")) {
            // read the request body
            InputStream input = exchange.getRequestBody();
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            StringBuilder requestBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                requestBuilder.append(line);
            }
            String request = requestBuilder.toString();
            
            // parse the request body as a URL-encoded form
            Map<String, String> formData = parseFormData(request);
            
            // handle the form data
            // ...
            String response = "";
            ArrayList<String> coursesSearched = new ArrayList<>();
            for (Map.Entry<String, String> entry : formData.entrySet()) {
                //response += entry.getValue() + "\n";
                coursesSearched.add(entry.getValue());
            }
            DataManager DataMngr= new DataManager(coursesSearched);
            ArrayList<Schedule> viableSchedules = DataMngr.getViableSchedules();

            //Setting style and basic elements of HTML response string
            StringBuilder responseHTML = new StringBuilder().append("<html>");
            responseHTML.append("<head><style>");
            responseHTML.append("body { background-color: rgb(48, 20, 86); } "+
                        "header {}" +
                        "table { background-color: white; border-collapse: separate; border-spacing: 0px 0px; } " +
                        "th, td { text-align: center; border: 1px solid black; width:10%;} " +
                        "p { color: white;}"); // Add padding to <th> tags
            responseHTML.append("</style></head><body><header><div><a href=\"https://www.lsu.edu\">" +
                        "<img src=\"https://www.lsu.edu/_resources_fierce/svgs/lsu-logo.svg\" alt=\"Louisiana State University\" style=\"object-position: left;width: 250px;\"></a>"+
                        "<a href=\"https://sso.paws.lsu.edu/login?service=https%3A%2F%2Fmylsu.apps.lsu.edu%2Fc%2Fportal%2Flogin\">" +
                        "<img src=\"https://logolook.net/wp-content/uploads/2022/02/LSU-Tigers-Logo-1955-768x432.png\" alt=\"Mikey\" style=\"object-position:right; width: 400px; float: right;\"></a></div></header>");
            responseHTML.append("<div><h1 style=\"color: white;\">LSU Automatic Scheduler</h1></div>");
            responseHTML.append("<form><input type=\"button\" style=\"border-radius: 6px; margin-top: 5px;\" value=\"Back\" onclick=\"history.back()\"></form>");
            
            //Handle Html result when there is only 1 viable schedule
            if (viableSchedules.size() == 1) {
                responseHTML.append("<div><h2 style=\"color: white;\">Your course search returned one schedule option:</h2></div>");
                responseHTML.append(new ScheduleTableBuilder(viableSchedules.get(0)).returnTableString());
            }
            
            //Handle Html result when there are several viable schedules that can be ranked
            else if (viableSchedules.size() > 1) {
                responseHTML.append("<div><h2 style=\"color: white;\">Your course search returned " + viableSchedules.size() + " schedule configurations. Here are the highest ranked options:</h2></div>");
                responseHTML.append("<p><strong>Morning Course Heavy Schedule</strong></p>");
                responseHTML.append(new ScheduleTableBuilder(DataMngr.getLowestRankedMorningSched()).returnTableString());
                responseHTML.append("<p><strong>Afternoon Course Heavy Schedule</strong></p>");
                responseHTML.append(new ScheduleTableBuilder(DataMngr.getHighestRankedMorningSched()).returnTableString());
                responseHTML.append("<p><strong>Schedule with Minimal Time Between Courses</strong></p>");
                responseHTML.append(new ScheduleTableBuilder(DataMngr.getLowestRankedSpreadSched()).returnTableString());
                responseHTML.append("<p><strong>Schedule with Maximal Time Between Courses</strong></p>");
                responseHTML.append(new ScheduleTableBuilder(DataMngr.getHighestRankedSpreadSched()).returnTableString());
            }

            //Handle Html result when there are no schedules that fit the courses searched
            else {
                responseHTML.append("<div><p>There were no schedules that fit the courses searched. Try a different search.</p></div>");
            }
            responseHTML.append("</body></html>");
            response += responseHTML.toString();

            // send the response
            exchange.sendResponseHeaders(200, response.length());
            OutputStream output = exchange.getResponseBody();
            output.write(response.getBytes());
            output.flush();
            output.close();
        }
    }

    //Take form search data, format correctly, and push each search box entry as a course code abbreviation and number pair
    private Map<String, String> parseFormData(String request) {
        Map<String, String> formData = new HashMap<>();
        String[] pairs = request.split("&");
        for (String pair : pairs) {
            String[] parts = pair.split("=");
            if (parts.length == 2) {
                String key = parts[0];
                String value = parts[1];
                try {
                    key = URLDecoder.decode(key, "UTF-8");
                    value = URLDecoder.decode(value, "UTF-8");
                } catch (UnsupportedEncodingException e) {
                    // ignore
                }
                formData.put(key, value);
            }
        }
        return formData;
    }
}
