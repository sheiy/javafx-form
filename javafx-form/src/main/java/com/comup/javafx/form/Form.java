package com.comup.javafx.form;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public abstract class Form extends Stage {

    private static final Map<String, Form> ALL_FORM = new ConcurrentHashMap<>();

    protected Map<String, Object> params;

    public Form() {
        this(null);
    }

    public Form(Map<String, Object> params) {
        if (params == null) params = new HashMap<>(0);
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getLocation());
        fxmlLoader.setResources(getResourceBundle());
        fxmlLoader.setControllerFactory(param -> this);
        Scene scene;
        try {
            scene = new Scene(fxmlLoader.load());
        } catch (IOException e) {
            runOnFxThread(() -> alert("此应用程序可能已损坏"));
            throw new RuntimeException(e);
        }
        this.setScene(scene);
        ALL_FORM.put(UUID.randomUUID().toString(), this);
    }

    protected void runOnFxThread(Runnable runnable) {
        if (Platform.isFxApplicationThread()) {
            runnable.run();
        } else {
            Platform.runLater(runnable);
        }
    }

    public Optional<ButtonType> alert(String content) {
        Alert alert = new Alert(Alert.AlertType.NONE, content, ButtonType.OK);
        alert.setTitle("");
        alert.initOwner(this);
        return alert.showAndWait();
    }

    public static void closeAllForm() {
        ALL_FORM.forEach((s, form) -> form.close());
    }

    public abstract URL getLocation();

    public abstract ResourceBundle getResourceBundle();
}
