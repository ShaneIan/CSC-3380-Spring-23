package ClientManager;
import java.net.*;
import com.sun.net.httpserver.*;


public class ScheduleServer {
  
    public static void main(String[] args) throws Exception {
      HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);

      //Creates webpage context for web app home page
      server.createContext("/", new StaticFileServer());

      //Creates webpage context for course search results
      server.createContext("/submit", new FormHandler());

      server.start();

      System.out.println("Server started");
  }
}
