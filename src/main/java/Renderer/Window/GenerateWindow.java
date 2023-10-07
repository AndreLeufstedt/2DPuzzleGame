package Renderer.Window;
import Engine.Objects.GameObject;
import Engine.Objects.Shapes;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class GenerateWindow {

    public Scene scene = null;
    public static Pane root;

    public static void generateWindow(Stage primaryStage, String WindowName, int Height, int Width ) {
        root = new Pane();

        Scene scene = new Scene(root, Height, Width);

        primaryStage.setTitle(WindowName);
        primaryStage.setScene(scene);
        primaryStage.show();


    }

}