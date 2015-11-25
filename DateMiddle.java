import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Kenneth Mintah Mensah on 11/10/15.
 * This class relays date from  the date server to the date client through
 * another port
 */
public class DateMiddle {


    public static void main(String args[]) throws IOException {

        //check if parameters have been passed
        if(args.length != 2){

            System.out.println("Usage DateMiddle <ip address> " +
                    "<port> eg. DateMiddle 10.10.20.93 9090");
            return;
        }

        //ip address
        String ipAddress = args[0];

        //port
        Integer port = Integer.parseInt(args[1]);


        while (true) {
            try {
                //client socket to receive data from Date Server
                Socket client = new Socket(ipAddress, port);

                //get input stream from the client
                InputStream in = client.getInputStream();

                BufferedReader bin = new BufferedReader(new InputStreamReader(in));

                String date;
                //read date
                while ((date = bin.readLine()) != null) {
                    //forward date to other client
                    forwardDate(date);
                }

                client.close();

            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }


    public static void forwardDate(String date) throws IOException{

        ServerSocket relayServer  = new ServerSocket( 9991);
        while(true) {
            try {
                //listen for connecting clients
                Socket client = relayServer.accept();

                //Output writer
                PrintWriter out = new PrintWriter(client.getOutputStream(), true);


                //write date to client
                out.println(date);


                //close client
                client.close();

            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
