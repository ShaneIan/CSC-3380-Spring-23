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
}