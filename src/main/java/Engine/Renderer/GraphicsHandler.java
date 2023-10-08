package Engine.Renderer;

import Engine.Objects.GameObject;
import Engine.Objects.Shapes;
import Engine.Utils.Vector2D;
import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.util.HashSet;
import java.util.Set;

import static Game.Main.ObjectHandler;


public class GraphicsHandler {

    private static final StackPane root = new StackPane();
    public Scene scene = null;
    public static Pane collisionLayer = new Pane();

    public static Pane staticLayer = new Pane();

    public static Pane playerLayer = new Pane();

    public static Set<KeyCode> pressedKeys = new HashSet<>();

    public static void generateWindow(Stage primaryStage, String WindowName, int Height, int Width) {
        // Add all predefined Layers to root.
        root.getChildren().addAll(playerLayer, collisionLayer, staticLayer);
        Scene scene = new Scene(root, Height, Width);

        primaryStage.setTitle(WindowName);

        // Add key pressed event handler
        scene.setOnKeyPressed(event -> {
            KeyCode key = event.getCode();
            pressedKeys.add(key);
            System.out.println("Currently pressed keys: " + pressedKeys);
        });

        // Add key released event handler
        scene.setOnKeyReleased(event -> {
            KeyCode key = event.getCode();
            pressedKeys.remove(key);
            System.out.println("Currently pressed keys: " + pressedKeys);
        });

        // Add mouse click event handler
        scene.setOnMouseClicked(event -> {
            double x = event.getX();
            double y = event.getY();
            GameObject newObject = new GameObject(new Vector2D(x, y), 50, 50, Shapes.CUBE);
            ObjectHandler.addObjectToRender(newObject);
        });

        primaryStage.setScene(scene);
        primaryStage.show();

        Button addButton = new Button("Add Object");
        addButton.setOnAction(e -> {
            GameObject newObject = new GameObject(new Vector2D(150, 150), 50, 50, Shapes.CUBE);
            ObjectHandler.addObjectToRender(newObject);
        });
        staticLayer.getChildren().add(addButton);


        new AnimationTimer() {
            @Override
            public void handle(long now) {
                collisionLayer.getChildren().clear();
                playerLayer.getChildren().clear();
                //System.out.print("Rendering...");

                ObjectHandler._ObjectRender();
            }
        }.start();


    }

    public void addLayerToRoot(Pane Layer) {
        root.getChildren().add(Layer);
    }

    public void removeLayerFromRoot(Pane Layer) {
        root.getChildren().remove(Layer);
    }

}