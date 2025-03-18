package br.edu.ifgoiano.tcpThreads;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class ClientHandler implements Runnable {
    private Socket clientSocket;

    public ClientHandler(Socket socket) {
        this.clientSocket = socket;
    }

    @Override
    public void run() {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
             PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {

            String region = in.readLine();
            String response;

            try {
                ZonedDateTime zonedDateTime = ZonedDateTime.now(ZoneId.of(region));
                response = zonedDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss z"));
            } catch (Exception e) {
                response = "Região inválida";
            }

            out.println(response);
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

