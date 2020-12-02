package app.services;

import app.database.MSSQLDatabase;
import app.entity.Account;
import app.model.AuthMessage;
import app.services.common.LogService;
import app.services.common.NotiService;
import app.utility.SecurityUtils;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class AuthService implements IService {
    private Account currentAccount;

    public AuthService () {
    }

    public AuthMessage login(String username, String password) {
        try {
            Account searchedAccount = MSSQLDatabase.getInstance().searchAccount(username);
            String hashedPassword = SecurityUtils.getMD5Hash(password);
            if (searchedAccount != null) {
                if (hashedPassword.equals(searchedAccount.getPassword())) {
                    this.currentAccount = searchedAccount;

                    LogService.info("Login successfully!");
                    return new AuthMessage(true, "Đăng nhập thành công.");
                } else {
                    return new AuthMessage(false, "Mật khẩu không chính xác. Vui lòng nhập lại.");
                }
            } else {
                return new AuthMessage(false, "Không tìm thấy tài khoản.");
            }

        } catch (Exception e) {
            LogService.error("AuthService: " + e.getMessage());
            return new AuthMessage(false, e.getMessage());
        }
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
