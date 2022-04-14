package by.sofy.entity.command;

import com.google.gson.annotations.Expose;

public class Command {
    @Expose
    private CommandEnum typeCommand;
    @Expose
    private String data;

    public Command(CommandEnum typeCommand, String data) {
        this.typeCommand = typeCommand;
        this.data = data;
    }

    public CommandEnum getTypeCommand() {
        return typeCommand;
    }

    public void setTypeCommand(CommandEnum typeCommand) {
        this.typeCommand = typeCommand;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
