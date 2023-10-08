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


    public static boolean checkCollisionBetween(GameObject obj1, GameObject obj2) {
        // Check horizontal overlap
        boolean collisionX = obj1.getX() + obj1.getWidth() >= obj2.getX() &&
                obj2.getX() + obj2.getWidth() >= obj1.getX();

        // Check vertical overlap
        boolean collisionY = obj1.getY() + obj1.getHeight() >= obj2.getY() &&
                obj2.getY() + obj2.getHeight() >= obj1.getY();

        // Return true if both horizontal and vertical overlaps exist
        return collisionX && collisionY;
    }

    public static boolean onGround(GameObject obj) {
        for (GameObject other : GameObject.GameObjects) {
            if (obj == other) continue; // Skip checking against itself

            // Check horizontal overlap
            boolean collisionX = obj.getX() + obj.getWidth() >= other.getX() &&
                    other.getX() + other.getWidth() >= obj.getX();

            // Check if the bottom of obj is close to or touching the top of other
            boolean nearOrTouchingTop = (obj.getY() + obj.getHeight()) - other.getY() < 0.5 &&
                    (obj.getY() + obj.getHeight()) - other.getY() >= 0;

            if (collisionX && nearOrTouchingTop) {
                return true; // obj is on or near the ground (on top of another object)
            }
        }
        return false;
    }


    public static String sideCollision(GameObject obj) {
        final double THRESHOLD = 2.0;  // Adjust this value as needed

        for (GameObject other : GameObject.GameObjects) {
            if (obj == other) continue; // Skip checking against itself

            // Check vertical overlap
            boolean collisionY = obj.getY() + obj.getHeight() >= other.getY() &&
                    other.getY() + other.getHeight() >= obj.getY();

            // Check if the left side of obj is close to the right side of other
            boolean nearOrTouchingLeft = (obj.getX() - (other.getX() + other.getWidth())) < THRESHOLD &&
                    (obj.getX() - (other.getX() + other.getWidth())) >= 0;

            // Check if the right side of obj is close to the left side of other
            boolean nearOrTouchingRight = ((obj.getX() + obj.getWidth()) - other.getX()) < THRESHOLD &&
                    ((obj.getX() + obj.getWidth()) - other.getX()) >= 0;

            if (collisionY && nearOrTouchingLeft) {
                return "left"; // obj is colliding or close to the left side of another object
            } else if (collisionY && nearOrTouchingRight) {
                return "right"; // obj is colliding or close to the right side of another object
            }
        }
        return "none"; // No sideways collision detected
    }

    public static Vector2D cornerCollision(GameObject obj) {
        for (GameObject other : GameObject.GameObjects) {
            if (obj == other) continue; // Skip checking against itself

            // Check for bottom-left corner of obj aligning with top-right corner of other
            boolean bottomLeftCorner = Math.abs(obj.getX() - (other.getX() + other.getWidth())) < 2 &&
                    Math.abs((obj.getY() + obj.getHeight()) - other.getY()) < 2;

            // Check for bottom-right corner of obj aligning with top-left corner of other
            boolean bottomRightCorner = Math.abs((obj.getX() + obj.getWidth()) - other.getX()) < 2 &&
                    Math.abs((obj.getY() + obj.getHeight()) - other.getY()) < 2;

            if (bottomLeftCorner && obj.Velocity.x > 0) {
                // Player is moving to the right, so ignore the bottom-left corner collision
                continue;
            } else if (bottomRightCorner && obj.Velocity.x < 0) {
                // Player is moving to the left, so ignore the bottom-right corner collision
                continue;
            }

            if (bottomLeftCorner) {
                return new Vector2D(-2, other.getY() - obj.getHeight()); // Adjust left and up
            } else if (bottomRightCorner) {
                return new Vector2D(2, other.getY() - obj.getHeight()); // Adjust right and up
            }
        }
        return null; // No corner collision detected
    }










}
