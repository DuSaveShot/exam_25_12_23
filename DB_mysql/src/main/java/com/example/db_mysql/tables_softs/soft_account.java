package com.example.db_mysql.tables_softs;

public class soft_account {

    String login, password;
    Integer role;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    public soft_account(String login, String password, Integer role) {
        this.login = login;
        this.password = password;
        this.role = role;
    }
}
