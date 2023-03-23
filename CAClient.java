import java.io.*;

/**
 * This module contains the presentaton logic of an Echo Client.
 * @author M. L. Liu
 */
public class CAClient {
   static final String endMessage = ".";
   static final String logOffMessage = "logoff";
   public static void main(String[] args) {
      InputStreamReader is = new InputStreamReader(System.in);
      BufferedReader br = new BufferedReader(is);
      try {
         
         System.out.println("Welcome to the Echo client.\n" + "What is the name of the server host?");
         String hostName = br.readLine();
         if (hostName.length() == 0) // if user did not enter a name
            hostName = "localhost";  //   use the default host name

         System.out.println("What is the port number of the server host?");
         String portNum = br.readLine();
         if (portNum.length() == 0)
            portNum = "7";          // default port number
		
		System.out.println("Enter your username for the server: ");
         String username = br.readLine();
         if (username.length() == 0){
            username = "user1";
         }
         System.out.println(("Enter your password for the server: "));
         String password = br.readLine();
         if (password.length() == 0){
            password = "password";
         }

         CAClientHelper helper = new CAClientHelper(hostName, portNum, username, password);
         boolean done = false;
         String message, echo;
         while (!done) {
            System.out.println("Enter message to send to the server.");
            message = (username +": " + br.readLine());
			if(message.equals("DOWNLOAD_MESSAGES")){
				helper.getAllServerMessages();
			}
            if (((message.trim()).equals(endMessage)) || ((message.trim()).equals(logOffMessage))){
               done = true;
               helper.done();
            } 
            else {
               helper.sendMessageToServer(message);
            }
          } // end while
      } // end try  
      catch (Exception ex) {
         ex.printStackTrace( );
      } //end catch
   } //end main
} // end class
