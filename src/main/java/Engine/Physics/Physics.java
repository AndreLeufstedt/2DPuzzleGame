package Engine.Physics;

public interface Physics {
    double gravity = 9.82;
    default double getGravity() {
        return gravity;
    }

    boolean changeGravity(double NewValue);

    void applyGravity(Object object);

    void removeGravity(Object object);


}
