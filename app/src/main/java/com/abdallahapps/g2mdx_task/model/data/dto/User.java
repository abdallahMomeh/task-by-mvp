package com.abdallahapps.g2mdx_task.model.data.dto;

public class User {

    private int id;
    private String username;
    private String password;
    private boolean loginFB;
    private String facebookToken;
    private boolean remembered;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public boolean isLoginFB() {
        return loginFB;
    }

    public void setLoginFB(boolean loginFB) {
        this.loginFB = loginFB;
    }

    public String getFacebookToken() {
        return facebookToken;
    }

    public void setFacebookToken(String facebookToken) {
        this.facebookToken = facebookToken;
    }

    public boolean isRemembered() {
        return remembered;
    }

    public void setRemembered(boolean remembered) {
        this.remembered = remembered;
    }
}
