package Engine.Utils;

/**
 * Represents a 2D vector with x and y components.
 */
public class Vector2D {

    /** X component of the vector. */
    public double x;
    /** Y component of the vector. */
    public double y;

    /**
     * Default constructor. Initializes the vector to (0, 0).
     */
    public Vector2D() {
        this(0, 0);
    }

    /**
     * Constructs a vector with specified x and y components.
     * @param x X component.
     * @param y Y component.
     */
    public Vector2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Adds another vector to this one.
     * @param v The other vector.
     * @return A new vector that is the sum of this vector and v.
     */
    public Vector2D add(Vector2D v) {
        return new Vector2D(x + v.x, y + v.y);
    }

    /**
     * Subtracts another vector from this one.
     * @param v The other vector.
     * @return A new vector that is the difference between this vector and v.
     */
    public Vector2D subtract(Vector2D v) {
        return new Vector2D(x - v.x, y - v.y);
    }

    /**
     * Multiplies this vector by a scalar.
     * @param scalar The scalar value.
     * @return A new vector that is this vector multiplied by the scalar.
     */
    public Vector2D multiply(double scalar) {
        return new Vector2D(x * scalar, y * scalar);
    }

    /**
     * Divides this vector by a scalar.
     * @param scalar The scalar value.
     * @return A new vector that is this vector divided by the scalar.
     */
    public Vector2D divide(double scalar) {
        return new Vector2D(x / scalar, y / scalar);
    }

    /**
     * Calculates the magnitude (length) of this vector.
     * @return The magnitude of this vector.
     */
    public double magnitude() {
        return Math.sqrt(x * x + y * y);
    }

    /**
     * Normalizes this vector (makes its magnitude 1).
     * @return A new vector that is the normalized version of this vector.
     */
    public Vector2D normalize() {
        double magnitude = magnitude();
        return new Vector2D(x / magnitude, y / magnitude);
    }

    /**
     * Calculates the dot product of this vector with another vector.
     * @param v The other vector.
     * @return The dot product of this vector and v.
     */
    public double dot(Vector2D v) {
        return x * v.x + y * v.y;
    }

    /**
     * Rotates this vector by a specified angle (in radians).
     * @param angle The angle in radians.
     * @return A new vector that is this vector rotated by the specified angle.
     */
    public Vector2D rotate(double angle) {
        double cos = Math.cos(angle);
        double sin = Math.sin(angle);
        return new Vector2D(x * cos - y * sin, x * sin + y * cos);
    }

    /**
     * Returns a string representation of this vector.
     * @return A string in the format "(x, y)".
     */
    public String toString() {
        return "(" + x + ", " + y + ")";
    }

}
