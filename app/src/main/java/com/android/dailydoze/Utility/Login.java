package com.android.dailydoze.Utility;

@SuppressWarnings("ALL")
public class Login {
    private String email, pass;

    public Login() {

    }

    public String getUserMail() {
        return email;
    }

    public String getUserPass() {
        return pass;
    }

    public void setUserMail(String email) {
        this.email = email;
    }

    public void setUserPass(String pass) {
        this.pass = pass;
    }
}
