package Engine.Objects;

public class GameObject {

    private double X, Y, Height, Width;
    private int gravity = 0;

    private Shapes Shape;

    public GameObject(double X, double Y, double Height, double Width, Shapes Shape) {
        this.X = X;
        this.Y = Y;
        this.Height = Height;
        this.Width = Width;
        this.Shape = Shape;
    }

    public double getX() {
        return X;
    }

    public double getY() {
        return Y;
    }

    public double getHeight() {
        return Height;
    }

    public double getWidth() {
        return Width;
    }

    public void changeX(double NewValue) {
        this.X = NewValue;
    }

    public void changeY(double NewValue) {
        this.Y = NewValue;
    }

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
}
