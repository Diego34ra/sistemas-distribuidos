package br.com.ifgoiano;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.time.DateTimeException;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class UDPServer {
    private static final int PORT = 12345;
    private static final int BUFFER_SIZE = 1024;

    public static void main(String[] args) {
        try (DatagramSocket socket = new DatagramSocket(PORT)) {
            System.out.println("Servidor UDP ouvindo na porta " + PORT);

            byte[] buffer = new byte[BUFFER_SIZE];

            while (true) {
                DatagramPacket requestPacket = new DatagramPacket(buffer, buffer.length);
                socket.receive(requestPacket);

                String timezone = new String(requestPacket.getData(), 0, requestPacket.getLength()).trim();

                String response;

                try {
                    ZonedDateTime zdt = ZonedDateTime.now(ZoneId.of(timezone));
                    response = zdt.toLocalDateTime().toString();
                } catch (DateTimeException e) {
                    response = "Fuso horário inválido!";
                }

                byte[] responseData = response.getBytes();
                InetAddress clientAddress = requestPacket.getAddress();
                int clientPort = requestPacket.getPort();

                DatagramPacket responsePacket = new DatagramPacket(
                        responseData,
                        responseData.length,
                        clientAddress,
                        clientPort
                );
                socket.send(responsePacket);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
