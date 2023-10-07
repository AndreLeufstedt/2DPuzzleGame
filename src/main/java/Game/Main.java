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
    public static void main(String[] args) {
        launch(args);
        ObjectRender ObjectHandler = new ObjectRender();
        GameObject myObject = new GameObject(150, 150, 50, 50, Shapes.CUBE);
        ObjectHandler.addObjectToRender(myObject);
        new AnimationTimer() {
            @Override
            public void handle(long now) {

                ObjectHandler._ObjectRender();
            }
        }.start();
    }


    @Override
    public void start(Stage stage) throws Exception {
        GenerateWindow.generateWindow(stage, "Game", 900, 900);


    }
}
