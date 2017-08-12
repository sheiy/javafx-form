package form;

import com.comup.javafx.form.Form;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class LoginForm extends Form {

    /**
     * public 控件 或者private后加@FXML注解
     */
    @FXML
    private TextField _Username;
    public TextField _Password;
    public Button _Login;
    public Button _Exit;

    public LoginForm() {
        this.setOnShowing(event -> {
            System.out.println("调用.Show之前执行");
        });
        this.setOnShown(event -> {
            System.out.println("调用.Show之后执行");
        });
        this.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            //此函数可用于设置快捷键操作

            /**
             * 此方法可中止事件继续传递到界面的控件上，
             * 但会在Filter中继续传递，
             *
             * 对于TextField会将输入字符继续显示到文本框中，但功能型按键将不会对输入框中输入的内容产生影响，
             * 如需过滤输入使用输入框的setOnKeyTyped
             */
            event.consume();
        });
        _Login.setOnAction(event -> {
            Map<String, Object> params = new HashMap<>();
            params.put("username", _Username.getText());
            params.put("password", _Password.getText());
            MainForm mainForm = new MainForm(params);
            this.close();
            mainForm.show();
        });
    }

    public URL getLocation() {
        return LoginForm.class.getResource("/fxml/login.fxml");
    }

    public ResourceBundle getResourceBundle() {
        return ResourceBundle.getBundle("i18n.login");
    }
}
