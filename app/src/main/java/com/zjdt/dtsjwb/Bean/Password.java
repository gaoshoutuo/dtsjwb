package com.zjdt.dtsjwb.Bean;

public class Password {
    private String username;
    private String password;
    private boolean married;

    public Password(String username, String password,boolean married) {
        this.username = username;
        this.password = password;
        this.married=married;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isMarried() {
        return married;
    }

    public void setMarried(boolean married) {
        this.married = married;
    }
}
