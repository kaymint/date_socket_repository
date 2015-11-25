import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
/**
 * Created by StreetHustling on 11/10/15.
 */
public class DateServer {

    public static void main(String args[]) throws IOException {

        //create server socket

        ServerSocket server = new ServerSocket(9990);

        while(true) {
            try {
                //listen for connecting clients
                Socket client = server.accept();

                //Output writer
                PrintWriter out = new PrintWriter(client.getOutputStream(), true);


                //write date to client
                out.println(new Date().toString());


                //close client
                client.close();

            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

    }
}
