package app.services;

import app.database.MSSQLDatabase;
import app.entity.Account;
import app.model.AuthMessage;
import app.services.common.LogService;
import app.services.common.NotiService;
import app.utility.SecurityUtils;
import javafx.css.Match;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AuthService implements IService {
    private Account currentAccount;

    public AuthService () {
    }

    public AuthMessage login(String username, String password) {
//        Pattern pattern = Pattern.compile("[$%^&*#@!,;'.?=|]", Pattern.CASE_INSENSITIVE);
//        Matcher matcherUsername = pattern.matcher(username);
//        if (matcherUsername.find()){
//            return new AuthMessage(false, "Tài khoản hoặc không được chứa kí tự đặc biệt!");
//        }
        if(username.isEmpty() || password.isEmpty()){
            if (username.isEmpty() && password.isEmpty()){
                return new AuthMessage(false, "Bạn chưa nhập tài khoản!");

            }
            else if (username.isEmpty()) {
                return new AuthMessage(false, "Bạn chưa nhập tên tài khoản!");
            }
            else if (password.isEmpty()){
                return new AuthMessage(false, "Bạn chưa nhập mật khẩu!");
            }
        }
        else {
            try {
                Account searchedAccount = MSSQLDatabase.getInstance().searchAccount(username);
                String hashedPassword = SecurityUtils.getMD5Hash(password);
                if (searchedAccount != null) {
                    if (hashedPassword.equals(searchedAccount.getPassword())) {
                        this.currentAccount = searchedAccount;
                        LogService.info("Login successfully!");
                        return new AuthMessage(true, "Đăng nhập thành công!");
                    } else {
                        return new AuthMessage(false, "Mật khẩu không chính xác. Vui lòng nhập lại!");
                    }
                } else {
                    return new AuthMessage(false, "Không tìm thấy tài khoản!");
                }

            } catch (Exception e) {
                LogService.error("AuthService: " + e.getMessage());
                return new AuthMessage(false, e.getMessage());
            }
        }
        return new AuthMessage();
    }

    public Account getCurrentAccount() {
        return this.currentAccount;
    }

    public void clearAccount() {
        this.currentAccount = null;
    }

    public static void main(String[] args) {
        AuthService authService = new AuthService();
        AuthMessage authMessage = authService.login("admin1", "123456");

        System.out.println(authMessage.getMessage());
    }
}
