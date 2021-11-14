package vhrybyniuk.echo;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Objects;

public class Server {
    public static void main(String[] args) throws IOException {

        ServerSocket serverSocket = new ServerSocket(3000);
        while (true) {
            Socket socket = serverSocket.accept();
            System.out.println("Connected with Server");
            try (OutputStream outputStream = socket.getOutputStream();
                 InputStream inputStream = socket.getInputStream()) {
                byte[] buffer = new byte[100];
                int readRequest = inputStream.read(buffer);
                String serverRequest = new String(buffer, 0, readRequest);

                if (Objects.equals(serverRequest, ":q")) {
                    System.out.println("Connected with Server closed");
                    break;
                }
                System.out.println("Server got a message " + serverRequest);
                outputStream.write(("echo " + serverRequest).getBytes());
                outputStream.flush();
            }
        }

    }
}
