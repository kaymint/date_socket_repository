import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * Created by Kenneth Mintah on 11/11/15.
 * This class receives current date and time from the middle server
 */
public class DateClient {

    public static void main(String args[]) throws IOException {

        //check if arguments have been passed
        if(args.length != 2){

            System.out.println("Usage DateClient <ip address> " +
                    "<port> eg. DateClient 10.10.20.93 9090");
            return;
        }

        //ip address
        String ipAddress = args[0];

        //port
        Integer port = Integer.parseInt(args[1]);

        //client socket to receive data from middle ware
        Socket client = new Socket(ipAddress, port);

        try {

            InputStream in = client.getInputStream();

            BufferedReader bin = new BufferedReader(new InputStreamReader(in));

            String date;
            while ((date = bin.readLine()) != null) {
                System.out.println("Date: " + date);
            }

            client.close();

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
