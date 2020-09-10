package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javax.swing.filechooser.FileSystemView;
import java.io.File;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("sampl2.fxml"));
        primaryStage.setTitle("Aktualny samochód");
        primaryStage.setScene(new Scene(root, 1300, 600));
        primaryStage.setFullScreen(true);
        primaryStage.show();

        File file = new File(FileSystemView.getFileSystemView().getDefaultDirectory() + "\\aktualnyUbojErrorLog.txt");
        if (file.createNewFile()) {
            System.out.println("Utworzone");
        } else {
            System.out.println("Plik istnieje");
        }
    }
}
