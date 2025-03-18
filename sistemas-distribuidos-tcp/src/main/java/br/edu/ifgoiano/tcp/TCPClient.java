package br.edu.ifgoiano.tcp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class TCPClient {
    public static void main(String[] args) {
        String serverAddress = "localhost";
        int port = 5000;

        try (Socket socket = new Socket(serverAddress, port);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in))) {

            System.out.print("Digite a região geográfica (ex: America/Sao_Paulo): ");
            String region = userInput.readLine();
            out.println(region);

            String response = in.readLine();
            System.out.println("Hora na região " + region + ": " + response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
