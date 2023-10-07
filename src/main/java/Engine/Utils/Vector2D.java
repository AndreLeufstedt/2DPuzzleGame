package Engine.Utils;

public class Vector2D {

    public double x, y;

    // Constructors
    public Vector2D() {
        this(0, 0);
    }

    public Vector2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    // Add another vector to this one
    public Vector2D add(Vector2D v) {
        return new Vector2D(x + v.x, y + v.y);
    }

    // Subtract another vector from this one
    public Vector2D subtract(Vector2D v) {
        return new Vector2D(x - v.x, y - v.y);
    }

    // Multiply this vector by a scalar
    public Vector2D multiply(double scalar) {
        return new Vector2D(x * scalar, y * scalar);
    }

    // Divide this vector by a scalar
    public Vector2D divide(double scalar) {
        return new Vector2D(x / scalar, y / scalar);
    }

    // Get the magnitude (length) of this vector
    public double magnitude() {
        return Math.sqrt(x * x + y * y);
    }

    // Normalize this vector (make its magnitude 1)
    public Vector2D normalize() {
        double magnitude = magnitude();
        return new Vector2D(x / magnitude, y / magnitude);
    }

    // Dot product with another vector
    public double dot(Vector2D v) {
        return x * v.x + y * v.y;
    }

    // Rotate this vector by an angle (in radians)
    public Vector2D rotate(double angle) {
        double cos = Math.cos(angle);
        double sin = Math.sin(angle);
        return new Vector2D(x * cos - y * sin, x * sin + y * cos);
    }

    public String toString() {
        return "(" + x + ", " + y + ")";
    }

}
