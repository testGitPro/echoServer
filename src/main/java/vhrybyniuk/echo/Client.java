package vhrybyniuk.echo;

import java.io.*;
import java.net.Socket;
import java.util.Objects;

public class Client {
    public static void main(String[] args) throws IOException {

        System.out.println("To exit, type :q and hit Enter.");
        while (true) {
            Socket socket = new Socket("localhost", 3000);
            try (InputStream inputStream = socket.getInputStream();
                 OutputStream outputStream = socket.getOutputStream()) {
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(System.in));

                String message = reader.readLine();
                if (Objects.equals(message, ":q")) {
                    outputStream.write(message.getBytes());
                    break;
                }
                outputStream.write(message.getBytes());
                outputStream.flush();
                byte[] data = new byte[100];
                int readBytes = inputStream.read(data);
                System.out.println("SERVER RESPONSE>>> " + new String(data, 0, readBytes));
            }
        }
    }
}
