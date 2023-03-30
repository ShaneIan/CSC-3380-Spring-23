package ClientManager;
import java.io.*;
import java.util.*;
import java.net.*;
import com.sun.net.httpserver.*;
import CourseDataManager.*;


public class ScheduleServer {
  
    public static void main(String[] args) throws Exception {
      HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);

      server.createContext("/", new StaticFileServer());

      server.start();

      System.out.println("Server started on port 8000");
      DatabaseOperations dbOper = new DatabaseOperations();
      String[] dbResponse = dbOper.fetchDataByCourseCode("ACCT", "2000");
      System.out.print(Arrays.toString(dbResponse));
  }
}
