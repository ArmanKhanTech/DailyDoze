package com.android.dailydoze.Utility;

public class Signup {
    private String email, name, pass, phone;

    public Signup() {

    }

    public String getUserMail() {
        return email;
    }

    public void setUserMail(String email) {
        this.email = email;
    }

    public String getUserName() {
        return name;
    }

    public void setUserName(String name) {
        this.name = name;
    }

    public String getUserPhone() {
        return phone;
    }

    public void setUserPhone(String phone) {
        this.phone = phone;
    }

    public String getUserPass() {
        return pass;
    }

    public void setUserPass(String pass) {
        this.pass = pass;
    }
}