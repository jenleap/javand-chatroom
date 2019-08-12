package edu.udacity.java.nano.chat;

/**
 * WebSocket message model
 */
public class Message {
    private String msg;
    private String username;
    private String type;
    private int onlineCount;

    public Message() {}

    public Message(String msg, String username, String type, int onlineCount){
        this.msg = msg;
        this.username = username;
        this.type = type;
        this.onlineCount = onlineCount;
    }

    public String getMsg() {
        return msg;
    }

    public String getUsername() {
        return username;
    }

    public String getType() {
        return type;
    }

    public int getOnlineCount() {
        return onlineCount;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setOnlineCount(int onlineCount) {
        this.onlineCount = onlineCount;
    }

    public String toJsonStr() {
        return "{"
                + "\"msg\": \"" + this.msg + "\", "
                + "\"username\": \"" + this.username + "\", "
                + "\"type\": \"" + this.type + "\", "
                + "\"onlineCount\": \"" + this.onlineCount
                + "\" }";
        }
}
