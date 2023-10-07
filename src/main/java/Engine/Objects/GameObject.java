package Engine.Objects;

import Engine.Physics.Physics;
import Engine.Utils.Vector2D;

import java.util.ArrayList;
import java.util.List;

public class GameObject implements Physics {

    public static List<GameObject> GameObjects = new ArrayList<>();

    private Vector2D Position = new Vector2D();

    private double Height;
    private double Width;
    private double gravity = 0;

    private final Shapes Shape;

    protected Vector2D Velocity = new Vector2D(0, 0);





    public GameObject(Vector2D Position, double Height, double Width, Shapes Shape) {
        this.Position = Position;
        this.Height = Height;
        this.Width = Width;
        this.Shape = Shape;
    }

    public double getX() {
        return Position.x;
    }

    public double getY() {
        return Position.y;
    }

    public double getHeight() {
        return Height;
    }

    public double getWidth() {
        return Width;
    }

    public void changeX(double NewValue) {
        this.Position.x = NewValue;
    }

    public void changeY(double NewValue) {
        this.Position.y = NewValue;
    }

    public void changePositionVector(Vector2D vector) {
        this.Position = Position.add(vector);
    }

    public Vector2D getPositionVector() { return this.Position; }

    public void changeHeight(double NewValue) {
        this.Height = NewValue;
    }

    public void changeWidth(double NewValue) {
        this.Width = NewValue;
    }

    // Note: This function won't actually "destroy" the object. It just sets the reference to null.
    // The actual object would still exist if there are other references to it.
    public static void destroyObject(GameObject object) {
        object = null;
    }

    public void changeGravity(int newValue) {
        this.gravity = newValue;
    }

    public Shapes getShape() {
        return this.Shape;
    }

    @Override
    public boolean changeGravity(double NewValue) {
        if(this.gravity != 0) {
            this.gravity = NewValue;
            return true;
        }

        return false;
    }

    @Override
    public void applyGravity() {
        this.gravity = constGravity;
    }

    @Override
    public void removeGravity() {
        this.gravity = 0;

    }

    public void UpdateObject() {
        changePositionVector(this.Velocity);
    }


    public static boolean[] checkCollisionBetween(GameObject obj1, GameObject obj2) {
        // Check horizontal overlap
        boolean collisionX = obj1.getX() + obj1.getWidth() >= obj2.getX() &&
                obj2.getX() + obj2.getWidth() >= obj1.getX();

        // Check if the bottom of obj1 is touching or penetrating the top of obj2
        boolean onTopOf = obj1.getY() + obj1.getHeight() >= obj2.getY() &&
                obj1.getY() + obj1.getHeight() <= obj2.getY() + obj2.getHeight();

        return new boolean[]{collisionX, onTopOf};
    }

}
