package Engine.Renderer;

import Engine.Objects.GameObject;
import Engine.Objects.Shapes;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

import static Engine.Objects.GameObject.GameObjects;

public class ObjectRender {


    public void _ObjectRender() {
        for (GameObject object : GameObjects) {
            object.UpdateObject();
            renderObject(object);

        }

    }


    private void renderObject(GameObject object) {
        switch (object.getShape()) {
            case Shapes.CUBE -> {

                Rectangle rectangle = new Rectangle();
                rectangle.setX(object.getX());
                rectangle.setY(object.getY());
                rectangle.setWidth(object.getWidth());
                rectangle.setHeight(object.getHeight());
                rectangle.setFill(Color.BLACK);
                GraphicsHandler.collisionLayer.getChildren().add(rectangle);
            }

            // Needs a lot of work
            case Shapes.LINE -> {
                Line line = new Line();
                line.setStartX(object.getX());
                line.setStartY(object.getY());
                line.setEndX(object.getWidth());
                line.setEndY(object.getHeight());
                line.setStroke(Color.BLACK);
                line.setStrokeWidth(3);
                GraphicsHandler.collisionLayer.getChildren().add(line);

            }
            // Needs a lot of work
            case Shapes.CIRCLE -> {
                Circle circle = new Circle();
                circle.setCenterX(object.getX());
                circle.setCenterY(object.getY());
                circle.setRadius(object.getWidth());
                circle.setFill(Color.BLACK);
                GraphicsHandler.collisionLayer.getChildren().add(circle);
            }

            case Shapes.PLAYER -> {
                Rectangle rectangle = new Rectangle();
                rectangle.setX(object.getX());
                rectangle.setY(object.getY());
                rectangle.setWidth(object.getWidth());
                rectangle.setHeight(object.getHeight());
                rectangle.setFill(Color.RED);
                GraphicsHandler.playerLayer.getChildren().add(rectangle);
            }

        }

    }



    public void addObjectToRender(GameObject object) {
        GameObjects.add(object);
    }

    public void removeObjectFromRender(GameObject object) {
        GameObjects.remove(object);
    }


}
