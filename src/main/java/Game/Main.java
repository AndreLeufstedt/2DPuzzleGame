package Game;

import Engine.Objects.GameObject;
import Engine.Objects.Shapes;
import Renderer.ObjectRender;
import Renderer.Window.*;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.application.Application.Parameters;
import javafx.stage.Stage;

import static javafx.application.Application.launch;

public class Main extends Application {

    public static ObjectRender ObjectHandler = new ObjectRender();

    public static void main(String[] args) {


        GameObject myObject = new GameObject(150, 150, 50, 50, Shapes.CUBE);
        ObjectHandler.addObjectToRender(myObject);

        launch(args);
    }


    @Override
    public void start(Stage stage) throws Exception {
        GenerateWindow.generateWindow(stage, "Game", 900, 900);


    }
}
