package sample.entity;

import java.util.Objects;

public class LoginWindowData {
    private String username;
    private String ip;
    private int port;

    public LoginWindowData() {
    }

    public LoginWindowData(String username, String ip, int port) {
        this.username = username;
        this.ip = ip;
        this.port = port;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LoginWindowData loginWindowData = (LoginWindowData) o;
        return port == loginWindowData.port && Objects.equals(username, loginWindowData.username) && Objects.equals(ip, loginWindowData.ip);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, ip, port);
    }
}
