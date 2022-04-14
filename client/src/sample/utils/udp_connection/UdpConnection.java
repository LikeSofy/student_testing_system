package sample.utils.udp_connection;

import java.io.IOException;
import java.net.*;
import java.nio.charset.StandardCharsets;

public class UdpConnection {
    public String sendData(String data, String ip, int port) throws IOException {

        /* Создайте экземпляр клиентского сокета.
        Нет необходимости в привязке к определенному порту */
        DatagramSocket clientSocket = new DatagramSocket();
        clientSocket.setSoTimeout(1000);

        // Получите IP-адрес сервера
        InetAddress IPAddress = InetAddress.getByName(ip);

        // Создайте соответствующие буферы
        byte[] sendingDataBuffer = new byte[102400];
        byte[] receivingDataBuffer = new byte[102400];

        /* Преобразуйте данные в байты
          и разместите в буферах */
        sendingDataBuffer = data.getBytes(StandardCharsets.UTF_8);

        // Создайте UDP-пакет
        DatagramPacket sendingPacket = new DatagramPacket(sendingDataBuffer,sendingDataBuffer.length,IPAddress, port);

        // Отправьте UDP-пакет серверу
        clientSocket.send(sendingPacket);

        // Получите ответ от сервера, т.е. предложение из заглавных букв
        DatagramPacket receivingPacket = new DatagramPacket(receivingDataBuffer,receivingDataBuffer.length);
        clientSocket.receive(receivingPacket);

        // Выведите на экране полученные данные
        String receivedData = new String(receivingPacket.getData());
        receivedData = receivedData.trim();

        // Закройте соединение с сервером через сокет
        clientSocket.close();

        return receivedData;
    }
}