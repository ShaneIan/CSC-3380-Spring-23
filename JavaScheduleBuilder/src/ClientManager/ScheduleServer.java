package ClientManager;
import java.io.*;
import java.net.*;
import com.sun.net.httpserver.*;


public class ScheduleServer {
    public static void main(String[] args) throws Exception {
      HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);

      server.createContext("/", new RequestManager());

      server.start();

      System.out.println("Server started on port 8000");
  }
}
