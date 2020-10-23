package app.entity;

import app.common.AccountPositionEnum;

public class Account implements IEntity {
    private int id;

    private String name;

    private String username;

    private String password;

    private AccountPositionEnum accountPositionEnum;

    public Account() {

    }

    public Account(int id, String name) {
        setId(id);
        setName(name);
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public int getId() {
        return 0;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
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

    public int getAccountPosition() {
        return accountPositionEnum.getValue();
    }

    public void setAccountPositionEnum(AccountPositionEnum accountPositionEnum) {
        this.accountPositionEnum = accountPositionEnum;
    }
}
