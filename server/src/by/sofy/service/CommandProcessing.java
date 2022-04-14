package by.sofy.service;

import by.sofy.entity.result.Result;
import by.sofy.entity.result.ResultsSingleton;
import by.sofy.entity.test.Test;
import by.sofy.entity.test.TestSingleton;
import by.sofy.entity.command.Command;
import by.sofy.entity.command.CommandEnum;
import com.google.gson.Gson;

public class CommandProcessing {
    Gson gson = new Gson();

    public Command processCommand(Command command){
        Command reciveCommand = new Command(CommandEnum.SUCCSESS, "error");

        if (command.getTypeCommand() == CommandEnum.SEND_TEST){
            processCommandSendTest(command.getData());
            reciveCommand = new Command(CommandEnum.SUCCSESS, "ok");
        }

        else if (command.getTypeCommand() == CommandEnum.GET_TEST){
            String jsonTest = processCommandGetTest();
            reciveCommand = new Command(CommandEnum.GET_TEST, jsonTest);
        }

        else if (command.getTypeCommand() == CommandEnum.SEND_RESULT){
            processCommandSendResult(command.getData());
            reciveCommand = new Command(CommandEnum.SUCCSESS, "ok");
        }

        else if (command.getTypeCommand() == CommandEnum.GET_RESULTS){
            String jsonResults = processCommandGetResults();
            reciveCommand = new Command(CommandEnum.GET_RESULTS, jsonResults);
        }

        return reciveCommand;
    }

    private void processCommandSendResult(String data){
        Result result = gson.fromJson(data, Result.class);
        ResultsSingleton.getInstance().add(result);
    }

    private String processCommandGetTest(){
        return (gson.toJson(TestSingleton.getInstance().getTest()));
    }

    private void processCommandSendTest(String data){
        Test test = gson.fromJson(data, Test.class);
        TestSingleton.getInstance().setTest(test);
    }

    private String processCommandGetResults(){
        return (gson.toJson(ResultsSingleton.getInstance()));
    }


}
