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

    public Account(int id, int position, String name, String username, String password) {
        setId(id);
        setName(name);
        setAccountPosition(position);
        setUsername(username);
        setPassword(password);
    }

    @Override
    public String getName() {
        return name;
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

    public AccountPositionEnum getAccountPositionEnum() {
        return accountPositionEnum;
    }

    public void setAccountPosition(AccountPositionEnum accountPositionEnum) {
        this.accountPositionEnum = accountPositionEnum;
    }

    public void setAccountPosition(int pos) {
        this.accountPositionEnum = AccountPositionEnum.NONE.setValue(pos);
    }
}
