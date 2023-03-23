import java.net.*;
import java.io.*;

/**
 * This class is a module which provides the application logic
 * for an Echo client using stream-mode socket.
 * @author M. L. Liu
 */

public class CAClientHelper {

   static final String endMessage = ".";
   static final String logoffMessage = "logoff";
   private MyStreamSocket mySocket;
   private InetAddress serverHost;
   private int serverPort;

   private String username;
   private String password;

   CAClientHelper(String hostName, String portNum, String username, String password) 
					throws SocketException, UnknownHostException, IOException {
                                     
  	    this.serverHost = InetAddress.getByName(hostName);
  		this.serverPort = Integer.parseInt(portNum);
        //Instantiates a stream-mode socket and wait for a connection.
		this.mySocket = new MyStreamSocket(this.serverHost, this.serverPort); 
		this.username = username;
		this.password = password;
/**/  System.out.println("Connection request made");
			
   } // end constructor
	
   public String getEcho(String message) throws SocketException,
      IOException{     
      String echo = "";    
      mySocket.sendMessage( message);
	   // now receive the echo
      echo = mySocket.receiveMessage();
      return echo;
   } // end getEcho
   
   public void sendMessageToServer(String message)throws IOException{
	   mySocket.sendMessage( message);
   }

   public void done() throws SocketException, IOException{
      mySocket.sendMessage(endMessage);
	  mySocket.sendMessage(logoffMessage);
      mySocket.close();
   } // end done 
   
   public void getAllServerMessages() throws IOException{
		mySocket.getMessages();
   }
} //end class
