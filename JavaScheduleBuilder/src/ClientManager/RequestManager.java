package ClientManager;
import java.io.*;
import java.util.*;
import java.net.*;
import java.nio.file.Files;
import com.sun.net.httpserver.*;

public class RequestManager implements HttpHandler {   

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String requestMethod = exchange.getRequestMethod();
        File file = new File("./JavaScheduleBuilder/src/ClientManager/index.html");
        byte[] bytes = Files.readAllBytes(file.toPath());

        exchange.getResponseHeaders().set("Content-Type", "text/html");
        exchange.sendResponseHeaders(200, bytes.length);

        OutputStream os = exchange.getResponseBody();
        os.write(bytes);
        os.close();
        if (requestMethod.equalsIgnoreCase("POST")) {
            // Process the form submission
            InputStreamReader isr = new InputStreamReader(exchange.getRequestBody(), "utf-8");
            BufferedReader br = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line).append("\n");
            }
            String formData = sb.toString();

            // Handle the form data here

            // Send the response
            String response = "Form submission received!";
                exchange.getResponseHeaders().set("Content-Type", "text/plain");
                exchange.sendResponseHeaders(200, response.length());

                OutputStream ost = exchange.getResponseBody();
                ost.write(response.getBytes());
                ost.close();
            }
        }

    //Method callable on html form submission to handle form submit
    public void handleCourseSearch() {

    }

    //Return constructed schedules to the user interface
    public void serveScheduleData() {

    }

    //Collect html form data and format it into a form usable by CourseDataManager
    public void processForm(){

    }

    //Using search information, construct a dataManager to query course database and construct a viable schedules
    public void buildDataManagerFromSearch(){
        
    }
}