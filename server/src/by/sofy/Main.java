package by.sofy;

import by.sofy.entity.command.Command;
import by.sofy.service.CommandProcessing;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.nio.charset.StandardCharsets;

public class Main {
    // Серверный UDP-сокет запущен на этом порту
    public final static int SERVICE_PORT=50001;
    private static Gson gson = new GsonBuilder().create();
    private static CommandProcessing commandProcessing = new CommandProcessing();
    private static boolean cycle = true;

    public static void main(String[] args) throws IOException {
        try (DatagramSocket serverSocket = new DatagramSocket(SERVICE_PORT);){
            while (cycle){
                /* Создайте буферы для хранения получаемых данных.
                Они временно хранят данные в случае задержек связи */
                byte[] receivingDataBuffer = new byte[65535];
                /* Создайте экземпляр UDP-пакета для хранения клиентских данных с использованием буфера для полученных данных */
                DatagramPacket inputPacket = new DatagramPacket(receivingDataBuffer, receivingDataBuffer.length);
                System.out.println("Waiting for a client to connect...");

                // Получите данные от клиента и сохраните их в inputPacket
                serverSocket.receive(inputPacket);
                String receivedData = new String(inputPacket.getData(), StandardCharsets.UTF_8).trim();

                System.out.printf("Get data from: %s, data: %s\n", inputPacket.getAddress(), receivedData);

                // Обработка комманд
                Command command = gson.fromJson(receivedData, Command.class);
                Command reciveCommand = commandProcessing.processCommand(command);

                String reciveData = gson.toJson(reciveCommand);

                System.out.printf("return data: %s\n", reciveData);

                /*
                 * Преобразуйте отправленные клиентом данные в верхний регистр,
                 * Преобразуйте их в байты
                 * и сохраните в соответствующий буфер. */
                byte[] sendingDataBuffer = reciveData.getBytes();

                // Получите IP-адрес и порт клиента
                InetAddress senderAddress = inputPacket.getAddress();
                int senderPort = inputPacket.getPort();

                // Создайте новый UDP-пакет с данными, чтобы отправить их клиенту
                DatagramPacket outputPacket = new DatagramPacket(
                        sendingDataBuffer, sendingDataBuffer.length,
                        senderAddress,senderPort
                );

                // Отправьте пакет клиенту
                serverSocket.send(outputPacket);
            }
        }
        catch (SocketException e){
            e.printStackTrace();
        }

    }
}
