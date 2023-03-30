package ClientManager;
import java.io.*;

import com.sun.net.httpserver.*;

public class StaticFileServer implements HttpHandler {   
    private final static String INDEX_HTML = "./JavaScheduleBuilder/src/ClientManager/index.html";
    private final static String INDEX_CSS = "./JavaScheduleBuilder/src/ClientManager/styles.css";

    @Override
    public void handle(HttpExchange exchange) throws IOException {
      File htmlFile = new File(INDEX_HTML);
      File cssFile = new File(INDEX_CSS);
      exchange.sendResponseHeaders(200, 0);
      OutputStream output = exchange.getResponseBody();
      FileInputStream htmlFs = new FileInputStream(htmlFile);
      FileInputStream cssFs = new FileInputStream(cssFile);

      final byte[] buffer = new byte[0x10000];
      int count = 0;
      while ((count = htmlFs.read(buffer)) >= 0) {
          output.write(buffer, 0, count);
      }
      while ((count = cssFs.read(buffer)) >= 0) {
        output.write(buffer, 0, count);
    }
      output.flush();
      output.close();
      htmlFs.close();
      cssFs.close();
    }
/* 
        String requestMethod = exchange.getRequestMethod();
        File htmlFile = new File("./JavaScheduleBuilder/src/ClientManager/index.html");
        byte[] htmlBytes = Files.readAllBytes(htmlFile.toPath());
        File cssFile = new File("./JavaScheduleBuilder/src/ClientManager/styles.css");
        byte[] cssBytes = Files.readAllBytes(cssFile.toPath());

        exchange.getResponseHeaders().set("Content-Type", "text/html");

        exchange.sendResponseHeaders(200, htmlBytes.length);

        OutputStream os = exchange.getResponseBody();
        os.write(htmlBytes);
        os.write(cssBytes);
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
*/

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