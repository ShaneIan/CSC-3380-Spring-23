package ClientManager;
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
            
            // send the response
            String response = "Form submitted successfully";
            exchange.sendResponseHeaders(200, response.length());
            OutputStream output = exchange.getResponseBody();
            output.write(response.getBytes());
            output.flush();
            output.close();
        }
    }

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
