package Engine.Utils;

public class DirectionVector extends Vector2D {

    // Constructors
    public DirectionVector() {
        super();
    }

    public DirectionVector(double x, double y) {
        super(clamp(x), clamp(y));
    }

    // Ensure values are clamped between -1 and 1
    private static double clamp(double value) {
        if (value > 1) return 1;
        if (value < -1) return -1;
        return value;
    }

    @Override
    public DirectionVector add(Vector2D v) {
        return new DirectionVector(clamp(x + v.x), clamp(y + v.y));
    }

    @Override
    public DirectionVector subtract(Vector2D v) {
        return new DirectionVector(clamp(x - v.x), clamp(y - v.y));
    }

    @Override
    public DirectionVector multiply(double scalar) {
        return new DirectionVector(clamp(x * scalar), clamp(y * scalar));
    }

    @Override
    public DirectionVector divide(double scalar) {
        return new DirectionVector(clamp(x / scalar), clamp(y / scalar));
    }

    @Override
    public DirectionVector normalize() {
        Vector2D normalized = super.normalize();
        return new DirectionVector(normalized.x, normalized.y);
    }

    @Override
    public DirectionVector rotate(double angle) {
        Vector2D rotated = super.rotate(angle);
        return new DirectionVector(rotated.x, rotated.y);
    }

    public _Directions getActualDirection() {
        if (Math.abs(x) > Math.abs(y)) {
            // Horizontal movement is dominant
            return x > 0 ? _Directions.RIGHT : _Directions.LEFT;
        } else {
            // Vertical movement is dominant or equal
            return y > 0 ? _Directions.UP : _Directions.DOWN;
        }
    }

}
