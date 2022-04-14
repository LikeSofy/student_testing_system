package sample.entity;

import com.google.gson.Gson;
import sample.entity.command.Command;
import sample.entity.command.CommandEnum;
import sample.entity.result.Result;
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

    public Test getTest() throws IOException {
        Command command = new Command(CommandEnum.GET_TEST, "");
        Command returnCommand = send(command);

        if (returnCommand.getTypeCommand() != CommandEnum.GET_TEST){
            throw new ServerException("Incorect returned data");
        }

        if (returnCommand.getData().equals("null")){
            throw new ServerException("Test not upload");
        }

        Test test = gson.fromJson(returnCommand.getData(), Test.class);

        return test;
    }

    public void sendResult(Result result) throws IOException {
        String json = gson.toJson(result);
        Command command = new Command(CommandEnum.SEND_RESULT, json);
        Command returnCommand = send(command);

        if (returnCommand.getTypeCommand() != CommandEnum.SUCCSESS){
            throw new ServerException("Incorect returned data");
        }

        if (! returnCommand.getData().equals("ok")){
            throw new ServerException("Test not upload");
        }
    }

    public Command send(Command command) throws IOException {
        UdpConnection connection = new UdpConnection();
        String send = gson.toJson(command);
        String recive = connection.sendData(send, ip, port);
        Command reciveCommand = gson.fromJson(recive, Command.class);

        return reciveCommand;
    }
}
