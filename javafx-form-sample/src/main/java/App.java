import form.LoginForm;
import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application {
    public void start(Stage primaryStage) throws Exception {
        //使用此套窗口管理方案不需要primaryStage
        new LoginForm().show();
    }

public static void main(String[] args) {
        launch(args);
        }
        }
