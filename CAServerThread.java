import java.io.*;


class CAServerThread implements Runnable {
   static final String endMessage = ".";
   static final String logoffMessage = "logoff";
   MyStreamSocket myDataSocket;
   

   CAServerThread(MyStreamSocket myDataSocket) {
      this.myDataSocket = myDataSocket;
   }
 
   public void run( ) {
      boolean done = false;
      String message = "";
      try {
         while (!done) {
				message = myDataSocket.receiveMessage( );
/**/         	System.out.println("message received: "+ message);
				CAServer.setAllMessages(message);
            // if (((message.trim()).equals(endMessage)) || ((message.trim()).equals(logoffMessage))){
                //Session over; close the data socket.
/**/        //    System.out.println("Session over.");
            //    myDataSocket.close( );
            //    done = true;
            // } //end if
            // else {
                // Now send the echo to the requestor
			if(message.equals("DOWNLOAD_MESSAGES")){	
                for(String serverMessage:CAServer.getAllMessages()){					
					myDataSocket.sendMessage(serverMessage);
				}
			}
             //} //end else
          } //end while !done
        }// end try
        catch (Exception ex) {
           System.out.println("Exception caught in thread: " + ex);
        } // end catch
   } //end run
} //end class 
