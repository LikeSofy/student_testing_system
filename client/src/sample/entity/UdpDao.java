package sample.entity;

import com.google.gson.Gson;
import sample.entity.results.Results;
import sample.entity.send_commands.Command;
import sample.entity.send_commands.CommandEnum;
import sample.entity.test.Test;
import sample.utils.udp_connection.UdpConnection;

import java.io.IOException;
import java.rmi.ServerException;

public class UdpDao {
    private String ip;
    private int port;
    private Gson gson = new Gson();

    public UdpDao(String ip, int port) {
        this.ip = ip;
        this.port = port;
    }

    public void sendTest(Test test) throws IOException {
        Command command = new Command(CommandEnum.SEND_TEST, gson.toJson(test));
        Command returnCommand = send(command);

        if (returnCommand.getTypeCommand() != CommandEnum.SUCCSESS){
            throw new ServerException("Incorect returned data");
        }

        if (! returnCommand.getData().equals("ok")){
            throw new ServerException("Test not upload");
        }
    }

    public Results getResults() throws IOException {
        Command command = new Command(CommandEnum.GET_RESULTS, "");
        Command returnCommand = send(command);

        if (returnCommand.getTypeCommand() != CommandEnum.GET_RESULTS){
            throw new ServerException("Incorect returned data");
        }

        return (gson.fromJson(returnCommand.getData(), Results.class));
    }

    public Command send(Command command) throws IOException {
        UdpConnection connection = new UdpConnection();
        String send = gson.toJson(command);
        String recive = connection.sendData(send, ip, port);

        return (gson.fromJson(recive, Command.class));
    }
}
