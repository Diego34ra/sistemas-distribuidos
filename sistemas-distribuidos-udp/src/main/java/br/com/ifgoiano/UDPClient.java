package br.com.ifgoiano;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class UDPClient {
    private static final String SERVER_IP = "localhost";
    private static final int SERVER_PORT = 12345;

    public static void main(String[] args) {
        try (DatagramSocket socket = new DatagramSocket();
             Scanner scanner = new Scanner(System.in)) {

            InetAddress serverAddress = InetAddress.getByName(SERVER_IP);

            System.out.print("Digite o fuso horário (ex: America/Sao_Paulo): ");
            String timezone = scanner.nextLine();

            byte[] requestData = timezone.getBytes();
            DatagramPacket requestPacket = new DatagramPacket(
                    requestData,
                    requestData.length,
                    serverAddress,
                    SERVER_PORT
            );
            socket.send(requestPacket);

            byte[] buffer = new byte[1024];
            DatagramPacket responsePacket = new DatagramPacket(buffer, buffer.length);
            socket.receive(responsePacket);

            String response = new String(
                    responsePacket.getData(),
                    0,
                    responsePacket.getLength()
            );
            System.out.println("Hora local: " + response);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
