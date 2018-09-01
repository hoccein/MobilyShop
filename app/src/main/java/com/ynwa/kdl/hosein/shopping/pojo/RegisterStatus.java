package com.ynwa.kdl.hosein.shopping.pojo;

public class RegisterStatus {
    private boolean registered;
    private String msg;

    public RegisterStatus(){

    }

    public RegisterStatus(boolean registered, String msg){
        this.registered = registered;
        this.msg = msg;
    }

    public boolean isRegistered() {
        return registered;
    }

    public void setRegistered(boolean registered) {
        this.registered = registered;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
