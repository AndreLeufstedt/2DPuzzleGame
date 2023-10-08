package Game;

import Engine.Objects.GameObject;
import Engine.Objects.Shapes;
import Engine.Player;
import Engine.Renderer.GraphicsHandler;
import Engine.Renderer.ObjectRender;

import Engine.Utils.Vector2D;
import javafx.application.Application;
import javafx.stage.Stage;

import java.util.Vector;

import static Engine.Objects.GameObject.GameObjects;

public class Main extends Application {

    public static ObjectRender ObjectHandler = new ObjectRender();

    static {
        GameObject PLAYER = new Player(new Vector2D(450, 450), 100, 50, Shapes.PLAYER);
        GameObjects.add(PLAYER);

        GameObject Floor = new GameObject(new Vector2D(0, 900-128), 128, 900, Shapes.CUBE);
        GameObjects.add(Floor);
    }

    public static void main(String[] args) {
        launch(args);



    }


    @Override
    public void start(Stage stage) {
        GraphicsHandler.generateWindow(stage, "Game", 900, 900);


    }
}
