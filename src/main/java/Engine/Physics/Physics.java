package Engine.Physics;

public interface Physics {
    final double constGravity = 0.1;

    double gravity = 0;

    default double getGravity() {
        return this.gravity;
    }

    boolean changeGravity(double NewValue);

    void applyGravity();

    void removeGravity();


}
