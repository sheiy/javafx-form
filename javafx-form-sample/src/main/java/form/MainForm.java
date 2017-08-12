package form;

import com.comup.javafx.form.Form;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;

public class MainForm extends Form {


    public Label _Tip1;
    public Label _Tip2;

    public MainForm(Map<String, Object> params) {
        //Form间传递参数
        super(params);
        _Tip1.setText(_Tip1.getText() + params.get("username").toString());
        _Tip2.setText(_Tip2.getText() + params.get("password").toString());
    }

    @Override
    public URL getLocation() {
        return MainForm.class.getResource("/fxml/main.fxml");
    }

    @Override
    public ResourceBundle getResourceBundle() {
        return ResourceBundle.getBundle("i18n.main");
    }
}
