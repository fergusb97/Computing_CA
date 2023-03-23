import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

public class CAServer {
	static List<String> allmessages = new ArrayList<>();
   public static void main(String[] args) {
      int serverPort = 7;    // default port
      String message;

	  
      if (args.length == 1 )
         serverPort = Integer.parseInt(args[0]);       
      try {
         // instantiates a stream socket for accepting
         //   connections
   	   ServerSocket myConnectionSocket = new ServerSocket(serverPort); 
/**/     System.out.println("Echo server ready.");  
         while (true) {  // forever loop
            // wait to accept a connection 
/**/        System.out.println("Waiting for a connection.");
            MyStreamSocket myDataSocket = new MyStreamSocket(myConnectionSocket.accept( ));
/**/        System.out.println("connection accepted");
            // Start a thread to handle this client's sesson
            Thread theThread = new Thread(new CAServerThread(myDataSocket));
            theThread.start();
            // and go on to the next client
            } //end while forever
       } // end try
	    catch (Exception ex) {
          ex.printStackTrace( );
	    } // end catch
   } //end main
   
   public static void setAllMessages(String message){
	   allmessages.add(message);
   }
   
   public static List<String> getAllMessages(){
		return allmessages;
    }
   
} // end class
