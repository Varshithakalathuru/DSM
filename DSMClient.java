
import java.io.*;
import java.net.*;

public class DSMClient {

    static String SERVER_IP = "11.12.3.146";
    static int PORT = 5000;

    // Method to communicate with server
    static String sendRequest(String request)
            throws Exception {

        Socket socket = new Socket(SERVER_IP, PORT);

        PrintWriter output =
            new PrintWriter(
                socket.getOutputStream(), true
            );

        BufferedReader input =
            new BufferedReader(
                new InputStreamReader(
                    socket.getInputStream()
                )
            );

        output.println(request);

        String response = input.readLine();

        socket.close();

        return response;
    }

    public static void main(String[] args) {

        try {

            System.out.println("Connected to DSM Server");

            // Read initial shared value
            String value = sendRequest("GET");

            System.out.println(
                "Initial Shared Value: " + value
            );

            // Increment shared variable
            String updated = sendRequest("INCREMENT");

            System.out.println(
                "Updated Shared Value: " + updated
            );

            // Read the value again
            String finalValue = sendRequest("GET");

            System.out.println(
                "Final Shared Value: " + finalValue
            );

        } catch (Exception e) {

            System.out.println("Error: " + e.getMessage());

        }
    }
}
