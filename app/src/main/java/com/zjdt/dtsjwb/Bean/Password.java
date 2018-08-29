package com.zjdt.dtsjwb.Bean;

public class Password {
    private String username;
    private String password;
    private boolean married;
    private String authorthm;

    public String getAuthorthm() {
        return authorthm;
    }

    public void setAuthorthm(String authorthm) {
        this.authorthm = authorthm;
    }

    public Password(String username, String password, boolean married,String authorthm) {
        this.username = username;
        this.password = password;
        this.married=married;
        this.authorthm=authorthm;
    }

    public Password() {
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
