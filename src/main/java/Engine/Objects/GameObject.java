package Engine.Objects;

import Engine.Physics.Physics;
import Engine.Utils.Vector2D;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a game object with physics properties.
 */
public class GameObject implements Physics {

    /** List of all game objects. */
    public static List<GameObject> GameObjects = new ArrayList<>();

    /** Position of the game object. */
    private Vector2D Position = new Vector2D();

    /** Height of the game object. */
    private double Height;
    /** Width of the game object. */
    private double Width;
    /** Gravity applied to the game object. */
    private double gravity = 0;

    /** Shape of the game object. */
    private final Shapes Shape;

    /** Velocity of the game object. */
    protected Vector2D Velocity = new Vector2D(0, 0);

    /**
     * Constructs a game object with specified properties.
     * @param Position Initial position.
     * @param Height Initial height.
     * @param Width Initial width.
     * @param Shape Shape of the game object.
     */
    public GameObject(Vector2D Position, double Height, double Width, Shapes Shape) {
        this.Position = Position;
        this.Height = Height;
        this.Width = Width;
        this.Shape = Shape;
    }

    /** @return X-coordinate of the game object. */
    public double getX() {
        return Position.x;
    }

    /** @return Y-coordinate of the game object. */
    public double getY() {
        return Position.y;
    }

    /** @return Height of the game object. */
    public double getHeight() {
        return Height;
    }

    /** @return Width of the game object. */
    public double getWidth() {
        return Width;
    }

    /**
     * Changes the X-coordinate of the game object.
     * @param NewValue New X-coordinate.
     */
    public void changeX(double NewValue) {
        this.Position.x = NewValue;
    }

    /**
     * Changes the Y-coordinate of the game object.
     * @param NewValue New Y-coordinate.
     */
    public void changeY(double NewValue) {
        this.Position.y = NewValue;
    }

    /**
     * Updates the position of the game object.
     * @param vector Vector to add to the current position.
     */
    public void changePositionVector(Vector2D vector) {
        this.Position = Position.add(vector);
    }

    /** @return Current position vector of the game object. */
    public Vector2D getPositionVector() { return this.Position; }

    /**
     * Changes the height of the game object.
     * @param NewValue New height.
     */
    public void changeHeight(double NewValue) {
        this.Height = NewValue;
    }

    /**
     * Changes the width of the game object.
     * @param NewValue New width.
     */
    public void changeWidth(double NewValue) {
        this.Width = NewValue;
    }

    /**
     * "Destroys" the game object by setting its reference to null.
     * Note: The actual object would still exist if there are other references to it.
     * @param object Game object to destroy.
     */
    public static void destroyObject(GameObject object) {
        object = null;
    }

    /**
     * Changes the gravity applied to the game object.
     * @param newValue New gravity value.
     */
    public void changeGravity(int newValue) {
        this.gravity = newValue;
    }

    /** @return Shape of the game object. */
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

    /** Updates the game object's position based on its velocity. */
    public void UpdateObject() {
        changePositionVector(this.Velocity);
    }

    /**
     * Checks for collision between two game objects.
     * @param obj1 First game object.
     * @param obj2 Second game object.
     * @return True if the game objects collide, false otherwise.
     */
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

    /**
     * Checks if a game object is on the ground.
     * @param obj Game object to check.
     * @return True if the game object is on the ground, false otherwise.
     */
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


    /**
     * Checks for side collision of a game object.
     * @param obj Game object to check.
     * @return "left", "right", or "none" based on the side of collision.
     */
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

    /**
     * Checks for corner collision of a game object.
     * @param obj Game object to check.
     * @return Position vector if there's a corner collision, null otherwise.
     */
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
