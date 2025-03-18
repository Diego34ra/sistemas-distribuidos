package br.edu.ifgoiano.tcp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class TCPServer {
    public static void main(String[] args) {
        int port = 5000;
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Servidor TCP iniciado na porta " + port);

            while (true) {
                try (Socket clientSocket = serverSocket.accept();
                     BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                     PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {

                    String region = in.readLine();
                    System.out.println("Região recebida: " + region);

                    String response;
                    try {
                        ZonedDateTime dateTime = ZonedDateTime.now(ZoneId.of(region));
                        response = dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss z"));
                    } catch (Exception e) {
                        response = "Região inválida";
                    }

                    out.println(response);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
