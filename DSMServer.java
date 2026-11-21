
import java.net.*;
import java.io.*;

public class DSMServer {

    static int sharedVariable = 10;

    public static void main(String[] args) {

        try {
            ServerSocket server =
                new ServerSocket(5000);

            System.out.println("DSM Server Started");
            System.out.println("Initial Shared Value: "
                               + sharedVariable);

            System.out.println("Waiting for client...");

            while (true) {

                Socket socket = server.accept();

                System.out.println("Client Connected!");

                BufferedReader input =
                    new BufferedReader(
                        new InputStreamReader(
                            socket.getInputStream()
                        )
                    );

                PrintWriter output =
                    new PrintWriter(
                        socket.getOutputStream(), true
                    );

                String request = input.readLine();

                if (request.equals("GET")) {

                    output.println(sharedVariable);

                }

                else if (request.equals("INCREMENT")) {

                    sharedVariable++;

                    output.println(sharedVariable);

                    System.out.println(
                        "Updated Shared Value: "
                        + sharedVariable
                    );
                }

                socket.close();
            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
